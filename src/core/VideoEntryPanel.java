package core;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CreditEntry;
import actions.RemoveAction;

public class VideoEntryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7992163861580456895L;
	private JFrame frame;
	private ListModel<CreditEntry> model;

	public VideoEntryPanel(JFrame frame, ListModel<CreditEntry> model) {
		this.frame = frame;
		this.model = model;
		initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.NORTH);

		JList<CreditEntry> list = new JList<CreditEntry>();
		list.setModel(model);
		scrollPane.setViewportView(list);

		JPanel panel_3 = new JPanel();
		this.add(panel_3, BorderLayout.CENTER);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new CreditEntryAddAction(frame, model));
		panel_3.add(btnAdd);
		JButton btnRemove = new JButton("Remove selected");
		btnRemove.addActionListener(new RemoveAction(frame, list, model));
		panel_3.add(btnRemove);
	}
}
