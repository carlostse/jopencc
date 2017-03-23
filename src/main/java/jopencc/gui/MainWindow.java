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

import jopencc.util.Dict;
import jopencc.util.FileUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.io.File;
import static jopencc.gui.Interface.*;
import static jopencc.util.Util.*;

public class MainWindow {

    private static final Point SHELL_SIZE = new Point(550, 250);

    public MainWindow() {
        // Initialize interface
        display = new Display();
        shell = new Shell(display);
        icon = new Image(display, "icon.png");
        buttons = new Button[2];
        initInterface();
        // Initialize dictionary
        zhtTozhs = new Dict(Dict.ZHT_TO_ZHS);
        zhsToZht = new Dict(Dict.ZHS_TO_ZHT);
    }

    private Display display;
    private Shell shell;
    private Image icon;
    private Button buttons[];
    private Text text;
    private Dict zhtTozhs, zhsToZht; // don't set dictionary to null

    public void run() {
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();

            shell.isDisposed();
        }
    }

    private void initInterface() {
        shell.setSize(SHELL_SIZE);
        shell.setMinimumSize(SHELL_SIZE);
        shell.setText(PROG_NAME + " " + PROG_VERSION);
        shell.setImage(icon);
        shell.setLayout(new GridLayout(3, false));
        shell.setMenuBar(getMenuBar());

        // fill grid data
        GridData fillGridData = new GridData();
        fillGridData.horizontalAlignment = GridData.FILL;
        fillGridData.grabExcessHorizontalSpace = true;

        // left grid data
        GridData leftGridData = new GridData();
        leftGridData.horizontalAlignment = SWT.LEFT;

        // left grid data
        GridData txtGridData = new GridData();
        txtGridData.horizontalSpan = 3;
        txtGridData.horizontalAlignment = GridData.FILL;
        txtGridData.verticalAlignment = GridData.FILL;
        txtGridData.grabExcessHorizontalSpace = true;
        txtGridData.grabExcessVerticalSpace = true;

        // -- 1st row ---
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(shell, SWT.RADIO); // radio button
            buttons[i].setText(i == 0 ? BTN_TO_ZHS : BTN_TO_ZHT);
            buttons[i].setLayoutData(leftGridData);
            if (i == 0)
                buttons[i].setSelection(true);
        }

        Button convBtn = new Button(shell, SWT.PUSH | SWT.CENTER); // push button
        convBtn.setData("convert");
        convBtn.setText(BTN_CONVERT);
        convBtn.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                switch (e.type) {
                    case SWT.Selection:
                        convert();
                        break;
                }
            }
        });
        convBtn.setLayoutData(fillGridData);

        // -- 2nd row ---
        text = new Text(shell, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
        text.setLayoutData(txtGridData);

        shell.open();
    }

    private Menu getMenuBar() {
        final Menu menuBar = new Menu(shell, SWT.BAR),
                fileMenu = new Menu(shell, SWT.DROP_DOWN),
                helpMenu = new Menu(shell, SWT.DROP_DOWN);

        // --- file ---
        final MenuItem file = new MenuItem(menuBar, SWT.CASCADE);
        file.setText(MENU_FILE);
        file.setMenu(fileMenu);

        final MenuItem openItem = new MenuItem(fileMenu, SWT.PUSH);
        openItem.setText(MENU_OPEN);
        openItem.setAccelerator(SWT.CTRL + 'O');
        openItem.addSelectionListener(new Open());

        final MenuItem saveItem = new MenuItem(fileMenu, SWT.PUSH);
        saveItem.setText(MENU_SAVE);
        saveItem.setAccelerator(SWT.CTRL + 'S');
        saveItem.addSelectionListener(new Save());

        new MenuItem(fileMenu, SWT.SEPARATOR);

        final MenuItem convItem = new MenuItem(fileMenu, SWT.PUSH);
        convItem.setText(MENU_CONVERT);
        convItem.setAccelerator(SWT.CTRL + 'E');
        convItem.addSelectionListener(new Convert());

        new MenuItem(fileMenu, SWT.SEPARATOR);

        final MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText(MENU_EXIT);
        exitItem.setAccelerator(SWT.CTRL + 'Q');
        exitItem.addSelectionListener(new Exit());

        // --- edit ---


        // --- help ---
        final MenuItem help = new MenuItem(menuBar, SWT.CASCADE);
        help.setText(MENU_HELP);
        help.setMenu(helpMenu);

        final MenuItem aboutItem = new MenuItem(helpMenu, SWT.PUSH);
        aboutItem.setText(MENU_ABOUT);
        aboutItem.addSelectionListener(new About());

        return menuBar;
    }

    private void convert() {
        if (isMissing(text.getText()))
            return;

        Dict dict = buttons[0].getSelection() ? zhtTozhs : zhsToZht;
        dict.setSrc(text.getText());
        dict.convert();
        String s = dict.getResult();
        text.setText(s);
    }

    // --- handler ---
    private class Open implements SelectionListener {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
            FileDialog fd = new FileDialog(shell, SWT.OPEN);
            fd.setText("Open");
            fd.setFilterPath("C:/");
            fd.setFilterExtensions(new String[]{"*.txt", "*.html", "*.htm", "*.log", "*.*"});
            File file = new File(fd.open());
            log("src: " + file.getAbsolutePath());

            if (FileUtil.isReadableFile(file))
                text.setText(FileUtil.readSrc(file).toString());
        }
    }

    private class Save implements SelectionListener {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
            FileDialog fd = new FileDialog(shell, SWT.SAVE);
            fd.setText("Save");
            fd.setFilterPath("C:/");
            fd.setFilterExtensions(new String[]{"*.txt"});
            File file = new File(fd.open());
            log("out: " + file.getAbsolutePath());

            if (FileUtil.isWritableFile(file))
                FileUtil.write(file, text.getText());
        }
    }

    private class Convert implements SelectionListener {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
            convert();
        }
    }

    private class Exit implements SelectionListener {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
            log("Exit");
            shell.dispose();
        }
    }

    private class About implements SelectionListener {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
            AboutDialog aboutDialog = new AboutDialog(display, icon);
            aboutDialog.show();
        }
    }
}
