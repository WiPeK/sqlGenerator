package projekt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Acer on 19.04.2017.
 */
public class Teachers {
    private final String sequenceName = "TEACHERS_SEQ";

    private final String tableName = "TEACHERS";

    private final String[] columnsNames = {"ID_TEACHER", "ID_USER", "TITLE"};

    private String outFilePath;

    private int startUserId = 6;

    private int howManyRecordsGenerate = 50;

    private boolean withSequence = false;

    private Random generator = new Random();

    private String[] titles = {"dr", "mgr", "mgr inż.", "inż.", "brak"};

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            int uid = startUserId;
            for(int i = 0; i < this.howManyRecordsGenerate; i++) {

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : (i+1)) + ",";

                insert += uid + ",";
                insert += "'" + this.titles[this.generator.nextInt(this.titles.length)] + "'";

                insert += ");";

                out.println(insert);
                uid++;
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Teachers(String outFilePath, int startUserId) {
        this.outFilePath = outFilePath;
        this.startUserId = startUserId;
    }

    public Teachers(String outFilePath, int startUserId, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.startUserId = startUserId;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    public Teachers(String outFilePath, int startUserId, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.startUserId = startUserId;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
    }
}
