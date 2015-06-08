package tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.SaveAllAction;
import model.ClientSet;
import model.OrderSet;
import model.VideoSet;

public class UtilsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256390103463332246L;
	private ClientSet clientset;
	private VideoSet videoset;
	private OrderSet orderset;
	private JFrame frame;

	/**
	 * Create the panel.
	 */

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setBorder(new EmptyBorder(0, 5, 0, 5));
		p.add(new UtilsPanel(new JFrame(), new OrderSet(), new VideoSet(),
				new ClientSet()));
		f.add(p, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}

	public UtilsPanel(JFrame frame, OrderSet orderset, VideoSet videoset,
			ClientSet clientset) {
		this.frame = frame;
		this.orderset = orderset;
		this.clientset = clientset;
		this.videoset = videoset;
		initialize();

	}

	private void initialize() {

		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton btnSaveAll = new JButton("Save all");
		btnSaveAll.addActionListener(new SaveAllAction(frame, orderset,
				videoset, clientset));
		add(btnSaveAll);

	}

}
