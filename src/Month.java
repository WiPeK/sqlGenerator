import java.io.PrintWriter;

/**
 * Created by Acer on 30.03.2017.
 */
public class Month {
    private final String sequenceName = "seq_month";

    private final String tableName = "MONTH";

    private final String[] columnsNames = {"ID_MONTH", "MONTH"};

    private String outFilePath;

    private boolean withSequence = false;

    Month(String outFilepath, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.withSequence = withSequence;
    }

    Month(String outFilepath) {
        this.outFilePath = outFilepath;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < 12; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",'" + Months.values()[i] + "');";
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

    private enum Months {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }
}
