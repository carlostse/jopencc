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

package jopencc;

import jopencc.util.Dict;
import jopencc.util.Util;

public class Convertor {

    static {
        dictToZhs = new Dict(Dict.ZHT_TO_ZHS);
        dictToZht = new Dict(Dict.ZHS_TO_ZHT);
    }

    private static final Dict dictToZhs, dictToZht;

    /**
     * Convert the source to Traditional Chinese
     *
     * @param src - source StringBuffer
     * @return Text in Traditional Chinese
     */
    public String convertToZht(StringBuffer src) {
        if (Util.isMissing(src))
            return src == null ? null : src.toString();

        try {
            dictToZht.setSrc(src);
            dictToZht.convert();
            return dictToZht.getResult();

        } catch (Exception e) {
            return src.toString();
        } finally {
            dictToZht.clear();
        }
    }

    /**
     * Convert the source to Traditional Chinese
     *
     * @param src - source String
     * @return Text in Traditional Chinese
     */
    public String convertToZht(String src) {
        return Util.isMissing(src) ? src : convertToZht(new StringBuffer(src));
    }

    /**
     * Convert the source to Simplified Chinese
     *
     * @param src - source StringBuffer
     * @return Text in Traditional Chinese
     */
    public String convertToZhs(StringBuffer src) {
        if (Util.isMissing(src))
            return src == null ? null : src.toString();

        try {
            dictToZhs.setSrc(src);
            dictToZhs.convert();
            return dictToZhs.getResult();

        } catch (Exception e) {
            return src.toString();
        } finally {
            dictToZhs.clear();
        }
    }

    /**
     * Convert the source to Simplified Chinese
     *
     * @param src - source String
     * @return Text in Traditional Chinese
     */
    public String convertToZhs(String src) {
        return Util.isMissing(src) ? src : convertToZhs(new StringBuffer(src));
    }

}
