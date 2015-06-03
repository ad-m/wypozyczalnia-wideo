import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import model.ListModel;


final class RemoveAction implements ActionListener {
	private JList<?> list;
	private ListModel<?> model;
	
	public RemoveAction(JList<?> list, ListModel<?> model) {
		super();
		this.list = list;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = list.getSelectedIndex();
		if(index != -1){
			model.remove(index);
		}
	}
}