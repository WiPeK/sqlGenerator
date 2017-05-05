import java.io.PrintWriter;

/**
 * Created by Acer on 30.03.2017.
 */
public class Year {
    private final String sequenceName = "seq_year";

    private final String tableName = "YEAR";

    private final String[] columnsNames = {"ID_YEAR", "YEAR"};

    private String outFilePath;

    private boolean withSequence = false;

    private int startYear;

    private int endYear;

    Year(String outFilepath, int startYear, int endYear, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.startYear = startYear;
        this.endYear = endYear;
        this.withSequence = withSequence;
    }

    Year(String outFilepath, int startYear, int endYear) {
        this.outFilePath = outFilepath;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            int j = 1;
            for(int i = this.startYear; i <= this.endYear; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(j)) + "," + Integer.toString(i) + ");";
                j++;
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
}
