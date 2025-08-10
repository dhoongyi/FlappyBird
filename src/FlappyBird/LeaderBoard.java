package FlappyBird;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeaderBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private int contentY = 208;
	private int starY = 200;
	List<String> playerNames = new ArrayList<>();
	List<Integer> scores = new ArrayList<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public LeaderBoard() {
		setTitle("Flappy Bird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 

		getScore();		
		
		for (int i = 0; i < playerNames.size(); i++) {
			JLabel lblSerialNumber = new JLabel(String.valueOf(i + 1));
			lblSerialNumber.setBounds(78, contentY, 17, 13);
			contentPane.add(lblSerialNumber);
			lblSerialNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblSerialNumber.setForeground(new Color(128, 64, 0));
			lblSerialNumber.setHorizontalAlignment(SwingConstants.CENTER);

			JLabel lblStar = new JLabel("");
			lblStar.setBounds(75, starY, 22, 28);
			contentPane.add(lblStar);
			lblStar.setIcon(new ImageIcon(getClass().getResource("/assets/star.png")));

			JLabel lblPlayerName = new JLabel(playerNames.get(i));
			lblPlayerName.setHorizontalAlignment(SwingConstants.LEFT);
			lblPlayerName.setForeground(new Color(128, 64, 0));
			lblPlayerName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPlayerName.setBounds(110, contentY, 102, 13);
			contentPane.add(lblPlayerName);

			JLabel lblScore = new JLabel(String.valueOf(scores.get(i)));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setForeground(new Color(128, 64, 0));
			lblScore.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblScore.setBounds(200, contentY, 50, 13);
			contentPane.add(lblScore);

			contentY += 38; 
			starY += 38;	
		}
		
		JLabel leaderBoardCard = new JLabel("");
		leaderBoardCard.setHorizontalAlignment(SwingConstants.CENTER);
		leaderBoardCard.setIcon(new ImageIcon(getClass().getResource("/assets/leaderboardtemplate.png")));
		leaderBoardCard.setBounds(48, 120, 230, 292);
		contentPane.add(leaderBoardCard);
		
		JButton btnRestart = new JButton("");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseGameMode chgamemode = new ChooseGameMode();
				chgamemode.setVisible(true);
				setVisible(false);
			}
		});
		btnRestart.setIcon(new ImageIcon(getClass().getResource("/assets/restart.png")));
		btnRestart.setBounds(35, 442, 115, 40);
		contentPane.add(btnRestart);
		
		JButton btnQuite = new JButton("");
		btnQuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuite.setIcon(new ImageIcon(getClass().getResource("/assets/quite.png")));
		btnQuite.setBounds(183, 442, 115, 40);
		contentPane.add(btnQuite);
		
		JLabel bgMenu = new JLabel("");
		bgMenu.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		bgMenu.setBounds(0, -75, 350, 675);
		contentPane.add(bgMenu);
		
		
	}

	public void getScore(){
		try (Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT users.user_name, scores.score FROM scores JOIN users ON scores.user_id = users.user_id ORDER BY scores.score DESC LIMIT 5"
			);
			ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				playerNames.add(rs.getString("user_name"));
				scores.add(rs.getInt("score"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
