package application;

import javafx.beans.property.*;

public class FermiEntry {
	private SimpleStringProperty name, phone;
	private SimpleDoubleProperty overtime;
	private SimpleIntegerProperty seniority;
	private SimpleBooleanProperty inBison;
	
	public FermiEntry(String name, String phone, Double overtime, Integer seniority, Boolean inBison) {
		this.name = new SimpleStringProperty(name);
		this.phone = new SimpleStringProperty(phone);
		this.overtime = new SimpleDoubleProperty(overtime);
		this.seniority = new SimpleIntegerProperty(seniority);
		this.inBison = new SimpleBooleanProperty(inBison);
	}

	public String getName() {
		return name.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(SimpleStringProperty phone) {
		this.phone = phone;
	}

	public Double getOvertime() {
		return overtime.get();
	}

	public void setOvertime(SimpleDoubleProperty overtime) {
		this.overtime = overtime;
	}

	public Integer getSeniority() {
		return seniority.get();
	}

	public void setSeniority(SimpleIntegerProperty seniority) {
		this.seniority = seniority;
	}

	public Boolean isInBison() {
		return inBison.get();
	}

	public void setInBison(SimpleBooleanProperty inBison) {
		this.inBison = inBison;
	}
	
	public String tableFormat() {
		int blInt;
		
		if (inBison.get())
			blInt = 1;
		else
			blInt = 0;
		return String.format("%s, %s, %f, %d, %d", name, phone, overtime, seniority, blInt);
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