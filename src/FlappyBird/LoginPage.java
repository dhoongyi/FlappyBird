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
		setBounds(100, 100, 298, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login To Flappy Bird",SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(64, 32, 157, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(45, 86, 66, 13);
		contentPane.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(45, 108, 190, 32);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(45, 171, 131, 13);
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(45, 194, 190, 32);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userEmail = txtEmail.getText().trim();
				String userPassword = new String(txtPassword.getText());
				
				if(AuthService.isLogin(userEmail, userPassword) && EmailValidator.isEmailExists(userEmail) && EmailValidator.isValidateEmail(userEmail)) {
					LoggedUserInfo.getInstance().setUserEmail(userEmail);
					LoggedUserInfo.getInstance().setUserId(AuthService.getUserId(userEmail));
					LoggedUserInfo.getInstance().setUserName(AuthService.getUserName(userEmail));
					
					JOptionPane.showMessageDialog(null, "Login successfully");
					System.out.println("Login Successfully");
					
				}else {
					JOptionPane.showMessageDialog(null, "Login Failed");
					System.out.println("Login Failed");
					
				}
			}
		});
		btnNewButton.setBounds(150, 260, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("You don't have an account?");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUpPage signup = new SignUpPage();
				signup.setVisible(true);
				setVisible(false);
			}
		});
		lblNewLabel_3.setBounds(45, 237, 190, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\FlappyBird\\src\\background-day.png"));
		lblNewLabel_4.setBounds(0, 10, 284, 499);
		contentPane.add(lblNewLabel_4);

	}

}
