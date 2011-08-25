package jopencc.util;

import static jopencc.util.Util.*;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dict {

	private static final String PROPERTY_PHRASE = "phrase",
								PROPERTY_CHAR	= "character";

	public Dict(StringBuffer src, String config) {
		super();
		this.src = src;
		this.config = config;
		dictPhrase = new LinkedHashMap<String, String>();
		dictChar = new LinkedHashMap<String, String>();
	}

	private static boolean init;
	private static Map<String, String> dictPhrase, dictChar; // static dictionary map
	private StringBuffer src;
	private String config;

	public StringBuffer getResult(){
		return src;
	}

	/**
	 * initialize dictionary
	 */
	private void initDict(){
		if (init)
			return;
		
		log("initialize map...");
		String[] p = getValFrProperty(config + ".properties", new String[]{ PROPERTY_PHRASE, PROPERTY_CHAR });
		if (isMissing(p)){
			log("cannot get config: " + config);
			return;
		}
		
		dictPhrase = FileUtil.readDict(new File(p[0]));
		dictChar = FileUtil.readDict(new File(p[1]));
		
		if (isMissing(dictPhrase) && isMissing(dictChar)){
			log("cannot get dict");
			return;
		}
		
		init = true;
	}
	
	/**
	 * Convert the source
	 */
	public void convert(){
		// initialize the map once
		initDict();
		
		// map the phrases
		log("map phrases...");
		if (!isMissing(src) && !isMissing(dictPhrase))
			map(src, dictPhrase);
		
		// map the characters
		log("map characters...");
		if (!isMissing(src) && !isMissing(dictChar))
			map(src, dictChar);
	}

	private static void map(StringBuffer src, Map<String, String> dict){
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
}
