import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.CreditEntry;
import model.ListModel;
import model.Video;

public class VideoWindow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2518133095245391812L;
	private Video video;
	private JFrame parent;
	private JTextPane titleText = new JTextPane();
	private JTextField diskFree = new JTextField();
	private JTextField diskTotal = new JTextField();
	private ListModel<CreditEntry> model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoWindow window = new VideoWindow(new JFrame(), "A");
					System.out.print(window.getVideo());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VideoWindow(JFrame parent, String title, Video video) {
		super(parent, title, true);
		this.parent = parent;
		this.video = video;
		copyData();
		initialize();
	}

	private void copyData() {
		video.getTitle();
		this.titleText.setText("A");
		this.model = video.getCredits();
		this.diskFree.setText(video.getDiskFree()+"");
		this.diskTotal.setText(video.getDiskTotal()+"");
	}
	public VideoWindow(JFrame parent, String title) {
		super(parent, title, true);
		this.parent = parent;
		model = new ListModel<CreditEntry>();
		this.video = new Video();
		copyData();
		initialize();
	}
	

	private void initialize() {
		if (parent != null) {
			Dimension parentSize = parent.getSize(); 
		    Point p = parent.getLocation(); 
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

		titleText.setText("Shrek");
		generalPanel.add(titleText);

		JLabel label_1 = new JLabel("Disks");
		generalPanel.add(label_1);
		
		JPanel diskStatus = new JPanel();
		generalPanel.add(diskStatus);
		
		diskStatus.add(diskFree);
		diskFree.setColumns(10);
		
		diskStatus.add(diskTotal);
		diskTotal.setColumns(10);

		JPanel entriesPanel = new JPanel();
		tabbedPane.addTab("Entries", null, entriesPanel, null);
		entriesPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		entriesPanel.add(scrollPane, BorderLayout.NORTH);

		JList<CreditEntry> list = new JList<CreditEntry>();
		list.setModel(model);
		scrollPane.setViewportView(list);

		JPanel panel_3 = new JPanel();
		entriesPanel.add(panel_3, BorderLayout.CENTER);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreditEntryPicker picker = new CreditEntryPicker();
				int result = JOptionPane.showConfirmDialog(null, picker,
						"CreditEntry prompt", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					model.addElement(picker.getCreditEntry());
				}
			}
		});
		panel_3.add(btnAdd);
		JButton btnRemove = new JButton("Remove selected");
		btnRemove.addActionListener(new RemoveAction(list, model));
		panel_3.add(btnRemove);

		JPanel mainBtn = new JPanel();
		getContentPane().add(mainBtn, BorderLayout.SOUTH);
		mainBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				updateVideo();
				}catch(NumberFormatException ex){
					ExceptionDialog.showExceptionDialog(ex);
				};
				dispose();
			}
		});
		mainBtn.add(btnOK);
		
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new CloseAction(this));
		mainBtn.add(btnClose);
		pack();
		setVisible(true);
	}

	protected void updateVideo() {
		video.setTitle(titleText.getText());
		video.setDiskFree(Integer.parseInt(diskFree.getText()));
		video.setDiskTotal(Integer.parseInt(diskTotal.getText()));
		
	}
	public Video getVideo(){
		return this.video;
	};
}
