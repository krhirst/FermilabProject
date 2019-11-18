package login;

import application.FermiConnector;
import java.sql.*;

public class UserLogin {
	private FermiConnector base = new FermiConnector();
	Connection conn = base.getConn();
	
	public void createUser(String username, String password) {
		String[] encript = PasswordEncripter.encriptPass(password);
		
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeQuery(String.format("INSERT INTO user_data VALUES (%s, %s, %s)", 
					username, encript[0], encript[1]));
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public boolean login(String username, String password) {
		boolean valid = false;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet user = stmt.executeQuery("SELECT * FROM user_data WHERE Username = " + username + ";");
			
			if (user.getFetchSize() == 1) {
				user.first();
				
				valid = Authentication.validatePass(password, user.getString(2), user.getString(3));
			}
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		return valid;
	}
}
