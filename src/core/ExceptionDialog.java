package core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import actions.CloseAction;

public class ExceptionDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2013533529093896863L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void showExceptionDialog(JFrame frame, Throwable ex) {
		new ExceptionDialog(new JFrame(), ex);
	}

	/**
	 * Create the dialog.
	 */
	public ExceptionDialog(JFrame frame, Throwable ex) {
		super(frame, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblsomeErrorsOccurs = new JLabel(
					"Some errors occurs. Sorry for that. Here is log. Send it to author!");
			contentPanel.add(lblsomeErrorsOccurs, BorderLayout.NORTH);
		}
		{
			JTextArea textArea = new JTextArea();
			JScrollPane scroll = new JScrollPane(textArea);
			textArea.setText(getStackTraceAsString(ex));
			contentPanel.add(scroll);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new CloseAction(this));
				getRootPane().setDefaultButton(okButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private String getStackTraceAsString(Throwable exception) {
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		exception.printStackTrace(printWriter);
		return result.toString();
	}
}
