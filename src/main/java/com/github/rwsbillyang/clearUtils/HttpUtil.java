/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rwsbillyang.clearUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;



/**
 * 
 * @version v1.0
 * @date 2014-7-4
 */
public class HttpUtil {

	public final static String defaultContentEncoding = "UTF-8";

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, null, null);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendGet(String url, Map<String, String> params) {
		return sendGet(url, params, null);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static String sendGet(String urlString, Map<String, String> params, Map<String, String> headers) {
		StringBuffer response = new StringBuffer();
		BufferedReader in = null;
		HttpURLConnection conn = null;
		try {
			StringBuilder buf = new StringBuilder(urlString);
			// 设置请求参数
			if (params != null && params.size() > 0) {
				buf.append("?");
				for (Map.Entry<String, String> entry : params.entrySet()) {
					buf.append(entry.getKey()).append("=")
							.append(URLEncoder.encode(entry.getValue(), defaultContentEncoding)).append("&");
				}
				buf.deleteCharAt(buf.length() - 1);
			}
			URL url = new URL(buf.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:60.0) Gecko/20100101 Firefox/60.0");
			// 设置请求头
			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			conn.connect();

			// 获取响应数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), defaultContentEncoding));
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
		} catch (Exception e) {
			System.err.print("send GET request exeption：" + e.getMessage());
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				System.err.print("close instream exception" + e2.getMessage());
			}
		}

		return response.toString();
	}

	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @return
	 */
	public static String sendPost(String url) {
		return sendPost(url, null, null);
	}

	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> params) {
		return sendPost(url, params, null);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String urlString, Map<String, String> params, Map<String, String> headers) {
		StringBuffer response = new StringBuffer();
		HttpURLConnection conn = null;
		OutputStream out = null;
		BufferedReader in = null;
		try {
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:60.0) Gecko/20100101 Firefox/60.0");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			// 设置请求参数
			StringBuffer param = new StringBuffer();
			if (params != null && params.size() > 0) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					param.append(entry.getKey()).append("=")
							.append(URLEncoder.encode(entry.getValue(), defaultContentEncoding)).append("&");
				}
			}
			param.deleteCharAt(param.length() - 1);

			out = conn.getOutputStream();
			out.write(param.toString().getBytes());
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), defaultContentEncoding));
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
		} catch (Exception e) {
			System.err.print("发送 POST 请求出现异常！" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.err.print("发送 POST 请求出现异常！" + ex.getMessage());
			}
		}
		return response.toString();
	}

	public static boolean downloadFile(String url, String absolutePath, String filename) {
		if (!FileUtil.createDir(absolutePath)) {
			System.err.print("create folder failed: absolutePath=" + absolutePath + ",ignore to download");
			return false;
		}
		if (MyStringUtil.isBlank(url)) {
			System.err.print("url is empty,ignore to download");
			return false;
		}
		try {
			FileOutputStream fileOutStream = new FileOutputStream(absolutePath + filename);

			if (url.startsWith("//"))
				url = "http:" + url;

			// 建立请求链接
			URLConnection connection = new URL(url).openConnection();
			InputStream in = connection.getInputStream();
			if (null != in) {
				int len;
				byte[] buf = new byte[4096];
				while ((len = in.read(buf)) != -1) {
					fileOutStream.write(buf, 0, len);
				}
				fileOutStream.flush();
				fileOutStream.close();
				in.close();
			}
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.print("Exception: downloadFile failed, url=" + url + ",absoluteFilename=" + absolutePath + filename);
		}
		return false;
	}
}
