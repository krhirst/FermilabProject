package tableUpdates;

import application.FermiEntry;

import java.lang.reflect.Method;
import java.util.LinkedList;

public class UpdateOperation extends Operation {
	private FermiEntry originalEntry;
	private FermiEntry updatedEntry;
	private String firstName;
	private String lastName;
	private int seniority;
	private LinkedList<ChangedField> changedFields = new LinkedList<>();

	public UpdateOperation(String firstName, String lastName, int seniority, LinkedList<ChangedField> changedFields) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.seniority = seniority;
		this.changedFields = changedFields;
	}

	public UpdateOperation(FermiEntry oldEntry, FermiEntry newEntry) {
		super();
		originalEntry = oldEntry;
		updatedEntry = newEntry;

		firstName = oldEntry.getFirstName();
		lastName = oldEntry.getLastName();
		seniority = oldEntry.getSeniority();

		setChangedFields();
	}

	public UpdateOperation(String firstName, String lastName, String seniority,
			LinkedList<ChangedField> changedFields) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.seniority = Integer.parseInt(seniority);
		this.changedFields = changedFields;
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

	public LinkedList<ChangedField> getChangedFields() {
		return changedFields;
	}

	public void setChangedFields(LinkedList<ChangedField> changedFields) {
		this.changedFields = changedFields;
	}

	private void setChangedFields() {
		String[] fields = new String[] { "FirstName", "LastName", "Phone", "Overtime", "Seniority", "InBison" };
		String[] oldValues = new String[] { originalEntry.getFirstName(), originalEntry.getLastName(),
				originalEntry.getPhone(), originalEntry.getOvertime().toString(),
				originalEntry.getSeniority().toString(), originalEntry.isInBison().toString() };
		String[] newValues = new String[] { updatedEntry.getFirstName(), updatedEntry.getLastName(),
				updatedEntry.getPhone(), updatedEntry.getOvertime().toString(), updatedEntry.getSeniority().toString(),
				updatedEntry.isInBison().toString() };

		for (int i = 0; i < fields.length; i++) {
			if (!oldValues[i].equals(newValues[i])) {
				ChangedField changedField = new ChangedField(fields[i], oldValues[i], newValues[i]);
				changedFields.add(changedField);
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("%s,%s,%s,%s,%s,", this.getTime(), "update", this.firstName,
				this.lastName, this.seniority));
		for (ChangedField cf : changedFields) {
			sb.append(cf.toString() + ",");
		}
		return sb.toString();
	}
}
