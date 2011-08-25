package jopencc.gui;

import static jopencc.gui.Interface.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainWindow {

	private static final Point SHELL_SIZE = new Point(550, 180);
	
	public MainWindow(){
		display = new Display();
		shell = new Shell(display);
		icon = new Image(display, "icon.png");
		initInterface();
	}
	
	private Display display;
	private Shell shell;
	private Image icon;
	
	public void run(){
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();

			shell.isDisposed();
		}
	}
	
	private void initInterface() {
		int i = 0;
		shell.setSize(SHELL_SIZE);
		shell.setMinimumSize(SHELL_SIZE);
		shell.setText(TXT[i++] + " " + TXT[i++]);
		shell.setImage(icon);
	
		shell.open();
	}
}
