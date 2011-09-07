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

package jopencc;

import static jopencc.util.Util.*;
import static jopencc.util.Dict.*;
import java.io.File;
import jopencc.util.Dict;
import jopencc.util.FileUtil;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length == 0){
			log("starting gui...");
			new jopencc.gui.MainWindow().run();
			return;
		}
		
		if (args.length < 6){
			showUsage();
			return;
		}
		
		String[] parm = new String[3];
		int i = 0;
		for (String s: args){
			i++;
			if ("-c".equals(s))
				parm[0] = args[i];
			
			if ("-i".equals(s))
				parm[1] = args[i];
			
			if ("-o".equals(s))
				parm[2] = args[i];
		}
		
		if (isMissing(parm[0]) || isMissing(parm[1]) || isMissing(parm[2])){
			showUsage();
			return;
		}
		
		// read the source
		File srcFile = new File(parm[1]);
		if (!FileUtil.isReadableFile(srcFile)){
			log("cannot read source " + srcFile.getAbsolutePath());
			showUsage();
			return;
		}
		
		// prepare the output file
		File outFile = new File(parm[2]);
		if (outFile.exists())
			outFile.delete();
		
		StringBuffer src = FileUtil.readSrc(srcFile);
		log("source [" + srcFile.getAbsolutePath() + "] read");
		
		Dict dict = new Dict(src, parm[0]);
		dict.convert();
		
		log("write output [" + outFile.getAbsolutePath() + "]");
		FileUtil.write(outFile, dict.getResult());
		dict.clear();
	}
	
	private static void showUsage(){
		log("java jopencc.Main -c config -i input -o output\n" +
			"\tconfig: " + ZHS_TO_ZHT + " - simplified Chinese to traditional Chinese\n" +
			"\t        " + ZHT_TO_ZHS + " - traditional Chinese to simplified Chinese");	
	}
}
