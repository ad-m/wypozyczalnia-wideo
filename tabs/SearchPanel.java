package tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import core.ResultsetDialog;
import model.Client;
import model.ClientSet;
import model.Order;
import model.OrderSet;
import model.Video;
import model.VideoSet;

public class SearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 852987520982161648L;
	private ClientSet clientset;
	private VideoSet videoset;
	private OrderSet orderset;
	private JFrame frame;

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setBorder(new EmptyBorder(0, 5, 0, 5));
		p.add(new SearchPanel(new JFrame(), new OrderSet(), new VideoSet(), new ClientSet()));
		f.add(p, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}

	/**
	 * Create the panel.
	 */
	public SearchPanel(JFrame frame, OrderSet orderset, VideoSet videoset, ClientSet clientset) {
		this.frame = frame;
		this.orderset = orderset;
		this.clientset = clientset;
		this.videoset = videoset;
		initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));
		JPanel selector_panel = new JPanel();
		selector_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(selector_panel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		JList<Client> clientList = new JList<>();
		clientList.setModel(clientset);
		clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(clientList);
		// video_panel.add(list);
		selector_panel.add(scrollPane, BorderLayout.SOUTH);

		JScrollPane scrollPane2 = new JScrollPane();
		JList<Video> videoList = new JList<>();
		videoList.setModel(videoset);
		videoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane2.setViewportView(videoList);
		// video_panel.add(list);
		selector_panel.add(scrollPane2, BorderLayout.NORTH);

		JPanel button_panel = new JPanel();
		add(button_panel);
		JButton btnSearch = new JButton("Search");

		btnSearch.addActionListener(new ActionListener() {

			private boolean check(Order el) {
				if (clientList.getSelectedValue() != null
						&& !clientList.getSelectedValue()
								.equals(el.getClient())) {
					return false;
				}
				if (videoList.getSelectedValue() != null
						&& !el.hasVideo(videoList.getSelectedValue())) {
					return false;
				}
				return true;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("Print orderset");
				OrderSet resultset = new OrderSet();

				for (Iterator<Order> iterator = orderset.iterator(); iterator
						.hasNext();) {
					Order el = iterator.next();
					if (check(el)) {
						resultset.addElement(el);
					}
				}
				new ResultsetDialog<Order>(frame, resultset);

			}
		});
		button_panel.add(btnSearch);
	}

}
