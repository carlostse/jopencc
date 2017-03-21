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

import static jopencc.gui.Interface.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class AboutDialog {

	private static final Point SHELL_SIZE = new Point(325, 160);

	public AboutDialog(Display display, Image icon) {
		super();
		this.display = display;
		this.icon = icon;
	}

	private Display display;
	private Image icon;

	public void show(){
		Shell aboutShell = new Shell(display, SWT.DIALOG_TRIM);
		aboutShell.setText(PROG_NAME + " " + PROG_VERSION);
		aboutShell.setImage(icon);

		String msg = ABT_AUTHOR + " " + ABT_NAME + " <" + ABT_EMAIL + ">\n\n" + ABT_HOMEPAGE + "<a>" + ABT_URL + "</a>";

		org.eclipse.swt.widgets.Link label = new org.eclipse.swt.widgets.Link(aboutShell, SWT.NONE);
		label.setText(msg);
		label.setBounds(10, 20, 290, 50);

		Button button = new Button(aboutShell, SWT.PUSH);
		button.setText(BTN_OK);
		button.setBounds(SHELL_SIZE.x - 20 - 80, SHELL_SIZE.y - 50- 25, 80, 25);
		button.addListener(SWT.Selection, new aboutCloseListener());

		aboutShell.setSize(SHELL_SIZE); 
		aboutShell.setMinimumSize(SHELL_SIZE);
		aboutShell.open();

		while(!aboutShell.isDisposed())
			if (!display.readAndDispatch()) 
				display.sleep();
	}

	private static class aboutCloseListener implements Listener {
		public void handleEvent(Event event) {
			Button widget = (Button) event.widget;
			widget.getShell().dispose();
		}
	}
}
