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
	
	public void add(FermiEntry entry) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeQuery("INSERT INTO hours_offered VALUES (" + entry.tableFormat() + ")");
			
			conn.close();
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public void remove(FermiEntry entry) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeQuery("DELETE FROM hours_offered WHERE Intradept._Seniority = "
			+ entry.getSeniority() + ";");
			
			conn.close();
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}
