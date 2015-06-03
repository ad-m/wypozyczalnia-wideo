/* Temat 12: Wypożyczalnia filmów
Program obsługujący wypożyczalnię filmów. Obiektowy opis osób (obsada filmów oraz klienci
wypożyczalni) i filmów oraz możliwość wiązania ze sobą tych informacji (wprowadzanie obsady
filmów, reżysera, scenarzysty itd.). Zapamiętywanie historii wypożyczania dla filmów oraz klientów.
Możliwość przeszukiwania i filtrowania bazy według zadanych kryteriów. */

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Video;
import model.VideoSet;


public class Application {

	private JFrame frame;
	//private OrderSet orderset = new OrderSet();
	//private ClientSet clientset = new ClientSet();
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
		tabbedPane.setBorder(new EmptyBorder(0, 30, 0, 30));
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel client_panel = new JPanel();
		tabbedPane.addTab("Clients", null, client_panel, null);
		
		JPanel order_panel = new JPanel();
		tabbedPane.addTab("Orders", null, order_panel, null);
		
		tabbedPane.addTab("Video", null, makeVideoPanel(), null);
		
		
		
	}

	private JPanel makeVideoPanel() {
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		JList<Video> list = new JList<>();
		list.setModel(videoset);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(list);
		// video_panel.add(list);
		main_panel.add(scrollPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		main_panel.add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VideoWindow vw = new VideoWindow(frame, "Jacobson!");
				videoset.addElement(vw.getVideo());
				
			}
		});
		
		panel.add(btnAdd);
		
		JButton btnLoad = new JButton("Load");
		
		btnLoad.addActionListener(new LoadAction<Video>(frame, videoset));
		
		panel.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		
		btnSave.addActionListener(new SaveAction(frame, videoset));
		
		panel.add(btnSave);
		return main_panel;
	}
}
