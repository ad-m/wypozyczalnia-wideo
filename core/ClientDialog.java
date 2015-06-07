package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;

import actions.CloseAction;
import model.Client;

public class ClientDialog extends JDialog implements WindowObject<Client> {
	private static final long serialVersionUID = -199117375756393413L;
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameText = new JTextField("John");
	private JTextField secondNameText = new JTextField("Smith");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField createdOnText = new JFormattedTextField(df);
	private Client object;
	private JFrame frame;
	private boolean status = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientDialog dialog = new ClientDialog(null, "B");
			System.out.println(dialog.getObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientDialog(JFrame parent, String title, Client client) {
		super(parent, title, true);
		this.frame = parent;
		this.object = client;
		copyData();
		initialize();
	}

	public ClientDialog(JFrame frame, String title) {
		super(frame, title, true);
		this.frame = frame;
		this.object = new Client();
		copyData();
		initialize();
	}

	private void initialize() {
		if (frame != null) {
			Dimension parentSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		// contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel firstNameLabel = new JLabel("First name:");
			contentPanel.add(firstNameLabel);

			contentPanel.add(firstNameText);
			firstNameText.setColumns(10);
		}
		{
			JLabel secondNameLabel = new JLabel("Second name:");
			contentPanel.add(secondNameLabel);

			contentPanel.add(secondNameText);
			secondNameText.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Created on:");
			contentPanel.add(lblNewLabel_1);
			contentPanel.add(createdOnText);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						updateData();
						status = true;
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new CloseAction(this));
				buttonPane.add(cancelButton);
			}
		}
		pack();
		setVisible(true);

	}

	private void copyData() {
		firstNameText.setText(object.getFirstName());
		secondNameText.setText(object.getSecondName());
		createdOnText.setText(df.format(object.getCreatedOn()));
	}

	private void updateData() {
		object.setFirstName(firstNameText.getText());
		object.setSecondName(secondNameText.getText());
		try {
			object.setCreatedOn(df.parse(createdOnText.getText()));
		} catch (ParseException e) {
			ExceptionDialog.showExceptionDialog(frame, e);
		}
	}

	public Client getObject() {
		return this.object;
	}
	
	public boolean getStatus() {
		return this.status;
	}
}