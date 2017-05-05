package snowflake;

import java.io.PrintWriter;

/**
 * Created by Acer on 10.04.2017.
 */
public class Data {

    private final String tableName = "g2_data";

    private final String[] columnsNames = {"data_id", "data_zal"};

    private String outFilePath;

    private int startYear;

    private int endYear;

    private int numberOfRecords = 0;

    public Data(String outFilePath, int startYear, int endYear) {
        this.outFilePath = outFilePath;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            int id = 1;
            for(int i = this.startYear; i <= this.endYear; i++) {

                for(int monthIterator = 1; monthIterator <= 12; monthIterator++) {
                    for(int dayIterator = 1; dayIterator <= 28; dayIterator++) {

                        String insert = "INSERT INTO " + this.tableName + "(";
                        String columns = "";
                        for(int j = 0; j < this.columnsNames.length; j++) {
                            columns += this.columnsNames[j] + ",";
                        }
                        insert += columns.substring(0, columns.length()-1);

                        insert += ") VALUES(";
                        insert += (id+1) + ",";
                        insert += "to_date('" + (dayIterator < 10? "0" + dayIterator : dayIterator) + "/" + (monthIterator < 10? "0" + monthIterator: monthIterator) + "/" + i + "', 'DD/MM/YYYY')";
                        insert += ");";
                        out.println(insert);
                        this.numberOfRecords++;
                        id++;
                    }
                }
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
