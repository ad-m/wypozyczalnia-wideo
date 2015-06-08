package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;

import model.Set;
import tester.SearchTester;
import core.ResultsetDialog;

public class SearchAction<T> implements ActionListener {
	private JFrame frame;
	private SearchTester<T> tester;
	private Set<T> set;

	public SearchAction(JFrame frame, SearchTester<T> tester, Set<T> set) {
		this.frame = frame;
		this.tester = tester;
		this.set = set;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print("Print orderset");
		Set<T> resultset = new Set<T>();

		for (Iterator<T> iterator = set.iterator(); iterator.hasNext();) {
			T el = iterator.next();
			if (tester.check(el)) {
				resultset.addElement(el);
			}
		}
		new ResultsetDialog<T>(frame, resultset);

	}
}