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

import static jopencc.util.Util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class FileUtil {

	public static StringBuffer readSrc(File file) {
		StringBuffer sb = new StringBuffer();
		
		if (!isReadableFile(file))
			return sb;
		
		BufferedReader reader = null;
		String s;
		
		try {
			reader = getReader(file);
			
			while ((s = reader.readLine()) != null){
				sb.append(s);	// don't use + \n here, to prevent generate new String
				sb.append('\n');
			}
			
		} catch (IOException e) {
			log("readSrc , IOException: ", e);
		} finally {
			closeReader(reader);
		}
		return sb;
	}
	
	public static LinkedHashMap<String, String> readDict(File file) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		
		if (!isReadableFile(file))
			return map;
		
		String s;
		String[] array;
		BufferedReader reader = null;
		
		try {
			reader = getReader(file);
			
			while ((s = reader.readLine()) != null){
				array = s.split("\t");
				if (array == null || array.length < 2)
					continue;
				map.put(array[0], array[1].split(" ")[0]);
			}
			
		} catch (IOException e) {
			log("readDict , IOException: ", e);
		} finally {
			closeReader(reader);
		}
		return map;
	}
	
	public static void write(File file, String str){
		BufferedWriter writer = null;
		
		try {
			writer = getWrite(file);
			writer.write(str);
			
		} catch (UnsupportedEncodingException e) {
			log("write , UnsupportedEncodingException: ", e);
		} catch (FileNotFoundException e) {
			log("write , FileNotFoundException: ", e);
		} catch (IOException e) {
			log("write , IOException: ", e);
		} finally {
			closeWriter(writer);
		}
	}
	
	/**
	 * Check the file can read or not
	 * @param file
	 * @return true if the file is readable
	 */
	public static boolean isReadableFile(File file){
		return file != null && file.exists() && file.isFile() && file.canRead();
	}
	
	/**
	 * Check the file can write or not
	 * @param file
	 * @return true if the file is writable
	 */
	public static boolean isWritableFile(File file){
		return file != null && file.exists() && file.isFile() && file.canWrite();
	}
	
	/**
	 * @param file
	 * @return reader using UTF-8
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private static BufferedReader getReader(File file) throws FileNotFoundException, UnsupportedEncodingException{
		return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
	}
	
	/**
	 * @param file
	 * @return writer using UTF-8
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private static BufferedWriter getWrite(File file) throws UnsupportedEncodingException, FileNotFoundException{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
	}
	
	/**
	 * Close the reader
	 * @param reader
	 */
	private static void closeReader(Reader reader){
		if (reader == null)
			return;
		try {
			reader.close();
		} catch (IOException e) {
			log("closeReader , IOException: ", e);
		}
		reader = null;
	}
	
	/**
	 * Close the writer
	 * @param writer
	 */
	private static void closeWriter(Writer writer){
		if (writer == null)
			return;
		try {
			writer.close();
		} catch (IOException e) {
			log("closeWriter , IOException: ", e);
		}
		writer = null;
	}
}
