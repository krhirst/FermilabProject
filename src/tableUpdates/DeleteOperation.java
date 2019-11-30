package tableUpdates;

import application.FermiEntry;

import java.sql.Time;

public class DeleteOperation extends Operation {
    private FermiEntry entry;
    private String firstName;
    private String lastName;
    private int seniority;

    public DeleteOperation(Time time, String firstName, String lastName, int seniority) {
        super(time);
        this.firstName = firstName;
        this.lastName = lastName;
        this.seniority = seniority;
    }

    public DeleteOperation(Time time, FermiEntry entry) {
        super(time);
        this.entry = entry;
        this.firstName = entry.getFirstName();
        this.lastName = entry.getLastName();
        this.seniority = entry.getSeniority();
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
}
