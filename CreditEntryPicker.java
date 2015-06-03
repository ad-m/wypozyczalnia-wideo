import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.CreditEntry;


public class CreditEntryPicker extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3016498623663729607L;
	private JTextField secondNameText;
	private JTextField roleText;
	private JTextField firstNameText;
	private JLabel firstNameLabel;
	private JLabel secondNameLabel;
	private JLabel roleLabel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreditEntryPicker b = new CreditEntryPicker();
			JOptionPane.showConfirmDialog(null, b ,
		            "Select Something", JOptionPane.OK_CANCEL_OPTION) ;
			System.out.print("Done!");
			System.out.print(b.getCreditEntry());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreditEntry getCreditEntry(){
		return new CreditEntry(firstNameText.getText(), secondNameText.getText(), roleText.getText());
	}

	/**
	 * Create the dialog.
	 */
	public CreditEntryPicker() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		// add(this, BorderLayout.CENTER);
		this.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel firstNamePanel = new JPanel();
			this.add(firstNamePanel);
			firstNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				firstNameLabel = new JLabel("First name");
				firstNamePanel.add(firstNameLabel);
			}
			{
				firstNameText = new JTextField();
				firstNameLabel.setLabelFor(firstNameText);
				firstNamePanel.add(firstNameText);
				firstNameText.setColumns(10);
			}
		}
		{
			JPanel secondNamePanel = new JPanel();
			this.add(secondNamePanel);
			secondNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				secondNameLabel = new JLabel("Second name");
				secondNamePanel.add(secondNameLabel);
			}
			{
				secondNameText = new JTextField();
				secondNameLabel.setLabelFor(secondNameText);
				secondNamePanel.add(secondNameText);
				secondNameText.setColumns(10);
			}
		}
		{
			JPanel rolePanel = new JPanel();
			this.add(rolePanel);
			rolePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				roleLabel = new JLabel("Role");
				rolePanel.add(roleLabel);
			}
			{
				roleText = new JTextField();
				roleLabel.setLabelFor(roleText);
				rolePanel.add(roleText);
				roleText.setColumns(10);
			}
		}
	}
}
