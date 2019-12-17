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

public class CompareUtil {
	/**
	 * 严格意义上的数值相等比较；任何一个为null，则返回false,都为null则返回true，否则比较值
	 * */
	public static boolean isEqual(Integer a,Integer b)
	{
		if(a==null && b==null) return true;
		if(a!=null && b!=null &&  a.intValue()==b.intValue())
			return true;
		else return false;
		
	}
	/**
	 * 严格意义上的字符串值相等比较；任何一个为null或都为null，则返回false
	 * */
	public static boolean isEqual(String a,String b)
	{
		if(a!=null && b!=null &&  a.equals(b))
			return true;
		else return false;
	}
}
