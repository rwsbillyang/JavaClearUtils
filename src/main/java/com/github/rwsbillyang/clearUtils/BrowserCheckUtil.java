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


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * 检测是否为移动端设备访问 
 * 
 */ 
public class BrowserCheckUtil {
	public final static int BROWSER_PC=0;
	public final static int BROWSER_MOBILE=1;
	public final static int BROWSER_WECHAT=2;
	public final static String[] DEVICETYPE= {"PC","手机","微信"};
	
	public final static int PLATFORM_IOS=100;
	public final static int PLATFORM_ANDROID=200;
	public final static int PLATFORM_OTHER=300;
	

	  // \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),    
    // 字符串在编译时会被转码一次,所以是 "\\b"    
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)    
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"    
            +"|windows (phone|ce)|blackberry"    
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"    
            +"|laystation portable)|nokia|fennec|htc[-_]"    
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"    
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
      
    //移动设备正则匹配：手机端、平板  
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);    
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);    
        
 
    /**
     * 0: PC browser
     * 1: mobile
     * 2: weixin
     * */
	public static int  getBrowserType(String userAgent)				
	{  	    
	    boolean isFromMobile=false;  	 
	    isFromMobile=isMobile(userAgent); 
	       if(isFromMobile){
	    	   if(userAgent.contains("micromessenger"))
		    	   return BROWSER_WECHAT;
		       else 
		    	   return BROWSER_MOBILE;
	       }     
	    return BROWSER_PC;  
	}
	

    /** 
     * 检测是否是移动设备访问 
     *  
     * @Title: check 
     * @Date : 2014-7-7 下午01:29:07 
     * @param userAgent 浏览器标识 
     * @return       0: PC browser  1: mobile
     */  
    public static boolean isMobile(String userAgent){    
        if(null == userAgent){  
        	System.err.print("userAgent is null");
            return false;
        }    
        // 匹配    
        Matcher matcherPhone = phonePat.matcher(userAgent);    
        Matcher matcherTable = tablePat.matcher(userAgent);    
        if(matcherPhone.find() || matcherTable.find()){    
            return true;    
        } else {    
            return false;    
        }    
    } 
    
    
    /**
     * 检查是否是微信浏览器
	 * 根据HttpServletRequest中的header信息中的User-Agent
	 * */
	public static boolean isMicroMessenger(String userAgent)
	{
		if(userAgent==null)
		{
			System.err.print("Strange!!! userAgent is null");
			return false;
		}
		userAgent= userAgent.toLowerCase();
   	 	if(userAgent.contains("micromessenger")){
   			return true;
   	 	}else{
   	 		//String url= UrlUtil.getUrlFromRequest(request);
   	 		//log.info("\n userAgent="+userAgent+",url="+url);
   	 		return false;
   	 	}
	}
	
	/**
	 * 获取设备平台类型
	 * */
	public static int getPlatform(String ua){
	    if(ua.contains("iPhone")||ua.contains("iOS")||ua.contains("iPod")||ua.contains("iPad")){  
	        return PLATFORM_IOS;
	    } else if(ua.contains("Android") || ua.contains("Linux")) { 
	       return PLATFORM_ANDROID;
	    } 
	    return PLATFORM_OTHER;
	}

}
