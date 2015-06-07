package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import core.ExceptionDialog;
import model.ClientSet;
import model.OrderSet;
import model.VideoSet;


final class SaveAllAction implements ActionListener {
	private ClientSet clientset;
	private VideoSet videoset;
	private OrderSet orderset;

	SaveAllAction( OrderSet orderset, VideoSet videoset,
			ClientSet clientset){
		this.orderset = orderset;
		this.clientset = clientset;
		this.videoset = videoset;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try (ObjectOutputStream fos = FileToObjectOutputStream(new File(
					chooser.getSelectedFile(), "orderset.bin"));
					ObjectOutputStream fvs = FileToObjectOutputStream(new File(
							chooser.getSelectedFile(), "videoset.bin"));
					ObjectOutputStream fcs = FileToObjectOutputStream(new File(
							chooser.getSelectedFile(), "clientset.bin"))) {
				orderset.toFile(fos);
				videoset.toFile(fvs);
				clientset.toFile(fcs);
				JOptionPane.showMessageDialog(null, orderset.size()
						+ " orders, " + videoset.size() + " videos, "
						+ clientset.size() + " clients saved !");
			} catch (IOException e1) {
				ExceptionDialog.showExceptionDialog(e1);
			}
		}
	}

	private ObjectOutputStream FileToObjectOutputStream(File file)
			throws FileNotFoundException, IOException {
		return new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
	}
}