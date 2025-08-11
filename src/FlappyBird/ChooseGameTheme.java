package FlappyBird;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ChooseGameTheme extends JFrame {

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
					ChooseGameTheme frame = new ChooseGameTheme();
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
	public ChooseGameTheme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHOOSE BIRD CHARACTER");
		lblNewLabel.setFont(new Font("Concert One", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 31, 316, 32);
		contentPane.add(lblNewLabel);
		
		 // Bird selection labels
	    JLabel lblRedBird = new JLabel(new ImageIcon(getClass().getResource("/assets/redBird.png")));
	    lblRedBird.setBounds(56, 97, 34, 24);
	    lblRedBird.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            selectedBird = "redBird";
	            System.out.println("Selected: Red Bird");
	        }
	    });
	    contentPane.setLayout(null);
	    contentPane.add(lblRedBird);
	    
	    JLabel lblBlueBird = new JLabel(new ImageIcon(getClass().getResource("/assets/blueBird.png")));
	    lblBlueBird.setBounds(156, 97, 34, 24);
	    lblBlueBird.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            selectedBird = "blueBird";
	            System.out.println("Selected: Blue Bird");
	        }
	    });
	    contentPane.add(lblBlueBird);

	    JLabel lblYellowBird = new JLabel(new ImageIcon(getClass().getResource("/assets/yelloBird.png")));
	    lblYellowBird.setBounds(253, 97, 34, 24);
	    lblYellowBird.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            selectedBird = "flappybird";
	            System.out.println("Selected: Green Bird");
	        }
	    });
	    contentPane.add(lblYellowBird);
	    
	    JLabel lblSelectMode = new JLabel("SELECT THEME");
        lblSelectMode.setFont(new Font("Concert One", Font.BOLD, 20));
        lblSelectMode.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectMode.setBounds(10, 152, 316, 32);
        contentPane.add(lblSelectMode);

        JLabel lblDayMode = new JLabel("");
        lblDayMode.setIcon(new ImageIcon(getClass().getResource("/assets/icon1.png")));
        lblDayMode.setBounds(34, 218, 34, 32);
        lblDayMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedThemeImg = "background-menu";
                System.out.println("Selected Mode: Day");
            }
        });
        contentPane.add(lblDayMode);
        
        JLabel lblNightMode = new JLabel("");
        lblNightMode.setIcon(new ImageIcon(getClass().getResource("/assets/icon2.png")));
        lblNightMode.setBounds(116, 218, 32, 32);
        lblNightMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedThemeImg = "background-night";
                System.out.println("Selected Mode: Night");
            }
        });
        contentPane.add(lblNightMode);
        
        JButton btnPlay = new JButton("");
		btnPlay.setBackground(new Color(34, 114, 255));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseGameMode gameMode = new ChooseGameMode(selectedBird, selectedThemeImg);
				gameMode.setVisible(true);
				setVisible(false);
			}
		});
        
        btnPlay.setIcon(new ImageIcon(getClass().getResource("/assets/play.png")));
		btnPlay.setBounds(116, 331, 117, 46);
		contentPane.add(btnPlay);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedThemeImg = "background-desert";
				System.out.println("Desert Mode Selected");
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/assets/icon3.png")));
		lblNewLabel_1.setBounds(193, 218, 34, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedThemeImg = "background-snow";
				System.out.println("Snow Mode Selected");
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/assets/icon4.png")));
		lblNewLabel_2.setBounds(275, 218, 32, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		lblNewLabel_3.setBounds(0, 0, 330, 535);
		contentPane.add(lblNewLabel_3);
		
	}
}
