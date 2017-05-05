package projekt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Acer on 19.04.2017.
 */
public class Users {
    private final String sequenceName = "USERS_SEQ";

    private final String tableName = "USERS";

    private final String[] columnsNames = {"ID_USER", "EMAIL", "PASSWORD", "NAME", "SURNAME", "PESEL", "CREATE_DATE"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 10000;

    private ArrayList<String> names;

    private ArrayList<String> surnames;

    private Random generator = new Random();

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            for(int i = 1; i <= this.howManyRecordsGenerate; i++) {

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : (i+1)) + ",";

                //name
                String firstUpper = this.names.get(this.generator.nextInt(this.names.size()-1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                String tmpName = firstUpper.trim();

                //surname
                firstUpper = this.surnames.get(this.generator.nextInt(this.surnames.size() -1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                String tmpSurname = firstUpper.trim();

                //email
                insert += "'" + tmpName.toLowerCase() + "." + tmpSurname.toLowerCase() + "@gmail.com',";
                insert += "'" + Hasher.hashSHA512("12345") + "',";
                insert += "'" + tmpName + "',";
                insert += "'" + tmpSurname + "',";
                insert += "'" + this.generatePesel() + "',";
                insert += "to_date('01/01/2000', 'DD/MM/YYYY')";

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String generatePesel() {
        StringBuilder stringBuilder = new StringBuilder(11);
        for(int i = 0; i < 11; i++) {
            stringBuilder.append(this.generator.nextInt(10));
        }
        return stringBuilder.toString();
    }

    public Users(String outFilePath, ArrayList<String> names, ArrayList<String> surnames) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
    }

    public Users(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.names = names;
        this.surnames = surnames;
    }

    public Users(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.names = names;
        this.surnames = surnames;
        this.withSequence = withSequence;
    }
}
