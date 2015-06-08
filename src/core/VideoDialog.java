package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import model.CreditEntry;
import model.Video;
import actions.CloseAction;
import actions.UpdateObjectAction;

public class VideoDialog extends JDialog implements WindowObject<Video> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2518133095245391812L;
	private Video video;
	private JFrame frame;
	private JTextPane titleText = new JTextPane();
	private JTextField diskFreeText = new JTextField();
	private JTextField diskTotalText = new JTextField();
	private JTextField perDayText = new JTextField();

	private ListModel<CreditEntry> model;
	private boolean status = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoDialog window = new VideoDialog(new JFrame(), "A");
					System.out.print(window.getObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VideoDialog(JFrame parent, String title, Video video) {
		super(parent, title, true);
		this.frame = parent;
		this.video = video;
		copyData();
		initialize();
	}

	public VideoDialog(JFrame parent, String title) {
		super(parent, title, true);
		this.frame = parent;
		model = new ListModel<CreditEntry>();
		this.video = new Video();
		copyData();
		initialize();
	}

	private void initialize() {
		if (frame != null) {
			Dimension parentSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);

		JPanel generalPanel = new JPanel();
		generalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("General", null, generalPanel, null);
		generalPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel label = new JLabel("Title");
		generalPanel.add(label);

		generalPanel.add(titleText);

		JLabel label2 = new JLabel("Price per day");
		generalPanel.add(label2);

		generalPanel.add(perDayText);

		JLabel label_1 = new JLabel("Disks");
		generalPanel.add(label_1);

		JPanel diskStatus = new JPanel();
		generalPanel.add(diskStatus);

		diskStatus.add(diskFreeText);
		diskFreeText.setColumns(10);

		diskStatus.add(diskTotalText);
		diskTotalText.setColumns(10);

		tabbedPane.addTab("Entries", null, new VideoEntryPanel(frame, model),
				null);

		JPanel mainBtn = new JPanel();
		getContentPane().add(mainBtn, BorderLayout.SOUTH);
		mainBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new UpdateObjectAction(frame, this));
		mainBtn.add(btnOK);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new CloseAction(this));
		mainBtn.add(btnClose);
		pack();
		setVisible(true);
	}

	private void copyData() {
		this.titleText.setText(video.getTitle());
		this.model = video.getCredits();
		this.diskFreeText.setText(video.getDiskFree() + "");
		this.diskTotalText.setText(video.getDiskTotal() + "");
		this.perDayText.setText(video.getPerDay().toString());
	}

	public void updateObject() {
		video.setTitle(titleText.getText());
		video.setDiskFree(Integer.parseInt(diskFreeText.getText()));
		video.setDiskTotal(Integer.parseInt(diskTotalText.getText()));
		video.setPerDay(new BigDecimal(perDayText.getText()));

	}

	public Video getObject() {
		return this.video;
	};

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean s) {
		this.status = s;
	}
}
