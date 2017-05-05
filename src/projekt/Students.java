package projekt;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 19.04.2017.
 */
public class Students {
    private final String sequenceName = "STUDENTS_SEQ";

    private final String tableName = "STUDENTS";

    private final String[] columnsNames = {"ID_STUDENT", "ID_USER", "ID_CLASS"};

    private String outFilePath;

    private int startUserId = 100;

    private int howManyRecordsGenerate = 9900;

    private boolean withSequence = false;

    private Random generator = new Random();

    private int numberOfClasses = 48;

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
                insert += this.generator.nextInt(this.numberOfClasses)+1;

                insert += ");";

                out.println(insert);
                uid++;
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Students(String outFilePath, int startUserId, int numberOfClasses) {
        this.outFilePath = outFilePath;
        this.startUserId = startUserId;
        this.numberOfClasses = numberOfClasses;
    }

    public Students(String outFilePath, int startUserId, int numberOfClasses, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.startUserId = startUserId;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.numberOfClasses = numberOfClasses;
    }

    public Students(String outFilePath, int startUserId, int numberOfClasses, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.startUserId = startUserId;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
        this.numberOfClasses = numberOfClasses;
    }
}
