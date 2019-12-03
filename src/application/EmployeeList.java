package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeList implements Iterable<FermiEntry> {
    private FermiConnector dbConnection = new FermiConnector();
    private List<FermiEntry> list = null;

    public EmployeeList() {
        this.list = getEmployees(dbConnection);
    }

    @Override
    public Iterator<FermiEntry> iterator() {
        return new FermiEntryIterator<>(list);
    }

    public static ArrayList<FermiEntry> getEmployees(FermiConnector dbConnection) {
        ResultSet resultSet = dbConnection.getData("hours_offered");
        return parseEmployeeData(resultSet);
    }

    private static ArrayList<FermiEntry> parseEmployeeData(ResultSet results) {
        ArrayList<FermiEntry> data = new ArrayList<>();
        try {
            while (results.next()) {
                data.add(new FermiEntry(results.getString(1), results.getString(2), results.getString(3),
                        results.getDouble(4), results.getInt(5), results.getBoolean(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}

class FermiEntryIterator<FermiEntry> implements Iterator<FermiEntry> {

    int indexPosition = 0;
    List<FermiEntry> internalList;

    public FermiEntryIterator(List<FermiEntry> internalList) {
        this.internalList = internalList;
    }

    @Override
    public boolean hasNext() {
        if (internalList.size() >= indexPosition + 1) {
            return true;
        }
        return false;
    }

    @Override
    public FermiEntry next() {
        FermiEntry val = internalList.get(indexPosition);
        indexPosition += 1;
        return val;
    }
}
