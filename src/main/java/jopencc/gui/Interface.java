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

package jopencc.gui;

import java.util.Locale;
import java.util.ResourceBundle;

public class Interface {

    public static final String
            PROG_NAME, PROG_VERSION,
            BTN_TO_ZHS, BTN_TO_ZHT, BTN_CONVERT, BTN_OK,
            ABT_AUTHOR, ABT_NAME, ABT_EMAIL, ABT_HOMEPAGE, ABT_URL,
            MENU_FILE, MENU_OPEN, MENU_SAVE, MENU_CONVERT, MENU_EXIT, MENU_HELP, MENU_ABOUT;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("interface", Locale.getDefault());
        PROG_NAME = rb.getString("prog.name");
        PROG_VERSION = rb.getString("prog.version");
        BTN_TO_ZHS = rb.getString("btn.toZhs");
        BTN_TO_ZHT = rb.getString("btn.toZht");
        BTN_OK = rb.getString("btn.ok");
        BTN_CONVERT = rb.getString("btn.convert");
        ABT_AUTHOR = rb.getString("abt.author");
        ABT_NAME = rb.getString("abt.name");
        ABT_EMAIL = rb.getString("abt.email");
        ABT_HOMEPAGE = rb.getString("abt.homepage");
        ABT_URL = rb.getString("abt.url");
        MENU_FILE = rb.getString("menu.file");
        MENU_OPEN = rb.getString("menu.open");
        MENU_SAVE = rb.getString("menu.save");
        MENU_CONVERT = rb.getString("menu.convert");
        MENU_EXIT = rb.getString("menu.exit");
        MENU_HELP = rb.getString("menu.help");
        MENU_ABOUT = rb.getString("menu.about");
    }
}
