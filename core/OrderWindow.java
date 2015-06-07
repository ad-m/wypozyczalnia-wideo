package core;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

import actions.CloseAction;
import actions.RemoveAction;
import model.Client;
import model.ClientSet;
import model.ListModel;
import model.Order;
import model.OrderEntry;
import model.VideoSet;

public class OrderWindow extends JDialog implements WindowObject<Order> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3528564249219924798L;
	private JFrame frame;
	private final JPanel contentPanel = new JPanel();
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField createdOnText = new JFormattedTextField(df);
	private JTextField returnedOnText = new JTextField("");
	private ListModel<OrderEntry> model;
	private ClientSet clientset;
	private VideoSet videoset;
	private JList<Client> clientList = new JList<Client>();
	private JList<OrderEntry> entryList = new JList<OrderEntry>();
	private Order object;
	private boolean status = false;

	public static void main(String[] args) {
		try {
			OrderWindow dialog = new OrderWindow(new JFrame(), "Title");
			System.out.print(dialog.getObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderWindow(JFrame frame, String title) {
		super(frame, title, true);
		this.frame = frame;
		this.object = new Order();
		this.model = new ListModel<OrderEntry>();
		this.clientset = new ClientSet();
		this.videoset = new VideoSet();
		copyData();
		initialize();
	}

	public OrderWindow(JFrame frame, String title, Order object,
			ClientSet clientset, VideoSet videoset) {
		super(frame, title, true);
		this.frame = frame;
		this.object = object;
		this.videoset = videoset;
		this.model = object.getData();
		this.clientset = clientset;
		copyData();
		initialize();
	}

	private void copyData() {
		if (object.getCreatedOn() != null) {
			this.createdOnText.setText(df.format(object.getCreatedOn()));
		}
		;
		if (object.getReturnDate() != null) {
			this.returnedOnText.setText(df.format(object.getReturnDate()));
		}
		;
		this.model = object.getData();
		this.clientList.setSelectedValue(object.getClient(), true);
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, BorderLayout.NORTH);
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("General", null, panel, null);
				panel.setLayout(new GridLayout(0, 2, 0, 0));
				{
					JLabel createdOnLabel = new JLabel("Created on");
					createdOnLabel.setLabelFor(createdOnText);
					panel.add(createdOnLabel);

					panel.add(createdOnText);
				}
				{
					JLabel lblNewLabel = new JLabel("Returned on");
					lblNewLabel.setLabelFor(returnedOnText);
					panel.add(lblNewLabel);

					panel.add(returnedOnText);
				}
			}
			{
				JPanel entriesPanel = new JPanel();
				tabbedPane.addTab("Entries", null, entriesPanel, null);
				entriesPanel.setLayout(new BorderLayout(0, 0));

				JScrollPane scrollPane = new JScrollPane();
				entriesPanel.add(scrollPane, BorderLayout.NORTH);

				entryList.setModel(model);
				scrollPane.setViewportView(entryList);

				JPanel buttonPanel = new JPanel();
				entriesPanel.add(buttonPanel);

				JButton addButton = new JButton("Add");
				addButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.print("Add action fired!");
						OrderEntryWindow vw = new OrderEntryWindow(frame,
								"Smiths", videoset, new OrderEntry());
						if (vw.getStatus()) {
							model.addElement(vw.getObject());
						}

					}
				});
				JButton btnRemove = new JButton("Remove selected");
				btnRemove.addActionListener(new RemoveAction(entryList, model));
				buttonPanel.add(btnRemove);

				buttonPanel.add(addButton);
			}
			{
				JPanel entriesPanel = new JPanel();
				tabbedPane.addTab("Client", null, entriesPanel, null);
				entriesPanel.setLayout(new BorderLayout(0, 0));

				JScrollPane scrollPane = new JScrollPane();
				entriesPanel.add(scrollPane, BorderLayout.NORTH);

				clientList
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				clientList.setModel(clientset);
				scrollPane.setViewportView(clientList);
			}
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
						updateObject();
						status = true;
						dispose();
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new CloseAction(frame));
				buttonPane.add(cancelButton);
			}
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public Order getObject() {
		return this.object;
	}

	@Override
	public boolean getStatus() {
		return this.status;
	}

	private void updateObject() {
		try {
			if (!createdOnText.getText().equals("")) {
				object.setCreatedOn(df.parse(createdOnText.getText()));
			}
			if (!returnedOnText.getText().equals("")) {
				object.setReturndate(df.parse(returnedOnText.getText()));
			}
		} catch (ParseException e) {
			ExceptionDialog.showExceptionDialog(e);
		}
		;
		object.setClient(this.clientList.getSelectedValue());
	}
}
