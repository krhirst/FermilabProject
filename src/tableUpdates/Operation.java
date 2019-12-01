package tableUpdates;

import application.FermiEntry;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class Operation {
    private String time;

    public Operation() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate=dateFormat.format(date);
        this.time = formattedDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean addToFile(String str) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("updates.txt", true);
            FileChannel channel = fos.getChannel();
            FileLock lock = channel.tryLock();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OverlappingFileLockException e) {
            e.printStackTrace();
        }

        str += "\n";
        System.out.println(str);
        byte[] bytes = str.toString().getBytes();
        try {
            fos.write(bytes);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Operation readFromFile(String data) {
        Operation op = null;

        String[] splitString = data.split(",");
        if (splitString[1].equals("add")) {
            op = parseAddOperation(splitString);
        } else if (splitString[1].equals("update")) {
            op = parseUpdateOperation(splitString);
        } else if (splitString[1].equals("delete")) {
            op = parseDeleteOperation(splitString);
        }

        op.setTime(splitString[0]);
        return op;
    }

    private Operation parseAddOperation(String[] splitString) {
        FermiEntry entry = parseEntry(splitString);
        return new AddOperation(entry);
    }

    private Operation parseUpdateOperation(String[] splitString) {
        ArrayList<String> newValues = new ArrayList<>();
        FermiEntry originalEntry = parseEntry(splitString);
        FermiEntry updatedEntry = null;

        for (int i = 2; i < splitString.length; i++) {
            String[] fieldSplit = splitString[i].split(":");
            newValues.add(fieldSplit[2]);
        }
        try {
            updatedEntry = new FermiEntry(newValues.get(0), newValues.get(1), newValues.get(2), Double.parseDouble(newValues.get(3)),
                    Integer.parseInt(newValues.get(4)), Boolean.parseBoolean(newValues.get(5)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new UpdateOperation(originalEntry, updatedEntry);
    }

    private Operation parseDeleteOperation(String[] splitString) {
        ArrayList<String> values = new ArrayList<>();
        Operation op = null;

        for (int i = 2; i < splitString.length; i++) {
            String[] fieldSplit = splitString[i].split(":");
            values.add(fieldSplit[1]);
        }
        try {
            op = new DeleteOperation(values.get(0), values.get(1), Integer.parseInt(values.get(2)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return op;
    }

    private FermiEntry parseEntry(String[] splitString) {
        ArrayList<String> values = new ArrayList<>();
        FermiEntry entry = null;
        for (int i = 2; i < splitString.length; i++) {
            String[] fieldSplit = splitString[i].split(":");
            values.add(fieldSplit[1]);
        }
        try {
            entry = new FermiEntry(values.get(0), values.get(1), values.get(2), Double.parseDouble(values.get(3)),
                    Integer.parseInt(values.get(4)), Boolean.parseBoolean(values.get(5)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return entry;
    }
}
