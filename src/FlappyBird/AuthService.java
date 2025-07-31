package FlappyBird;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
	public static boolean isLogin(String email, String password) {
		String query = "SELECT * FROM users WHERE email = ? AND password = ?";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			return rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static int getUserId(String email) {
		String query = "SELECT user_id FROM users WHERE email = ?";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("user_id");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static String getUserName(String email) {
		String query = "SELECT user_name FROM users WHERE email = ?";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("user_name");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
