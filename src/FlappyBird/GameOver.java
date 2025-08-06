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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		String username = LoggedUserInfo.getInstance().getUserName();	 // get username in here
		JLabel usernameLabel = new JLabel("" +username);
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setBounds(73, 265, 100, 32); 
		usernameLabel.setFont(new Font("Tiny5", Font.BOLD, 13)); 
		contentPane.add(usernameLabel);
		
		
		int bestscore = getScoreForCurrentPlayer(LoggedUserInfo.getInstance().getUserId());	 // get bestscore in here
		JLabel bestscoreLabel = new JLabel("" +bestscore);
		bestscoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bestscoreLabel.setBounds(223, 295, 64, 32); 
		bestscoreLabel.setFont(new Font("Tiny5", Font.BOLD, 20)); 
		contentPane.add(bestscoreLabel);
				
				
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaderBoard leaderBoard = new LeaderBoard();
				leaderBoard.setVisible(true);
				setVisible(false);
			}
		});
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
	
	public int getScoreForCurrentPlayer(int currentPlayerId) {
	    String selectQuery = "SELECT score FROM scores WHERE user_id = ?";
	    int playerScore = 0;  

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

	        stmt.setInt(1, currentPlayerId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            playerScore = rs.getInt("score");
	        } else {
	            System.out.println("No score found for player with ID: " + currentPlayerId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return playerScore;
	}
}
