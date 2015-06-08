package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import core.ListModel;
import core.OrderEntryDialog;
import model.OrderEntry;
import model.VideoSet;

public class AddOrderEntryAction implements ActionListener {
	private JFrame frame;
	private VideoSet videoset;
	private ListModel<OrderEntry> model;

	public AddOrderEntryAction(JFrame frame, VideoSet videoset,
			ListModel<OrderEntry> model) {
		super();
		this.frame = frame;
		this.videoset = videoset;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print("Add action fired!");
		OrderEntryDialog vw = new OrderEntryDialog(frame, "Order entry",
				videoset, new OrderEntry());
		if (vw.getStatus()) {
			model.addElement(vw.getObject());
		}

	}
}
