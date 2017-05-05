package projekt;

import java.io.PrintWriter;

/**
 * Created by Acer on 19.04.2017.
 */
public class Semesters {
    private final String sequenceName = "SEMESTERS_SEQ";

    private final String tableName = "SEMESTERS";

    private final String[] columnsNames = {"ID_SEMESTER", "START_DATE", "END_DATE", "ID_SCHOOL_YEAR"};

    private String outFilePath;

    private boolean withSequence = false;

    private int startYear = 2000;

    private int endYear = 2017;

    private int generatedRecords = 0;

    private String firstSemesterStartDate = "01/09/";

    private String firstSemesterEndDate = "15/01/";

    private String secondSemesterStartDate = "16/01/";

    private String secondSemesterEndDate = "30/06/";

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            int id = 1;
            int yearIterator = 1;
            for(int i = this.startYear; i < this.endYear; i++) {
                for(int nrSem = 1; nrSem < 3; nrSem++) {
                    String insert = "INSERT INTO " + this.tableName + "(";
                    String columns = "";
                    for(int j = 0; j < this.columnsNames.length; j++) {
                        columns += this.columnsNames[j] + ",";
                    }

                    insert += columns.substring(0, columns.length()-1);
                    insert += ") VALUES(";
                    insert += this.withSequence ? this.sequenceName + ".NEXTVAL" : id + ",";

                    if(nrSem == 1) {
                        insert += "to_date('" + this.firstSemesterStartDate + i + "', 'DD/MM/YYYY'),";
                        insert += "to_date('" + this.firstSemesterEndDate + (i+1) + "', 'DD/MM/YYYY'),";
                    }
                    else if(nrSem == 2) {
                        insert += "to_date('" + this.secondSemesterStartDate + (i+1) + "', 'DD/MM/YYYY'),";
                        insert += "to_date('" + this.secondSemesterEndDate + (i+1) + "', 'DD/MM/YYYY'),";
                    }
                    insert += yearIterator;

                    insert += ");";
                    out.println(insert);
                    id++;
                }
                yearIterator++;
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

    public Semesters(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public Semesters(String outFilePath, int startYear, int endYear) {
        this.outFilePath = outFilePath;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Semesters(String outFilePath, int startYear, int endYear, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.withSequence = withSequence;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
