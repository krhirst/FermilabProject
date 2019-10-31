package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FermiConnector {
		private final String DB_URL = "jdbc:mysql://45.55.136.114:3306/fermitracker";
		private ObservableList<FermiEntry> data = FXCollections.observableArrayList();
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


	public ObservableList<FermiEntry> getData() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM hours_offered");
		while (result.next()) {
			FermiEntry entry = new FermiEntry(result.getString(1), result.getString(2),
					result.getBigDecimal(3).doubleValue(), result.getInt(4), result.getBoolean(5));
			data.add(entry);
		}
		return data;
	}
}
