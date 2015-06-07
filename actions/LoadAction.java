package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.ExceptionDialog;
import model.Set;

public class LoadAction implements ActionListener {
	JFrame frame;
	Set<?> set;

	public LoadAction(JFrame frame, Set<?> set) {
		this.frame = frame;
		this.set = set;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		System.out.println("Load action fired!");
		if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectInputStream fs = FileToObjectInputStream(fc
						.getSelectedFile());
				set.fromFile(fs);
			} catch (EOFException e2) {
				// pass silient
			} catch (IOException | ClassNotFoundException e1) {
				ExceptionDialog.showExceptionDialog(e1);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "No file selected.");
		}
	}

	private ObjectInputStream FileToObjectInputStream(File file)
			throws IOException, FileNotFoundException {
		return new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(file)));
	}

}
