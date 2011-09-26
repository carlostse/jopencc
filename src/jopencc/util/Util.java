/*
Copyright (c) 2011, Carlos Tse <copperoxide@gmail.com>
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
