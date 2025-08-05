package FlappyBird;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class MainMenu extends JFrame {
	
//	Image backgroundImg;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
	public MainMenu() {
		
		setTitle("Flappy Bird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		
		JButton btnPlay = new JButton("");
		btnPlay.setBackground(new Color(34, 114, 255));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseGameMode chgamemode = new ChooseGameMode();
				chgamemode.setVisible(true);
				setVisible(false);
			}
		});
//		btnPlay.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/play.png"));
		btnPlay.setIcon(new ImageIcon(getClass().getResource("/assets/play.png")));
		btnPlay.setBounds(121, 250, 117, 46);
		contentPane.add(btnPlay);
		
		JButton btnLeaderboard = new JButton("");
		btnLeaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaderBoard leaderBoard = new LeaderBoard();
				leaderBoard.setVisible(true);
				setVisible(false);
			}
		});
//		btnLeaderboard.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/leaderboard.png"));
		btnLeaderboard.setIcon(new ImageIcon(getClass().getResource("/assets/leaderboard.png")));
		btnLeaderboard.setBackground(new Color(0, 0, 0));
		btnLeaderboard.setBounds(121, 332, 117, 46);
		contentPane.add(btnLeaderboard);
		
		JButton btnQuite = new JButton("");
		btnQuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
//		btnQuite.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/quite.png"));
		btnQuite.setIcon(new ImageIcon(getClass().getResource("/assets/quite.png")));
		btnQuite.setBackground(new Color(94, 226, 112));
		btnQuite.setBounds(121, 421, 117, 46);
		contentPane.add(btnQuite);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/flappytext1.png"));
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/assets/flappytext1.png")));
		lblNewLabel.setBounds(29, 57, 299, 145);
		contentPane.add(lblNewLabel);

		JLabel bgmenu = new JLabel("");
//		bgmenu.setIcon(new ImageIcon("/home/rango/eclipse-workspace/FB/src/assets/background-menu.png"));
		bgmenu.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		bgmenu.setBounds(0, -75, 350, 675);
		contentPane.add(bgmenu);
	}
}
