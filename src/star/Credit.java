package star;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 09.04.2017.
 */
public class Credit {

    private final String tableName = "g1_Zaliczenie";

    private final String[] columnsNames = {"id_zal", "id_przed", "nr_albumu", "nr_wykl", "ocena", "termin", "nazwa_gr", "Data"};

    private int howManyRecordsGenerate = 100000;

    private Random generator = new Random();

    private String outFilePath;

    public Credit(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.howManyRecordsGenerate; i++) {

                String insert = "";

                insert += (i+1) + ",";
                insert += this.generator.nextInt(10)+1 + ",";
                insert += this.generator.nextInt(10999 - 1000)+1000 + ",";
                insert += this.generator.nextInt(100)+1 + ",";
                insert += this.generator.nextInt(4)+2 + ",";
                insert += this.generator.nextInt(4)+1 + ",";
                insert += "\"GR" + ((i % 5)+1) + "\",";
                insert += (this.generator.nextInt(27)+1) + "/" + (this.generator.nextInt(12)+1) + "/" + (this.generator.nextInt(5)+2011);
                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
