package star;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 09.04.2017.
 */
public class Credit {

    private final String tableName = "g1_Zaliczenie";

    private final String[] columnsNames = {"id_zal", "id_przed", "nr_albumu", "nr_wykl", "id_sali", "ocena", "id_termin"};

    private int howManyRecordsGenerate = 5000;

    private Random generator = new Random();

    private int maxSubjectsValue;

    private int maxStudentsValue;

    private int maxTeacherValue;

    private int maxClassValue;

    private int maxTerminValue;

    private String manyFilePath;

    public Credit(String manyFilePath, int maxSubjectsValue, int maxStudentsValue, int maxTeacherValue, int maxClassValue, int maxTerminValue, int howManyRecordsGenerate) {
        this.manyFilePath = manyFilePath;
        this.maxSubjectsValue = maxSubjectsValue;
        this.maxStudentsValue= maxStudentsValue;
        this.maxTeacherValue = maxTeacherValue;
        this.maxClassValue = maxClassValue;
        this.maxTerminValue = maxTerminValue;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    public Credit(String manyFilePath, int maxSubjectsValue, int maxStudentsValue, int maxTeacherValue, int maxClassValue, int maxTerminValue) {
        this.manyFilePath = manyFilePath;
        this.maxSubjectsValue = maxSubjectsValue;
        this.maxStudentsValue= maxStudentsValue;
        this.maxTeacherValue = maxTeacherValue;
        this.maxClassValue = maxClassValue;
        this.maxTerminValue = maxTerminValue;
    }


    public void generateAllRecords() {
            this.generateToManyFiles();
    }

    private void generateToManyFiles() {
        int numbersOfFiles = Math.round(this.howManyRecordsGenerate / 20000)+1;
        String[] filesNames = new String[numbersOfFiles];
        for(int i = 0; i < numbersOfFiles; i++) {
            filesNames[i] = this.manyFilePath.substring(0, this.manyFilePath.length()-4) + i + ".sql";
        }
        PrintWriter[] out = new PrintWriter[numbersOfFiles+1];

        int outIterator = 0;
        try{
            out[outIterator] = new PrintWriter(filesNames[outIterator], "UTF-8");

            for(int i = 0; i < this.howManyRecordsGenerate; i++) {
                if(i % 20000 == 0) {
                    out[outIterator].close();
                    outIterator++;
                    out[outIterator] = new PrintWriter(filesNames[outIterator]);
                }

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += (i+1) + ",";
                insert += this.generator.nextInt(this.maxSubjectsValue)+1 + ",";
                insert += this.generator.nextInt(this.maxStudentsValue)+1000 + ",";
                insert += this.generator.nextInt(this.maxTeacherValue)+1 + ",";
                insert += this.generator.nextInt(this.maxClassValue)+1 + ",";
                insert += this.generator.nextInt(4)+2 + ",";
                insert += this.generator.nextInt(this.maxTerminValue);
                insert += ");";

                out[outIterator].println(insert);
                out[outIterator].flush();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setManyFilePath(String manyFilePath) {
        this.manyFilePath = manyFilePath;
    }
}
