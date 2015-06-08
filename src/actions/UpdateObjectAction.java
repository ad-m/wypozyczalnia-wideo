package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import core.ExceptionDialog;
import core.WindowObject;

public class UpdateObjectAction implements ActionListener {
	JFrame frame;
	WindowObject<?> dialog;

	public UpdateObjectAction(JFrame frame, WindowObject<?> dialog) {
		this.frame = frame;
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			dialog.updateObject();
		} catch (NumberFormatException ex) {
			ExceptionDialog.showExceptionDialog(frame, ex);
		}
		dialog.setStatus(true);
		dialog.dispose();
	}
}
