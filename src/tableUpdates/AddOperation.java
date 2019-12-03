package tableUpdates;

import application.FermiEntry;

public class AddOperation extends Operation {
	private FermiEntry entry;
	private String firstName;
	private String lastName;
	private int seniority;
	private double hoursChanged = 0;

	public AddOperation(FermiEntry entry) {
		super();
		this.type = "add";
		this.entry = entry;
		this.firstName = entry.getFirstName();
		this.lastName = entry.getLastName();
		this.seniority = entry.getSeniority();
	}

	public FermiEntry getEntry() {
		return entry;
	}

	public void setEntry(FermiEntry entry) {
		this.entry = entry;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public double getHoursChanged() {
		return hoursChanged;
	}

	@Override
	public String toString() {
		String str = String.format("%s,%s,%s", this.getTime(), "add", entry.toString());
		return str;
	}
}
