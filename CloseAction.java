import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CloseAction implements ActionListener {
	private Window frame;

	public CloseAction(Window frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
	}
}
