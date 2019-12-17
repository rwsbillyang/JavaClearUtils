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

import java.util.regex.Pattern;

public class MyStringUtil {
	// http://www.oschina.net/code/snippet_1021818_47946

	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机号
	 */
	// public static final String REGEX_MOBILE =
	// "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	public static final String REGEX_MOBILE = "^((\\+?86)|(\\+86))?1\\d{10}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 校验用户名
	 * 
	 * @param username
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUsername(String username) {
		if (username == null || username.isEmpty())
			return false;
		return Pattern.matches(REGEX_USERNAME, username);
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isPassword(String password) {
		if (password == null || password.isEmpty())
			return false;
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobileNumber(String mobile) {
		if (MyStringUtil.isBlank(mobile))
			return false;
		mobile = deleteChar(mobile, '-');
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.isEmpty())
			return false;
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 校验汉字
	 * 
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isChinese(String chinese) {
		if (chinese == null || chinese.isEmpty())
			return false;
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 校验身份证
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIDCard(String idCard) {
		if (idCard == null || idCard.isEmpty())
			return false;
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * 校验URL
	 * 
	 * @param url
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUrl(String url) {
		if (url == null || url.isEmpty())
			return false;
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * 校验IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIPAddr(String ipAddr) {
		if (ipAddr == null || ipAddr.isEmpty())
			return false;
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	/**
	 * 删除字符串中的某个字符 参见：https://blog.csdn.net/li767517488/article/details/64919194
	 */
	public static String deleteChar(String sourceString, char theDelChar) {
		StringBuffer stringBuffer = new StringBuffer("");
		for (int i = 0; i < sourceString.length(); i++) {
			if (sourceString.charAt(i) != theDelChar) {
				stringBuffer.append(sourceString.charAt(i));
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * 截断字符串，不超过length长度
	 */
	public static String truncate(String str, int length) {
		if (str == null)
			return null;
		if (str.length() <= length)
			return str;

		System.out.print("truncate length=" + length + ", origin str=" + str);
		return str.substring(0, length);
	}

	public static boolean isBlank(String str) {
		if (str == null || str == "")
			return true;
		else
			return false;
	}
}
