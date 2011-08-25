package jopencc;

import static jopencc.util.Util.*;
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
		
		StringBuffer src = FileUtil.readSrc(srcFile);
		log("source [" + srcFile.getAbsolutePath() + "] read");
		
		Dict dict = new Dict(src, parm[0]);
		dict.convert();
		String output = dict.getResult().toString();
		
		File f = new File(parm[2]);
		if (f.exists())
			f.delete();
		
		log("write output [" + f.getAbsolutePath() + "]");
		FileUtil.write(f, output);
	}
	
	private static void showUsage(){
		log("java jopencc.Main -c config -i input -o output\n" +
			"\tconfig: zhs2zht - simplified Chinese to traditional Chinese\n" +
			"\t        zht2zhs - traditional Chinese to simplified Chinese");	
	}
}
