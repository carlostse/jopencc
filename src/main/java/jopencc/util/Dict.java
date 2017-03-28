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

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import static jopencc.util.Util.*;

public class Dict {

    public static final String
            ZHT_TO_ZHS = "zht2zhs",
            ZHS_TO_ZHT = "zhs2zht";

    private static final String
            PROPERTY_PHRASE = "phrase",
            PROPERTY_CHAR = "character";

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
        String[] p;
        try {
            is = getClass().getClassLoader().getResourceAsStream(config + ".properties");
            p = getValFrProperty(is, new String[]{PROPERTY_PHRASE, PROPERTY_CHAR});

        } catch (Exception e) {
            log("initDict Exception: " + e.getMessage());
            Util.close(is);
            return;
        }

        if (isMissing(p)) {
            log("cannot get config: " + config);
            return;
        }

        dictPhrase = FileUtil.readDict(new File(p[0]));
        dictChar = FileUtil.readDict(new File(p[1]));

        if (isMissing(dictPhrase) || isMissing(dictChar)) {
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
        if (isMissing(src)) {
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
     *
     * @param src - source string
     * @param dict - dictionary
     */
    private static void map(StringBuffer src, Map<String, String> dict) {
       for (String key: dict.keySet()){
            int pos = 0, idx;
            while ((idx = src.indexOf(key, pos)) > -1) {
                String value = dict.get(key);
                int len = value.length();
//				log(key + " -> " + value + ", idx: " + idx + ", len: " + len);
                src.replace(idx, idx + len, value);
                pos = idx + len;
            }
        }
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
