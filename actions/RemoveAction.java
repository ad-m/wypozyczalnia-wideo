package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import model.ListModel;

public final class RemoveAction implements ActionListener {
	private JList<?> list;
	private ListModel<?> model;

	public RemoveAction(JList<?> list, ListModel<?> model) {
		super();
		this.list = list;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = list.getSelectedValue().toString();
		int index = list.getSelectedIndex();
		if (index != -1) {
			model.remove(index);
		}
		JOptionPane.showMessageDialog(null, text + " removed!");
	}
}