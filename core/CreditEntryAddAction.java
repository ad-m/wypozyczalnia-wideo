package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.CreditEntry;

public class CreditEntryAddAction implements ActionListener {
	private JFrame frame;
	private ListModel<CreditEntry> model;

	public CreditEntryAddAction(JFrame frame, ListModel<CreditEntry> model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CreditEntryPicker picker = new CreditEntryPicker();
		int result = JOptionPane.showConfirmDialog(frame, picker,
				"CreditEntry prompt", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			model.addElement(picker.getCreditEntry());
		}
	}
}
