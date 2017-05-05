import java.io.PrintWriter;

/**
 * Created by Acer on 30.03.2017.
 */
public class DayOfWeek {

    private final String sequenceName = "seq_day_of_week";

    private final String tableName = "DAY_OF_WEEK";

    private final String[] columnsNames = {"ID_DAY_OF_THE_WEEK", "NAME"};

    private String outFilePath;

    private boolean withSequence = false;

    DayOfWeek(String outFilepath, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.withSequence = withSequence;
    }

    DayOfWeek(String outFilepath) {
        this.outFilePath = outFilepath;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < 7; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",'" + Days.values()[i] + "');";
                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getColumnsNames() {
        return columnsNames;
    }

    private enum Days {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}
