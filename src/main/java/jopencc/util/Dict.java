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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dict {

	public static final String	ZHT_TO_ZHS		= "zht2zhs",
								ZHS_TO_ZHT		= "zhs2zht";
	
	private static final String PROPERTY_PHRASE = "phrase",
								PROPERTY_CHAR	= "character";
	
	public Dict(String config) {
		super();
		this.config = config;
		init();
	}
	
	public Dict(StringBuffer src, String config) {
		super();
		setSrc(src);
		this.config = config;
		init();
	}
	
	public Dict(String src, String config) {
		super();
		setSrc(src);
		this.config = config;
		init();
	}

	private void init() {
		dictPhrase = new LinkedHashMap<String, String>();
		dictChar = new LinkedHashMap<String, String>();
	}
	
	private boolean init;
	private Map<String, String> dictPhrase, dictChar; // static dictionary map
	private StringBuffer src;
	private String config;
	
	/**
	 * initialize dictionary
	 */
	private void initDict() {
		if (init)
			return;
		
		log("initialize map...");

		InputStream is = null;
		String[] p = null;

		try {
			is = getClass().getClassLoader().getResourceAsStream(config + ".properties");
			p = getValFrProperty(is, new String[]{ PROPERTY_PHRASE, PROPERTY_CHAR });

		} catch (Exception e){
			log("initDict Exception: " + e.getStackTrace());
			if (is != null){
				try {
					is.close();
				} catch (IOException e1) {
					log("initDict close IOException: " + e1.getStackTrace());
				}
				is = null;
			}
			return;
		}

		if (isMissing(p)){
			log("cannot get config: " + config);
			return;
		}
		
		dictPhrase = FileUtil.readDict(new File(p[0]));
		dictChar = FileUtil.readDict(new File(p[1]));
		
		if (isMissing(dictPhrase) || isMissing(dictChar)){
			log("cannot get dict");
			return;
		}
		
		init = true;
	}
	
	/**
	 * Convert the source
	 */
	public void convert() {
		// return if source is empty
		if (isMissing(src)){
			log("missing src");
			return;
		}
		
		// initialize the map once
		initDict();
		
		// map the phrases
//		log("map phrases...");
		if (!isMissing(dictPhrase))
			map(src, dictPhrase);
		else 
			log("missing dictPhrase");
		
		// map the characters
//		log("map characters...");
		if (!isMissing(dictChar))
			map(src, dictChar);
		else 
			log("missing dictChar");
	}

	/**
	 * Map the source with dictionary
	 * @param src
	 * @param dict
	 */
	private static void map(StringBuffer src, Map<String, String> dict) {
		String key, value;
		int idx, pos, len;
		Iterator<String> it = dict.keySet().iterator();
		while (it.hasNext()){
			key = it.next();
			pos = 0;
			while ((idx = src.indexOf(key, pos)) > -1){
				value = dict.get(key);
				len = value.length();
//				log(key + " -> " + value + ", idx: " + idx + ", len: " + len);
				src.replace(idx, idx + len, value);
				pos = idx + len;
			}
		}
		it = null;
	}
	
	public void clear() {
		src = null;
	}
	
	public String getResult() {
		return src.toString();
	}

	public void setSrc(StringBuffer src) {
		this.src = src;
	}
	
	public void setSrc(String src) {
		this.src = new StringBuffer(src);
	}
}
