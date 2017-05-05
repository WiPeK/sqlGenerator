package projekt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Acer on 20.04.2017.
 */
public class Subjects {

    private final String sequenceName = "SUBJECTS_SEQ";

    private final String tableName = "SUBJECTS";

    private final String[] columnsNames = {"ID_SUBJECT", "NAME"};

    private String outFilePath;

    private boolean withSequence = false;

    private ArrayList<String> subjectsList;

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            for(int i = 0; i < this.subjectsList.size(); i++) {
                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : (i+1)) + ",";
                insert += "'" + this.subjectsList.get(i) + "'";
                insert += ");";
                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Subjects(String outFilePath, ArrayList<String> subjectsList) {
        this.outFilePath = outFilePath;
        this.subjectsList = subjectsList;
    }

    public Subjects(String outFilePath, ArrayList<String> subjectsList, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.withSequence = withSequence;
        this.subjectsList = subjectsList;
    }
}
