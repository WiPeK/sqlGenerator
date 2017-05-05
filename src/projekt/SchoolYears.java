package projekt;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 19.04.2017.
 */
public class SchoolYears {

    private final String sequenceName = "SCHOOL_YEARS_SEQ";

    private final String tableName = "SCHOOL_YEARS";

    private final String[] columnsNames = {"ID_SCHOOL_YEAR", "NAME", "START_DATE", "END_DATE"};

    private String outFilePath;

    private boolean withSequence = false;

    private int startYear = 2000;

    private int endYear = 2017;

    private int generatedRecords = 0;

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            int id = 1;
            for(int i = this.startYear; i < this.endYear; i++) {
                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += this.withSequence ? this.sequenceName + ".NEXTVAL" : id + ",";

                insert += "'ROK SZKOLNY " + i + "/" + (i+1) + "',";
                insert += "to_date('01/09/" + i + "', 'DD/MM/YYYY'),";
                insert += "to_date('30/06/" + (i+1) + "', 'DD/MM/YYYY')";

                insert += ");";
                out.println(insert);
                id++;
            }
            this.generatedRecords = id;
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getGeneratedRecords() {
        return generatedRecords;
    }

    public SchoolYears(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public SchoolYears(String outFilePath, int startYear, int endYear) {
        this.outFilePath = outFilePath;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public SchoolYears(String outFilePath, boolean withSequence, int startYear, int endYear) {
        this.outFilePath = outFilePath;
        this.withSequence = withSequence;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
