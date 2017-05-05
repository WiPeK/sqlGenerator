package projekt;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 19.04.2017.
 */
public class Classes {

    private final String sequenceName = "CLASSES_SEQ";

    private final String tableName = "CLASSES";

    private final String[] columnsNames = {"ID_CLASS", "NAME", "ID_SEMESTER"};

    private String outFilePath;

    private int howManyRecordsGenerate = 50;

    private boolean withSequence = false;

    private Random generator = new Random();

    private int maxNrClass = 3;

    private int maxSubclasses = 2;

    private String[] classLetters = {"A", "B"};

    private int numberOfSemesters = 8;

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            int id = 1;
            for(int s = 1; s <= this.numberOfSemesters; s++) {
                for(int i = 0; i < maxNrClass; i++) {
                    for(int k = 0; k < maxSubclasses; k++) {
                        String insert = "INSERT INTO " + this.tableName + "(";
                        String columns = "";
                        for(int j = 0; j < this.columnsNames.length; j++) {
                            columns += this.columnsNames[j] + ",";
                        }

                        insert += columns.substring(0, columns.length()-1);
                        insert += ") VALUES(";
                        insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : id) + ",";

                        insert += "'" + (i+1) + this.classLetters[k] + "',";

                        insert += s;

                        insert += ");";

                        out.println(insert);
                        id++;
                    }
                }
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Classes(String outFilePath, int maxNrClass, int maxSubclasses) {
        this.outFilePath = outFilePath;
        this.maxNrClass = maxNrClass;
        this.maxSubclasses = maxSubclasses;
    }

    public Classes(String outFilePath, int maxNrClass, int maxSubclasses, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
        this.maxNrClass = maxNrClass;
        this.maxSubclasses = maxSubclasses;
    }
}
