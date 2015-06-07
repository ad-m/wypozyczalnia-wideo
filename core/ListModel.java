package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

public class ListModel<T> extends AbstractListModel<T> implements Iterable<T> {
	private static final long serialVersionUID = 7094310649646444688L;
	protected List<T> data = new ArrayList<T>();

	public ListModel() {
		super();
	}

	@Override
	public int getSize() {
		return this.data.size();
	}

	@Override
	public T getElementAt(int index) {
		return this.data.get(index);
	}

	public void addElement(T obj) {
		data.add(obj);
		fireIntervalAdded(this, data.size() - 1, data.size() - 1);
	}

	public void remove(int index) {
		data.remove(index);
		fireIntervalRemoved(this, index, index);
	}

	public Iterator<T> iterator() {
		return data.iterator();
	}

}