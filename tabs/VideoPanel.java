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

import model.Video;
import model.VideoSet;
import core.VideoDialog;

public class VideoPanel extends AbstractSetPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8332624443966015459L;
	private JFrame frame;
	private VideoSet videoset;

	public VideoPanel(JFrame frame, VideoSet videoset) {
		this.frame = frame;
		this.videoset = videoset;
		initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		JList<Video> list = new JList<>();
		list.setModel(videoset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(list);
		// video_panel.add(list);
		this.add(scrollPane, BorderLayout.CENTER);

		JPanel button_panel = new JPanel();
		this.add(button_panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VideoDialog vw = new VideoDialog(frame, "New video");
				if (vw.getStatus()) {
					videoset.addElement(vw.getObject());
				}

			}
		});
		button_panel.add(btnAdd);

		JButton btnEdit = new JButton("Edit selected");
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Video v = list.getSelectedValue();
				if (v == null) {
					return;
				}
				VideoDialog vw = new VideoDialog(frame, v.getTitle(), v);
				if (vw.getStatus()) {
					JOptionPane.showMessageDialog(frame, v + " saved!");
				}
			}
		});
		button_panel.add(btnEdit);

		standardButton(frame, list, button_panel, videoset);
	}
}
