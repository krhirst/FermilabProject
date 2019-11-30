package tableUpdates;

import application.FermiEntry;

import java.sql.Time;

public class AddOperation extends Operation {
    private FermiEntry entry;

    public AddOperation(Time time, FermiEntry entry) {
        super(time);
        this.entry = entry;
    }

    public FermiEntry getEntry() {
        return entry;
    }

    public void setEntry(FermiEntry entry) {
        this.entry = entry;
    }
}
