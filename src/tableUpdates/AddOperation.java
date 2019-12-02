package tableUpdates;

import application.FermiEntry;

import java.lang.reflect.Field;

public class AddOperation extends Operation {
	private FermiEntry entry;

	public AddOperation(FermiEntry entry) {
		super();
		this.entry = entry;
	}

	public FermiEntry getEntry() {
		return entry;
	}

	public void setEntry(FermiEntry entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		String str = String.format("%s,%s,%s", this.getTime(), "add", entry.toString());
		return str;
	}
}
