package star;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 09.04.2017.
 */
public class Termin {

    private final String tableName = "g1_termin";

    private final String[] columnsNames = {"id_termin", "termin", "data"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 10000;

    private Random generator = new Random();

    public Termin(String outFilePath, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    public Termin(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.howManyRecordsGenerate-1; i++) {
                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }
                insert += columns.substring(0, columns.length()-1);

                insert += ") VALUES(";
                insert += (i+1) + ",";
                insert += "'" + this.generator.nextInt(4) + "',";
                int day = this.generator.nextInt(27)+1;
                int month = this.generator.nextInt(12)+1;
                int year = this.generator.nextInt(10)+2006;
                insert += "to_date('" + day + "/" + month + "/" + year + "', 'DD/MM/YYYY')";
                insert += ");";
                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
