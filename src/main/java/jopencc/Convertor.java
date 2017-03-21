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
		DICT_TO_ZHS = new Dict(Dict.ZHT_TO_ZHS);
		DICT_TO_ZHT = new Dict(Dict.ZHS_TO_ZHT);
	}
	
	private static final Dict DICT_TO_ZHS, DICT_TO_ZHT;
	
	/**
	 * Convert the source to Traditional Chinese
	 * @param src
	 * @return Text in Traditional Chinese
	 */
	public String convertToZht(StringBuffer src){
		if (Util.isMissing(src))
			return src == null? null: src.toString();
		
		try {
			DICT_TO_ZHT.setSrc(src);
			DICT_TO_ZHT.convert();
			return DICT_TO_ZHT.getResult();
			
		} catch (Exception e){
			return src == null? null: src.toString();
		} finally {
			DICT_TO_ZHT.clear();
		}
	}
	
	/**
	 * Convert the source to Traditional Chinese
	 * @param src
	 * @return Text in Traditional Chinese
	 */
	public String convertToZht(String src){
		return Util.isMissing(src)? src: convertToZht(new StringBuffer(src));
	}
	
	/**
	 * Convert the source to Simplified Chinese
	 * @param src
	 * @return Text in Traditional Chinese
	 */
	public String convertToZhs(StringBuffer src){
		if (Util.isMissing(src))
			return src == null? null: src.toString();
		
		try {
			DICT_TO_ZHS.setSrc(src);
			DICT_TO_ZHS.convert();
			return DICT_TO_ZHS.getResult();
			
		} catch (Exception e){
			return src == null? null: src.toString();
		} finally {
			DICT_TO_ZHS.clear();
		}
	}
	
	/**
	 * Convert the source to Simplified Chinese
	 * @param src
	 * @return Text in Traditional Chinese
	 */
	public String convertToZhs(String src){
		return Util.isMissing(src)? src: convertToZhs(new StringBuffer(src));
	}
}
