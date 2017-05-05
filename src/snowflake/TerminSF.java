package snowflake;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 09.04.2017.
 */
public class TerminSF {

    private final String tableName = "g2_termin";

    private final String[] columnsNames = {"id_termin", "termin", "data_id"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 10000;

    private Random generator = new Random();

    private int numberOfDates;

    public TerminSF(String outFilePath, int howManyRecordsGenerate, int numberOfDates) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.numberOfDates = numberOfDates;
    }

    public TerminSF(String outFilePath) {
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
                insert += this.generator.nextInt(this.numberOfDates-1)+1;
                insert += ");";
                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
