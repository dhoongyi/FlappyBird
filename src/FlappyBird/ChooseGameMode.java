package FlappyBird;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseGameMode extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String selectedBird = "flappybird";   // default
	private String selectedThemeImg = "background-menu";     // default

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseGameMode frame = new ChooseGameMode();
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
	public ChooseGameMode() {
		setTitle("Flappy Bird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblSelectBird = new JLabel("SELECT BIRD COLOR");
		lblSelectBird.setFont(new Font("Concert One", Font.BOLD, 18));
		lblSelectBird.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectBird.setBounds(29, -20, 286, 129);
		contentPane.add(lblSelectBird);
		
		 // Bird selection labels
	    JLabel lblRedBird = new JLabel(new ImageIcon(getClass().getResource("/assets/redBird.png")));
	    lblRedBird.setBounds(50, 60, 50, 50);
	    lblRedBird.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            selectedBird = "redBird";
	            System.out.println("Selected: Red Bird");
	        }
	    });
	    contentPane.add(lblRedBird);
	    
	    JLabel lblBlueBird = new JLabel(new ImageIcon(getClass().getResource("/assets/blueBird.png")));
	    lblBlueBird.setBounds(150, 60, 50, 50);
	    lblBlueBird.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            selectedBird = "blueBird";
	            System.out.println("Selected: Blue Bird");
	        }
	    });
	    contentPane.add(lblBlueBird);

	    JLabel lblYellowBird = new JLabel(new ImageIcon(getClass().getResource("/assets/yelloBird.png")));
	    lblYellowBird.setBounds(250, 60, 50, 50);
	    lblYellowBird.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            selectedBird = "flappybird";
	            System.out.println("Selected: Green Bird");
	        }
	    });
	    contentPane.add(lblYellowBird);
	    
	    JLabel lblSelectMode = new JLabel("SELECT MODE");
        lblSelectMode.setFont(new Font("Concert One", Font.BOLD, 18));
        lblSelectMode.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectMode.setBounds(29, 120, 286, 40);
        contentPane.add(lblSelectMode);

        JLabel lblDayMode = new JLabel("Day Mode");
        lblDayMode.setBounds(80, 160, 64, 64);
        lblDayMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedThemeImg = "background-menu";
                System.out.println("Selected Mode: Day");
            }
        });
        contentPane.add(lblDayMode);
        
        JLabel lblNightMode = new JLabel("Night Mode");
        lblNightMode.setBounds(200, 160, 70, 64);
        lblNightMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedThemeImg = "background-night";
                System.out.println("Selected Mode: Night");
            }
        });
        contentPane.add(lblNightMode);

		
		JLabel lblEasy = new JLabel("Easy");
		lblEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FlappyPlay play = new FlappyPlay("easy",selectedBird, selectedThemeImg);
				play.setVisible(true);
				setVisible(false);
			}
		});
		lblEasy.setFont(new Font("Tiny5", Font.BOLD, 18));
		lblEasy.setHorizontalAlignment(SwingConstants.CENTER);
		lblEasy.setBounds(117, 297, 120, 39);
		contentPane.add(lblEasy);
		
		JLabel lblNormal = new JLabel("Normal");
		lblNormal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FlappyPlay play = new FlappyPlay("normal",selectedBird , selectedThemeImg);
				play.setVisible(true);
				setVisible(false);
			}
		});
		lblNormal.setFont(new Font("Tiny5", Font.BOLD, 18));
		lblNormal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNormal.setBounds(144, 367, 78, 15);
		contentPane.add(lblNormal);
		
		JLabel lblHard = new JLabel("Hard");
		lblHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FlappyPlay play = new FlappyPlay("hard",selectedBird ,selectedThemeImg);
				play.setVisible(true);
				setVisible(false);
			}
		});
		lblHard.setFont(new Font("Tiny5", Font.BOLD, 18));
		lblHard.setHorizontalAlignment(SwingConstants.CENTER);
		lblHard.setBounds(144, 429, 70, 15);
		contentPane.add(lblHard);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("/assets/diffmodemenu1.png")));
		label.setBounds(53, 235, 249, 279);
		contentPane.add(label);
		
		JLabel lblSelectDifficulity = new JLabel("SELECT DIFFICULITY");
		lblSelectDifficulity.setFont(new Font("Concert One", Font.BOLD, 18));
		lblSelectDifficulity.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDifficulity.setBounds(29, 174, 286, 129);
		contentPane.add(lblSelectDifficulity);
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu mainmenu = new MainMenu();
				mainmenu.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(getClass().getResource("/assets/back.png")));
		btnBack.setBackground(new Color(94, 226, 112));
		btnBack.setBounds(117, 500, 120, 46);
		contentPane.add(btnBack);
		
		
		JLabel bgmenu = new JLabel("");
		bgmenu.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		bgmenu.setBounds(0, -75, 350, 675);
		contentPane.add(bgmenu);

	}
}
