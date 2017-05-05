import java.io.PrintWriter;

/**
 * Created by Acer on 30.03.2017.
 */
public class Data {
    private final String sequenceName = "seq_data";

    private final String tableName = "DATA";

    private final String[] columnsNames = {"ID_DATA", "DAY", "ID_DAY_OF_THE_WEEK", "ID_QUARTER", "ID_MONTH", "ID_YEAR"};

    private String outFilePath;

    private boolean withSequence = false;

    private int startYear;

    private int endYear;

    private int numberOfRecords = 0;

    Data(String outFilePath, int startYear, int endYear) {
        this.outFilePath = outFilePath;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    Data(String outFilePath, int startYear, int endYear, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.startYear = startYear;
        this.endYear = endYear;
        this.withSequence = withSequence;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            int id = 1;
            int yearIterator = 1;
            for(int i = this.startYear; i <= this.endYear; i++) {

                for(int monthIterator = 1; monthIterator <= 12; monthIterator++) {
                    for(int dayIterator = 1; dayIterator <= 31; dayIterator++) {

                        String insert = "INSERT INTO " + this.tableName + "(";
                        String columns = "";
                        for(int j = 0; j < this.columnsNames.length; j++) {
                            columns += this.columnsNames[j] + ",";
                        }
                        insert += columns.substring(0, columns.length()-1);

                        insert += ") VALUES(";
                        insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : Integer.toString(id)) + ",";
                        insert += dayIterator + ",";
                        insert += (dayIterator % 7 == 0 ? 7 : dayIterator % 7) + ",";

                        if(monthIterator < 4) {
                            insert += 1 + ",";
                        }
                        else if(monthIterator >= 4 && monthIterator < 7) {
                            insert += 2 + ",";
                        }
                        else if(monthIterator >= 7 && monthIterator < 10) {
                            insert += 3 + ",";
                        }
                        else if(monthIterator >= 10) {
                            insert += 4 + ",";
                        }

                        insert += monthIterator + ",";

                        insert += (yearIterator % 12 == 0 ? 12 : yearIterator % 12) + ");";
                        out.println(insert);
                        this.numberOfRecords++;
                        id++;
                    }
                }
                yearIterator++;
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfCreatedRecords() {
        return numberOfRecords;
    }
}
