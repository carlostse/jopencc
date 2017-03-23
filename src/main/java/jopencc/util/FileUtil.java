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
import static jopencc.util.Util.log;

public class FileUtil {

    public static StringBuffer readSrc(File file) {
        StringBuffer sb = new StringBuffer();

        if (!isReadableFile(file))
            return sb;

        BufferedReader reader = null;
        String s;

        try {
            reader = getReader(file);

            while ((s = reader.readLine()) != null) {
                sb.append(s);    // don't use + \n here, to prevent generate new String
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

            while ((s = reader.readLine()) != null) {
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

    public static void write(File file, String str) {
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
     *
     * @param file
     * @return true if the file is readable
     */
    public static boolean isReadableFile(File file) {
        return file != null && file.exists() && file.isFile() && file.canRead();
    }

    /**
     * Check the file can write or not
     *
     * @param file
     * @return true if the file is writable
     */
    public static boolean isWritableFile(File file) {
        return file != null && file.exists() && file.isFile() && file.canWrite();
    }

    /**
     * @param file
     * @return reader using UTF-8
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private static BufferedReader getReader(File file) throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    }

    /**
     * @param file
     * @return writer using UTF-8
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private static BufferedWriter getWrite(File file) throws UnsupportedEncodingException, FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
    }

    /**
     * Close the reader
     *
     * @param reader
     */
    private static void closeReader(Reader reader) {
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
     *
     * @param writer
     */
    private static void closeWriter(Writer writer) {
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
