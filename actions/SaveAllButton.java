package actions;
import javax.swing.JButton;

import model.ClientSet;
import model.OrderSet;
import model.VideoSet;

public class SaveAllButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4178728176429273391L;
	private ClientSet clientset;
	private VideoSet videoset;
	private OrderSet orderset;

	public SaveAllButton(String text, OrderSet orderset, VideoSet videoset,
			ClientSet clientset) {
		super(text);
		this.orderset = orderset;
		this.clientset = clientset;
		this.videoset = videoset;
		initialize();
	}

	private void initialize() {
		addActionListener(new SaveAllAction(orderset, videoset, clientset));
	}

}
