/*
Copyright 2011 Carlos Tse <carlos@aboutmy.info>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package jopencc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Util {

	/**
	 * Logging function
	 * @param str
	 */
	public static void log(String str){
		System.out.println(str);
	}

	/**
	 * Logging function
	 * @param str
	 * @param e
	 */
	public static void log(String str, Throwable e){
		System.out.println(str);
		if (e != null)
			e.printStackTrace();
	}

	/**
	 * @param str
	 * @return true if the string is null or empty
	 */
	public static boolean isMissing(String str){
		return str == null || str.trim().length() == 0;
	}

	/**
	 * @param str
	 * @return true if the StringBuffer is null or empty
	 */
	public static boolean isMissing(StringBuffer str){
		return str == null || str.length() == 0;
	}
	
	/**
	 * @param value
	 * @return true if input is null or contains no values
	 */
	public static final boolean isMissing(String[] value){
		if (value == null)
			return true;
		
		for (String txt: value)
			if (!isMissing(txt))
				return false;
		
		return true;
	}
	
	/**
	 * @param list
	 * @return true if the list is null or empty
	 */
	public static boolean isMissing(List<?> list){
		return list == null || list.size() == 0;
	}
	
	/**
	 * @param list
	 * @return true if the map is null or empty
	 */
	public static boolean isMissing(Map<?, ?> map){
		return map == null || map.size() == 0;
	}
	
	/**
	 * Get values from property file
	 * @param steam
	 * @param key
	 * @return key array
	 */
	public static String[] getValFrProperty(InputStream steam, String[] key){
		if (isMissing(key)) 
			return null;
		
		String[] result = new String[key.length];
		Properties p;
		int i = 0;
		
		try {
			p = new Properties();
			p.load(steam);
			
			for (String s: key)
				result[i++] = p.getProperty(s);

		} catch (FileNotFoundException e) {
			log("getFromPropertyFile, FileNotFoundException", e);
		} catch (IOException e) {
			log("getFromPropertyFile, IOException", e);
		} finally {
			p = null;
		}
		return result;
	}
}
