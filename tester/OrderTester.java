package tester;

import javax.swing.JList;

import model.Client;
import model.Order;
import model.Video;

public class OrderTester implements SearchTester<Order> {
	private JList<Client> clientList;
	private JList<Video> videoList;

	public OrderTester(JList<Client> clientList, JList<Video> videoList) {
		super();
		this.clientList = clientList;
		this.videoList = videoList;
	}

	public boolean check(Order el) {
		if (clientList.getSelectedValue() != null
				&& !clientList.getSelectedValue().equals(el.getClient())) {
			return false;
		}
		if (videoList.getSelectedValue() != null
				&& !el.hasVideo(videoList.getSelectedValue())) {
			return false;
		}
		return true;
	};
}
