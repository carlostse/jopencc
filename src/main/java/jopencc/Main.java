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
import jopencc.util.FileUtil;
import java.io.File;
import static jopencc.util.Dict.*;
import static jopencc.util.Util.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            log("starting gui...");
            new jopencc.gui.MainWindow().run();
            return;
        }

        if (args.length < 6) {
            showUsage();
            return;
        }

        String[] parm = new String[3];
        int i = 0;
        for (String s : args) {
            i++;
            if ("-c".equals(s))
                parm[0] = args[i];

            if ("-i".equals(s))
                parm[1] = args[i];

            if ("-o".equals(s))
                parm[2] = args[i];
        }

        if (isMissing(parm[0]) || isMissing(parm[1]) || isMissing(parm[2])) {
            showUsage();
            return;
        }

        // read the source
        File srcFile = new File(parm[1]);
        if (!FileUtil.isReadableFile(srcFile)) {
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

    private static void showUsage() {
        log("java jopencc.Main -c config -i input -o output\n" +
                "\tconfig: " + ZHS_TO_ZHT + " - simplified Chinese to traditional Chinese\n" +
                "\t        " + ZHT_TO_ZHS + " - traditional Chinese to simplified Chinese");
    }
}
