package FlappyBird;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(64, 0, 0));
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
//		txtEmail.setBackground(new Color(0, 0, 0 ,0));
		txtEmail.setOpaque(false);
		txtEmail.setBackground(new Color(0, 0, 0, 0));
		txtEmail.setBorder(null);
		txtEmail.setBounds(92, 229, 173, 32);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(new Color(64, 0, 0));
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPassword.setBounds(92, 299, 173, 32);
		txtPassword.setOpaque(false);
		txtPassword.setBorder(null);
		txtPassword.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(txtPassword);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.setVerticalAlignment(SwingConstants.TOP);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Concert One", Font.BOLD, 15));
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userEmail = txtEmail.getText().trim();
				String userPassword = new String(txtPassword.getText());
				
				if(AuthService.isLogin(userEmail, userPassword) && EmailValidator.isEmailExists(userEmail) && EmailValidator.isValidateEmail(userEmail)) {
					LoggedUserInfo.getInstance().setUserEmail(userEmail);
					LoggedUserInfo.getInstance().setUserId(AuthService.getUserId(userEmail));
					LoggedUserInfo.getInstance().setUserName(AuthService.getUserName(userEmail));
					
					JOptionPane.showMessageDialog(null, "Login successfully");
					MainMenu main = new MainMenu();
					main.setVisible(true);
					setVisible(false);
					System.out.println("Login Successfully");
					
				}else {
					JOptionPane.showMessageDialog(null, "Login Failed");
					System.out.println("Login Failed");
					
				}
			}
		});
		btnLogin.setBounds(132, 420, 86, 29);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_3 = new JLabel("You don't have an account?");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUpPage signup = new SignUpPage();
				signup.setVisible(true);
				setVisible(false);
			}
		});
		lblNewLabel_3.setBounds(92, 367, 190, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/assets/loginmenuok.png")));
		lblNewLabel.setBounds(10, 76, 330, 417);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		lblNewLabel_1.setBounds(0, 0, 350, 602);
		contentPane.add(lblNewLabel_1);

	}

}
