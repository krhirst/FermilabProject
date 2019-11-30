package tableUpdates;

import application.FermiEntry;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;

public class UpdateOperation extends Operation {
    private FermiEntry originalEntry;
    private FermiEntry updatedEntry;
    private String firstName;
    private String lastName;
    private int seniority;
    private LinkedList<ChangedField> changedFields;

    public UpdateOperation(Time time, String firstName, String lastName, int seniority, LinkedList<ChangedField> changedFields) {
        super(time);
        this.firstName = firstName;
        this.lastName = lastName;
        this.seniority = seniority;
        this.changedFields = changedFields;
    }

    public UpdateOperation(Time time, FermiEntry oldEntry, FermiEntry newEntry) {
        super(time);
        originalEntry = oldEntry;
        updatedEntry = newEntry;

        firstName = oldEntry.getFirstName();
        lastName = oldEntry.getLastName();
        seniority = oldEntry.getSeniority();

        setChangedFields();
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
        Field[] fields = originalEntry.getClass().getDeclaredFields();
        String oldValue = null;
        String newValue = null;

        for (int i = 0; i < fields.length; i++) {
            try {
                oldValue = fields[i].get(originalEntry).toString();
                newValue = fields[i].get(updatedEntry).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (oldValue != newValue) {
                ChangedField changedField = new ChangedField(fields[i].getName(), oldValue, newValue);
                changedFields.add(changedField);
            }
        }

    }
}
