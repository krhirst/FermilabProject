package application;

import java.util.Comparator;

public class HoursComparator implements Comparator<FermiEntry> {
    @Override
    public int compare(FermiEntry o1, FermiEntry o2) {
        return o1.getOvertime().compareTo(o2.getOvertime());
    }
}
