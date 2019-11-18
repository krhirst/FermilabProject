package application;

import java.sql.*;
import java.util.ArrayList;

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
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public void remove(FermiEntry entry) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeQuery("DELETE FROM hours_offered WHERE Intradept._Seniority = "
			+ entry.getSeniority() + ";");
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public ArrayList<FermiEntry> search(String col, String val) {
		ArrayList<FermiEntry> result = new ArrayList<FermiEntry>();
		String colName, searchVal;
		if (col.equals("Name"))
			colName = "Name";
		else if (col.equals("Phone Number"))
			colName = "Phone_Number";
		else if (col.equals("OverTime Offered"))
			colName = "Overtime_Offered";
		else if (col.equals("Intradept. Seniority"))
			colName = "Intradept_Seniority";
		else
			colName = "In_Bison_Feeding_Program";
		
		if (col.equals("Name") || col.equals("Phone Number"))
			searchVal = String.format("'%s'", val);
		else
			searchVal = val;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(
					String.format("SELECT * FROM hours_offered WHERE %s = %s", colName, searchVal));
			
			while (res.next()) {
				result.add(new FermiEntry(res.getString(1), res.getString(2), res.getDouble(3),
						res.getInt(4), res.getBoolean(5)));
			}
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			result = null;
		}
		
		return result;
	}
	
	public ArrayList<FermiEntry> search(String exp) {
		ArrayList<FermiEntry> result = new ArrayList<FermiEntry>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM hours_offered WHERE " + exp);
			
			while (res.next()) {
				result.add(new FermiEntry(res.getString(1), res.getString(2), res.getDouble(3),
						res.getInt(4), res.getBoolean(5)));
			}
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			result = null;
		}
		
		return result;
	}
}
