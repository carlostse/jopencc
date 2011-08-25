package jopencc.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	 * @param key
	 * @return
	 */
	public static String[] getValFrProperty(String propertyFileName, String[] key){
		if (isMissing(key)) 
			return null;
		
		Properties p;
		String[] result = new String[key.length];
		int i = 0;
		try {
			p = new Properties();
			p.load(new BufferedInputStream(new FileInputStream(propertyFileName)));
			
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
