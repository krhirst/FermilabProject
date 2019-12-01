package tableUpdates;

public class ChangedField {
    private String fieldName;
    private String oldEntry;
    private String newEntry;

    public ChangedField(String fieldName, String oldEntry, String newEntry) {
        this.fieldName = fieldName;
        this.oldEntry = oldEntry;
        this.newEntry = newEntry;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldEntry() {
        return oldEntry;
    }

    public void setOldEntry(String oldEntry) {
        this.oldEntry = oldEntry;
    }

    public String getNewEntry() {
        return newEntry;
    }

    public void setNewEntry(String newEntry) {
        this.newEntry = newEntry;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", this.fieldName, this.oldEntry, this.newEntry);
    }
}
