package application;

import java.util.Comparator;

public class LastNameComparator implements Comparator<FermiEntry> {
	@Override
	public int compare(FermiEntry o1, FermiEntry o2) {
		return o1.getLastName().compareToIgnoreCase(o2.getLastName());
	}
}
