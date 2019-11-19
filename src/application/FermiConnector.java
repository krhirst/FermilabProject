package application;

import java.sql.*;

public class FermiConnector {
		private final String DB_URL = "jdbc:mysql://45.55.136.114:3306/fermitracker";
		private Connection conn;

	public String getDB_URL() {
		return DB_URL;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public FermiConnector() {
		try {
			conn = DriverManager.getConnection(DB_URL, "username", "password");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public boolean add(FermiEntry entry) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO hours_offered VALUES (?,?,?,?,?,?)");
			stmt.setString(1, entry.getFirstName());
			stmt.setString(2, entry.getLastName());
			stmt.setString(3, entry.getPhone());
			stmt.setDouble(4, entry.getOvertime());
			stmt.setInt(5, entry.getSeniority());
			stmt.setBoolean(6, entry.isInBison());

			stmt.execute();
			
			conn.close();

			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean remove(FermiEntry entry) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeQuery("DELETE FROM hours_offered WHERE Seniority = "
			+ entry.getSeniority() + ";");
			
			conn.close();
			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			return false;
		}
	}
}
