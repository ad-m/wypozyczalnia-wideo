package core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import actions.CloseAction;
import model.Client;
import model.ClientSet;

public class ResultsetDialog<T> extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6268145830414515607L;
	private final JPanel contentPanel = new JPanel();
	// private JFrame frame;
	private ListModel<T> resultset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new ResultsetDialog<Client>(new JFrame(), new ClientSet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResultsetDialog(JFrame frame, ListModel<T> resultset) {
		super(frame, false);
		// this.frame = frame;
		this.resultset = resultset;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scrollPane = new JScrollPane();
		JList<T> list = new JList<>();
		list.setModel(resultset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(list);
		// video_panel.add(list);
		contentPanel.add(scrollPane, BorderLayout.SOUTH);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.setActionCommand("Close");
				buttonPane.add(okButton);
				okButton.addActionListener(new CloseAction(this));
				getRootPane().setDefaultButton(okButton);

			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		pack();
	}

}
