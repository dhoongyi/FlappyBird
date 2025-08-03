package FlappyBird;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver frame = new GameOver();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameOver() {
		setTitle("Flappy Bird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/gameover.png"));
		label.setBounds(57, 99, 236, 96);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/gameoverscorebox.png"));
		label_1.setBounds(32, 194, 286, 161);
		contentPane.add(label_1);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/leaderboard.png"));
		button.setBounds(202, 384, 116, 44);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChooseGameMode chgamemode = new ChooseGameMode();
				chgamemode.setVisible(true);
				setVisible(false);
			}
		});
		button_1.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/restart.png"));
		button_1.setBounds(39, 384, 116, 44);
		contentPane.add(button_1);
		
		
		JLabel bgmenu = new JLabel("");
		bgmenu.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/background-menu.png"));
		bgmenu.setBounds(0, -75, 350, 675);
		contentPane.add(bgmenu);

	}
}
