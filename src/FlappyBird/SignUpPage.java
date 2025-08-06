package FlappyBird;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUpPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage frame = new SignUpPage();
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
	public SignUpPage() {
		setTitle("Sign Up an Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setForeground(new Color(64, 0, 0));
		txtName.setFont(new Font("Concert One", Font.PLAIN, 13));
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtName.getText().length() > 13) {
					e.consume();
				}
			}
		});
		txtName.setBounds(82, 211, 172, 31);
		txtName.setOpaque(false);
		txtName.setBorder(null);
		txtName.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(64, 0, 0));
		txtEmail.setFont(new Font("Concert One", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(82, 282, 172, 31);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(null);
		txtEmail.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(txtEmail);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setVerticalAlignment(SwingConstants.TOP);
		btnSignUp.setFont(new Font("Concert One", Font.PLAIN, 12));
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setIcon(null);
		btnSignUp.setOpaque(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setFocusPainted(false);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userName = txtName.getText().trim();
				String userEmail = txtEmail.getText().trim();
				String userPassword = new String(passwordField.getPassword());
				
				String hashPassword = BCrypt.hashpw(userPassword,BCrypt.gensalt());
				
				User user = new User(userName,userEmail,hashPassword);
				
				String insertSQL = "INSERT INTO users(user_name, email, password)"
						+ "	VALUES(?,?,?)";
				
				try(Connection conn = DBConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement(insertSQL);
						){
					
						stmt.setString(1, user.getUserName());
						if(!EmailValidator.isEmailExists(userEmail) && EmailValidator.isValidateEmail(userEmail)) {
							stmt.setString(2, user.getUserEmail());
						}else {
							JOptionPane.showMessageDialog(null, "Email Already Exist or Invalid Email");
//							throw new Error("Email Already Exist or Invalid Email);
							return;
						}
						stmt.setString(3, user.getUserPassword());
					
					int rowInsert = stmt.executeUpdate();
					if(rowInsert > 0) {
						JOptionPane.showMessageDialog(null, "User Registered Successfully");
						System.out.println("User registered successfully");
						
						LoginPage login = new LoginPage();
						login.setVisible(true);
						setVisible(false);
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setBounds(123, 400, 76, 28);
		contentPane.add(btnSignUp);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(64, 0, 0));
		passwordField.setFont(new Font("Concert One", Font.PLAIN, 13));
		passwordField.setBounds(82, 338, 172, 31);
		passwordField.setOpaque(false);
		passwordField.setBorder(null);
		passwordField.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(passwordField);
		setResizable(false);
		
		JLabel lblSwitchToLogin = new JLabel("Already registered?");
		lblSwitchToLogin.setForeground(new Color(128, 64, 0));
		lblSwitchToLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSwitchToLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);
				setVisible(false);
			}
		});
		lblSwitchToLogin.setBounds(113, 438, 125, 28);
		contentPane.add(lblSwitchToLogin);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/assets/signupmenuok.png")));
		lblNewLabel_4.setBounds(10, 10, 310, 521);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/assets/background-menu.png")));
		lblNewLabel.setBounds(0, 10, 350, 600);
		contentPane.add(lblNewLabel);

	}
}
