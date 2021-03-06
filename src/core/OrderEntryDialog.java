package core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.OrderEntry;
import model.Video;
import model.VideoSet;
import actions.CloseAction;
import actions.UpdateObjectListAction;

public class OrderEntryDialog extends JDialog implements
		WindowObject<OrderEntry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3791693029786666584L;
	private final JPanel contentPanel = new JPanel();
	private JList<Video> videoList = new JList<Video>();
	private OrderEntry object;
	private boolean status = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrderEntryDialog dialog = new OrderEntryDialog(new JFrame(),
					"Title", new VideoSet(), new OrderEntry());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderEntryDialog(JFrame frame, String title, VideoSet videoset,
			OrderEntry object) {
		super(frame, title, true);
		this.object = object;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel entriesPanel = new JPanel();
			contentPanel.add(entriesPanel);
			entriesPanel.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			entriesPanel.add(scrollPane, BorderLayout.NORTH);

			videoList.setModel(videoset);
			scrollPane.setViewportView(videoList);

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new UpdateObjectListAction<OrderEntry>(
						frame, this, videoList));
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new CloseAction(frame));
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public OrderEntry getObject() {
		return this.object;
	}

	@Override
	public boolean getStatus() {
		return this.status;
	}

	public void updateObject() {
		object.setVideo(this.videoList.getSelectedValue());
	}

	public void setStatus(boolean s) {
		this.status = s;
	}
}
