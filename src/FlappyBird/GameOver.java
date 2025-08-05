package FlappyBird;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
					GameOver frame = new GameOver(0);
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
	public GameOver(int score) {
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
		label.setIcon(new ImageIcon(getClass().getResource("/assets/gameover.png")));
		label.setBounds(57, 99, 236, 96);
		contentPane.add(label);
		
		JLabel scoreLabel = new JLabel("" +score);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(236, 242, 51, 32); 
		scoreLabel.setFont(new Font("Tiny5", Font.BOLD, 20)); 
		contentPane.add(scoreLabel);
		
		String username = AuthService.getUserName(LoggedUserInfo.getInstance().getUserEmail());	 // get username in here
		JLabel usernameLabel = new JLabel("" +username);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(73, 265, 51, 32); 
		usernameLabel.setFont(new Font("Tiny5", Font.BOLD, 20)); 
		contentPane.add(usernameLabel);
		
		String bestscore = null;	 // get bestscore in here
		JLabel bestscoreLabel = new JLabel("" +bestscore);
		bestscoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bestscoreLabel.setBounds(223, 295, 64, 32); 
		bestscoreLabel.setFont(new Font("Tiny5", Font.BOLD, 20)); 
		contentPane.add(bestscoreLabel);
				
				
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(getClass().getResource("/assets/leaderboard.png")));
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
		button_1.setIcon(new ImageIcon(getClass().getResource("/assets/restart.png")));
		button_1.setBounds(39, 384, 116, 44);
		contentPane.add(button_1);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(getClass().getResource("/assets/gameoverscorebox.png")));
		label_1.setBounds(32, 194, 286, 161);
		contentPane.add(label_1);
		
		
		JLabel bgmenu = new JLabel("");
		bgmenu.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		bgmenu.setBounds(0, -75, 350, 675);
		contentPane.add(bgmenu);

	}
}
