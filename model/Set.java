package model;
import java.io.*;
import java.util.Iterator;


public class Set<T> extends ListModel<T> implements Iterable<T> {
	private static final long serialVersionUID = -7097934167103878681L;
	public int size() {
		return data.size();
	}

	public Iterator<T> iterator() {
		return data.iterator();
	}

	public void clear() {
		data.clear();
	}

	public T get(int index) {
		return data.get(index);
	}


	public boolean add(T e) {
		return data.add(e);
	}

	@Override
	public String toString() {
		return "Set [data=" + data + "]";
	}
	
	@SuppressWarnings("unchecked")
	public void fromFile(ObjectInputStream input) throws IOException, ClassNotFoundException {
		try {
				Object el;
				while ((el = input.readObject()) != null) {
					this.addElement((T) el);
				}
		} catch (EOFException e) {

		}
	};
	public void toFile(ObjectOutputStream output) throws IOException{
		for(T el: this.data){
			System.out.println(el);
			output.writeObject(el);
		}
	}
}
