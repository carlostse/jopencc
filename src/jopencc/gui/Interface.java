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
