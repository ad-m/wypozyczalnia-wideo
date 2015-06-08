package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import core.WindowObject;

public class UpdateObjectListAction<T> implements ActionListener {
	private JFrame frame;
	private WindowObject<T> dialog;
	private JList<?> list;

	public UpdateObjectListAction(JFrame frame, WindowObject<T> dialog,
			JList<?> list) {
		super();
		this.frame = frame;
		this.dialog = dialog;
		this.list = list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (list.getSelectedValue() != null) {
			new UpdateObjectAction(frame, dialog).actionPerformed(e);
		} else {
			JOptionPane.showMessageDialog(frame, "No object selected in list.");
		}
	}
}
