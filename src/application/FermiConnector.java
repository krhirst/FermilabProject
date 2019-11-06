package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
			conn = DriverManager.getConnection(DB_URL, "fermitracker", "fermi123");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}
