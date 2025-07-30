package FlappyBird;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator {
	
	public static boolean isValidateEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}
	
	public static boolean isEmailExists(String email) {
        if (!isValidateEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        
        try(Connection conn = DBConnection.getConnection();) {
        	String query = "SELECT * FROM users WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
