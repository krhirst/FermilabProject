package application;

import javafx.beans.property.*;

public class FermiEntry {
	private SimpleStringProperty name, phone;
	private SimpleDoubleProperty overtime;
	private SimpleIntegerProperty seniority;
	private SimpleBooleanProperty inBison;
	
	public FermiEntry(String name, String phone, double overtime, int seniority, boolean inBison) {
		this.name = new SimpleStringProperty(name);
		this.phone = new SimpleStringProperty(phone);
		this.overtime = new SimpleDoubleProperty(overtime);
		this.seniority = new SimpleIntegerProperty(seniority);
		this.inBison = new SimpleBooleanProperty(inBison);
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public SimpleStringProperty getPhone() {
		return phone;
	}

	public void setPhone(SimpleStringProperty phone) {
		this.phone = phone;
	}

	public SimpleDoubleProperty getOvertime() {
		return overtime;
	}

	public void setOvertime(SimpleDoubleProperty overtime) {
		this.overtime = overtime;
	}

	public SimpleIntegerProperty getSeniority() {
		return seniority;
	}

	public void setSeniority(SimpleIntegerProperty seniority) {
		this.seniority = seniority;
	}

	public SimpleBooleanProperty isInBison() {
		return inBison;
	}

	public void setInBison(SimpleBooleanProperty inBison) {
		this.inBison = inBison;
	}

	@Override
	public String toString() {
		return "FermiEntry{" +
				"name=" + name +
				", phone=" + phone +
				", overtime=" + overtime +
				", seniority=" + seniority +
				", inBison=" + inBison +
				'}';
	}
}