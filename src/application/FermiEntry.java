package application;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class FermiEntry implements Iterable<FermiEntry> {
	private SimpleStringProperty firstName, lastName, phone;
	private SimpleDoubleProperty overtime;
	private SimpleIntegerProperty seniority;
	private SimpleBooleanProperty inBison;

	public FermiEntry(String firstName, String lastName, String phone, double overtime, int seniority,
			boolean inBison) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.overtime = new SimpleDoubleProperty(overtime);
		this.seniority = new SimpleIntegerProperty(seniority);
		this.inBison = new SimpleBooleanProperty(inBison);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}

	public Double getOvertime() {
		return overtime.get();
	}

	public void setOvertime(double overtime) {
		this.overtime = new SimpleDoubleProperty(overtime);
	}

	public Integer getSeniority() {
		return seniority.get();
	}

	public void setSeniority(int seniority) {
		this.seniority = new SimpleIntegerProperty(seniority);
	}

	public Boolean isInBison() {
		return inBison.get();
	}

	public void setInBison(boolean inBison) {
		this.inBison = new SimpleBooleanProperty(inBison);
	}

	public String tableFormat() {
		int blInt;

		if (inBison.get())
			blInt = 1;
		else
			blInt = 0;
		return String.format("%s, %s, %f, %d, %d", firstName.getValue(), phone.getValue(), overtime.getValue(),
				seniority.getValue(), blInt);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("FirstName:%s,LastName:%s,Phone:%s,Overtime:%s,Seniority:%s,InBison:%s", getFirstName(),
				getLastName(), getPhone(), getOvertime(), getSeniority(), isInBison()));
		return sb.toString();
	}

	public static ArrayList<FermiEntry> getEmployees(FermiConnector dbConnection) {
		ResultSet resultSet = dbConnection.getData("hours_offered");
		return parseEmployeeData(resultSet);
	}

	private static ArrayList<FermiEntry> parseEmployeeData(ResultSet results) {
		ArrayList<FermiEntry> data = new ArrayList<>();
		try {
			while (results.next()) {
				data.add(new FermiEntry(results.getString(1), results.getString(2), results.getString(3),
						results.getDouble(4), results.getInt(5), results.getBoolean(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public Iterator<FermiEntry> iterator() {
		return null;
	}
}