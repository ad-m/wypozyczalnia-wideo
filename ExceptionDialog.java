import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class ExceptionDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2013533529093896863L;
	private JDialog dialog;
	private final JPanel contentPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void showExceptionDialog(Throwable ex){
		ExceptionDialog dialog = new ExceptionDialog(ex);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	/**
	 * Create the dialog.
	 */
	public ExceptionDialog(Throwable ex) {
		this.dialog = this;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextArea textArea = new JTextArea();
			JScrollPane scroll = new JScrollPane(textArea);
			textArea.setText(getStackTraceAsString(ex));
			contentPanel.add(scroll);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
						
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	private String getStackTraceAsString(Throwable exception) {
		  Writer result = new StringWriter();
		  PrintWriter printWriter = new PrintWriter(result);
		  exception.printStackTrace(printWriter);
		  return result.toString();
		 }
}
