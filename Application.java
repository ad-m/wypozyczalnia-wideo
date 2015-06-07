

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.ClientWindow;
import core.OrderWindow;
import core.SearchPanel;
import core.UtilsPanel;
import core.VideoWindow;
import core.WindowObject;
import actions.LoadAction;
import actions.RemoveAction;
import actions.SaveAction;
import model.Client;
import model.ClientSet;
import model.Order;
import model.OrderSet;
import model.Set;
import model.Video;
import model.VideoSet;

public class Application {

	private JFrame frame;
	private OrderSet orderset = new OrderSet();
	private ClientSet clientset = new ClientSet();
	private VideoSet videoset = new VideoSet();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 5, 0, 5));
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);

		tabbedPane.addTab("Clients", null, makeClientPanel(), null);
		tabbedPane.addTab("Orders", null, makeOrderPanel(), null);
		tabbedPane.addTab("Video", null, makeVideoPanel(), null);
		tabbedPane.addTab("Search", null, new SearchPanel(orderset, videoset,
				clientset), null);
		tabbedPane.addTab("Utils", null, new UtilsPanel(orderset, videoset,
				clientset), null);
		frame.pack();
	}

	private JPanel makeClientPanel() {
		// TODO: Extract to new class
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		JList<Client> list = new JList<>();
		list.setModel(clientset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(list);
		// video_panel.add(list);
		main_panel.add(scrollPane, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		main_panel.add(panel, BorderLayout.SOUTH);

		// Add button
		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow dialog = new ClientWindow(frame, "New client");
				clientset.addElement(dialog.getClient());
			}
		});
		panel.add(btnAdd);

		// Edit button
		JButton btnEdit = new JButton("Edit selected");
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Client selectedObject = list.getSelectedValue();
				if (selectedObject == null) {
					return;
				}
				ClientWindow vw = new ClientWindow(frame, selectedObject
						.getName(), selectedObject);
				if (vw.getStatus()) {
					JOptionPane.showMessageDialog(frame, selectedObject
							+ " saved!");
				}
			}
		});
		panel.add(btnEdit);
		standardButton(list, panel, clientset);
		return main_panel;
	}

	private JPanel makeOrderPanel() {
		// TODO: Extract to new class
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		JList<Order> list = new JList<>();
		list.setModel(orderset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(list);
		// video_panel.add(list);
		main_panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		main_panel.add(panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OrderWindow vw = new OrderWindow(frame, "New order",
						new Order(), clientset, videoset);
				if (vw.getStatus()) {
					orderset.addElement(vw.getObject());
				}

			}
		});
		panel.add(btnAdd);

		JButton btnEdit = new JButton("Edit selected");
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order selectedObject = list.getSelectedValue();
				if (selectedObject == null) {
					return;
				}
				WindowObject<?> vw = new OrderWindow(frame, selectedObject
						.toString(), selectedObject, clientset, videoset);
				if (vw.getStatus()) {
					JOptionPane.showMessageDialog(frame, selectedObject
							+ " saved!");
				}
			}
		});
		panel.add(btnEdit);

		standardButton(list, panel, orderset);

		return main_panel;
	}

	private JPanel makeVideoPanel() {
		// TODO: Extract to new class
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		JList<Video> list = new JList<>();
		list.setModel(videoset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(list);
		// video_panel.add(list);
		main_panel.add(scrollPane, BorderLayout.CENTER);

		JPanel button_panel = new JPanel();
		main_panel.add(button_panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VideoWindow vw = new VideoWindow(frame, "New video");
				if (vw.getStatus()) {
					videoset.addElement(vw.getObject());
				}

			}
		});
		button_panel.add(btnAdd);

		JButton btnEdit = new JButton("Edit selected");
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Video v = list.getSelectedValue();
				if (v == null) {
					return;
				}
				VideoWindow vw = new VideoWindow(frame, v.getTitle(), v);
				if (vw.getStatus()) {
					JOptionPane.showMessageDialog(frame, v + " saved!");
				}
			}
		});
		button_panel.add(btnEdit);

		standardButton(list, button_panel, videoset);
		return main_panel;
	}

	private void standardButton(JList<?> list, JPanel panel, Set<?> set) {
		// Load button
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new LoadAction(frame, set));
		panel.add(btnLoad);

		// Save button
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveAction(frame, set));
		panel.add(btnSave);

		// Remove button
		JButton btnRemove = new JButton("Remove selected");
		btnRemove.addActionListener(new RemoveAction(list, set));
		panel.add(btnRemove);
	}
}
