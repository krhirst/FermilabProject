package xmlstuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FermiConnector {
	
	
	public static void main(String[] args) {
		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/fermi_base";
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL, "root", "Redrobot!6");
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM hours_offered");
			
			while (result.next()) {
				System.out.printf("Name: %s\nPhone Number: %s\nOvertime Offered: %f\nIntradept Senority: %d\n"
						+ "In Bison Feeding program: %s\n\n",result.getString(1), result.getString(2), 
						result.getBigDecimal(3), result.getInt(4), result.getBoolean(5));
			}
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}