package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import core.ExceptionDialog;
import model.Set;

public class SaveAction implements ActionListener {
	JFrame frame;
	Set<?> set;

	public SaveAction(JFrame frame, Set<?> set) {
		this.frame = frame;
		this.set = set;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		System.out.println("Save action fired");
		if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectOutputStream fs = FileToObjectOutputStream(fc
						.getSelectedFile());
				set.toFile(fs);
				fs.close();
			} catch (IOException e1) {
				ExceptionDialog.showExceptionDialog(frame, e1);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "No file selected.");
		}
	}

	private ObjectOutputStream FileToObjectOutputStream(File file)
			throws FileNotFoundException, IOException {
		return new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
	}
}
