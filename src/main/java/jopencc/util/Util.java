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

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

public class Util {

    /**
     * Logging function
     *
     * @param message - log message
     */
    public static void log(String message) {
        System.out.println(message);
    }

    /**
     * Logging function
     *
     * @param message - log message
     * @param e       - throwable
     */
    public static void log(String message, Throwable e) {
        System.out.println(message);
        if (e != null)
            e.printStackTrace();
    }

    /**
     * Check if the object is null or empty
     *
     * @param obj - the test object
     * @return true if the value is missing
     */
    public static boolean isMissing(Object obj) {
        if (obj == null) return true;
        if (obj instanceof CharSequence) return (obj).toString().trim().length() < 1;
        if (obj instanceof Collection) return ((Collection) obj).size() < 1;
        if (obj instanceof Map) return ((Map) obj).size() < 1;
        if (obj instanceof byte[]) return ((byte[]) obj).length < 1;
        if (obj instanceof Object[]) return ((Object[]) obj).length < 1;
        return false; // default just check not null
    }

    /**
     * Get values from property file
     *
     * @param steam - InputStream
     * @param key   - key array
     * @return value array
     */
    public static String[] getValFrProperty(InputStream steam, String[] key) {
        if (isMissing(key))
            return null;

        String[] result = new String[key.length];
        int i = 0;
        try {
            Properties p = new Properties();
            p.load(steam);

            for (String s : key)
                result[i++] = p.getProperty(s);

        } catch (FileNotFoundException e) {
            log("getFromPropertyFile, FileNotFoundException", e);
        } catch (IOException e) {
            log("getFromPropertyFile, IOException", e);
        }
        return result;
    }

    /**
     * Close stream, file, etc.
     *
     * @param c - closeable
     */
    public static void close(Closeable c){
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            // ignore exception
        }
    }
}
