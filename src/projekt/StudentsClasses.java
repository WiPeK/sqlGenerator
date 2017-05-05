package projekt;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 24.04.2017.
 */
public class StudentsClasses {

    private final String sequenceName = "STUDENTS_CLASSES_SEQ";

    private final String tableName = "STUDENTS_CLASSES";

    private final String[] columnsNames = {"ID_STUDENTS_CLASSES", "ID_STUDENT", "ID_CLASS"};

    private String outFilePath;

    private int maxIdStudent = 1000;

    private boolean withSequence = false;

    private Random generator = new Random();

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            int id = 1;
            for(int i = 0; i < 48; i++) {
                for(int s = 0; s < 10; s++) {
                    String insert = "INSERT INTO " + this.tableName + "(";
                    String columns = "";
                    for(int j = 0; j < this.columnsNames.length; j++) {
                        columns += this.columnsNames[j] + ",";
                    }

                    insert += columns.substring(0, columns.length()-1);
                    insert += ") VALUES(";
                    insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : id) + ",";
                    insert += this.generator.nextInt(this.maxIdStudent)+1 + ",";
                    insert += (i+1);

                    insert += ");";

                    out.println(insert);
                    id++;
                }

            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public StudentsClasses(String outFilePath) {
        this.outFilePath = outFilePath;
    }
}
