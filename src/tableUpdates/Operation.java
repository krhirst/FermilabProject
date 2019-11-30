package tableUpdates;

import javax.swing.*;
import java.sql.Time;
import java.util.Date;

public class Operation {
    private Time time;

    public Operation(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }
}
