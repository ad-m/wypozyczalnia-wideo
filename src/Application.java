import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import tabs.ClientPanel;
import tabs.OrderPanel;
import tabs.SearchOrderPanel;
import tabs.UtilsPanel;
import tabs.VideoPanel;
import model.ClientSet;
import model.OrderSet;
import model.VideoSet;

public class Application {

	public OrderSet orderset = new OrderSet();
	public ClientSet clientset = new ClientSet();
	public VideoSet videoset = new VideoSet();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Application();
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
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane()
				.add(new JLabel(
						"<html><body style='width: 450px'>"
								+ "<strong>Temat 12: Wypożyczalnia filmów<strong>"
								+ "<p>Program obsługujący wypożyczalnię filmów. Obiektowy opis osób (obsada filmów oraz klienci wypożyczalni)"
								+ "i filmów oraz możliwość wiązania ze sobą tych informacji (wprowadzanie obsady filmów, reżysera, scenarzysty "
								+ "itd.). Zapamiętywanie historii wypożyczania dla filmów oraz klientów. Możliwość przeszukiwania i "
								+ "filtrowania bazy według zadanych kryteriów.</p>"
								+ "</body></html>"));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 5, 0, 5));

		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);

		tabbedPane.addTab("Clients", new ClientPanel(frame, clientset));
		tabbedPane.addTab("Video", new VideoPanel(frame, videoset));
		tabbedPane.addTab("Orders", new OrderPanel(frame, orderset, clientset,
				videoset));
		tabbedPane.addTab("Search", new SearchOrderPanel(frame, orderset,
				videoset, clientset));
		tabbedPane.addTab("Utils", new UtilsPanel(frame, orderset, videoset,
				clientset));
		frame.pack();
		frame.setVisible(true);
	}

}
