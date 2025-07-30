package FlappyBird;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

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
		setBounds(100, 100, 299, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign Up an Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(85, 25, 125, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(56, 87, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(53, 118, 184, 31);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(56, 175, 85, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(56, 257, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(56, 280, 184, 31);
		contentPane.add(txtEmail);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userName = txtName.getText().trim();
				String userEmail = txtEmail.getText().trim();
				String userPassword = new String(passwordField.getText());
				
				User user = new User(userName,userEmail,userPassword);
				
				String insertSQL = "INSERT INTO users(user_name, email, password)"
						+ "	VALUES(?,?,?)";
				
				try(Connection conn = DBConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement(insertSQL);
						){
					
						stmt.setString(1, user.getUserName());
						if(!EmailValidator.isEmailExists(userEmail) || !EmailValidator.isValidateEmail(userEmail)) {
							stmt.setString(2, user.getUserEmail());
						}else {
							JOptionPane.showMessageDialog(null, "Email Already Exist or Invalid Email dected");
//							throw new Error("Email Already Exist o
							return;
						}
						stmt.setString(3, user.getUserPassword());
					
					int rowInsert = stmt.executeUpdate();
					if(rowInsert > 0) {
						JOptionPane.showMessageDialog(null, "User Registered Successfully");
						System.out.println("User registered successfully");
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setBounds(107, 339, 85, 21);
		contentPane.add(btnSignUp);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(53, 198, 184, 32);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\FlappyBird\\src\\background-day.png"));
		lblNewLabel_4.setBounds(0, 0, 285, 492);
		contentPane.add(lblNewLabel_4);

	}
}
