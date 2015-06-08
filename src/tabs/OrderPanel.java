package tabs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import core.OrderDialog;
import core.WindowObject;
import model.ClientSet;
import model.Order;
import model.OrderSet;
import model.VideoSet;

public class OrderPanel extends AbstractSetPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736908583005642990L;
	public JFrame frame;
	public OrderSet orderset;
	public ClientSet clientset;
	public VideoSet videoset;

	public OrderPanel(JFrame frame, OrderSet orderset, ClientSet clientset,
			VideoSet videoset) {
		this.frame = frame;
		this.orderset = orderset;
		this.clientset = clientset;
		this.videoset = videoset;
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		JList<Order> list = new JList<>();
		list.setModel(orderset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(list);
		// video_panel.add(list);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OrderDialog vw = new OrderDialog(frame, "New order",
						new Order(), clientset, videoset);
				if (vw.getStatus()) {
					orderset.addElement(vw.getObject());
				}

			}
		});
		panel.add(btnAdd);

		JButton btnEdit = new JButton("Edit selected");
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order selectedObject = list.getSelectedValue();
				if (selectedObject == null) {
					return;
				}
				WindowObject<?> vw = new OrderDialog(frame, selectedObject
						.toString(), selectedObject, clientset, videoset);
				if (vw.getStatus()) {
					JOptionPane.showMessageDialog(frame, selectedObject
							+ " saved!");
				}
			}
		});
		panel.add(btnEdit);

		super.standardButton(frame, list, panel, orderset);
	}
}