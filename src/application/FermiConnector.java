package application;

import tableUpdates.Operation;
import tableUpdates.UpdateFileReader;

import java.sql.*;
import java.util.Stack;

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
			conn = DriverManager.getConnection(DB_URL, "user", "pass");
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
			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			return false;
		}
	}

	public boolean edit(FermiEntry entry, int originalSeniority) {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE hours_offered "
					+ "SET First_Name = ?, Last_Name = ?, Phone_Number = ?, Overtime_Offered = ?, Seniority = ?,"
					+ "In_Bison_Feeding_Program = ? " + "WHERE Seniority = ?;");

			stmt.setString(1, entry.getFirstName());
			stmt.setString(2, entry.getLastName());
			stmt.setString(3, entry.getPhone());
			stmt.setDouble(4, entry.getOvertime());
			stmt.setInt(5, entry.getSeniority());
			stmt.setBoolean(6, entry.isInBison());
			stmt.setInt(7, originalSeniority);

			stmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}

	}

	public boolean remove(FermiEntry entry) {
		try {
			Statement stmt = conn.createStatement();

			stmt.execute("DELETE FROM hours_offered WHERE Seniority = " + entry.getSeniority() + ";");
			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			return false;
		}
	}

	public ResultSet getData(String tableName) {
		ResultSet result = null;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT * FROM " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getUpdateTime() {
		Stack stack = UpdateFileReader.getUpdatesAsStack();
		Operation op = (Operation) stack.peek();
		return op.getTime();
	}
}
