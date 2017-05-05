import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Acer on 30.03.2017.
 */
public class Author {

    private final String sequenceName = "seq_author";

    private final String tableName = "AUTHOR";

    private final String[] columnsNames = {"ID_AUTHOR", "NAME", "SURNAME", "ALIAS"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 1000;

    private ArrayList<String> names;

    private ArrayList<String> surnames;

    private ArrayList<String> nicknames;

    private Random generator = new Random();

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.howManyRecordsGenerate; i++) {

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += "'" + (this.withSequence ? this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + "',";

                String firstUpper = this.names.get(this.generator.nextInt(this.names.size()-1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "'" + firstUpper + "',";

                firstUpper = this.surnames.get(this.generator.nextInt(this.surnames.size() -1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "'" + firstUpper + "',";

                firstUpper = this.generator.nextBoolean() ? this.nicknames.get(this.generator.nextInt(this.nicknames.size()-1))  :  "null";
                if(!firstUpper.equals("null")) {
                    firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                    firstUpper = "'" + firstUpper + "'";
                }

                insert += firstUpper;
                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    Author(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> nicknames) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.nicknames = nicknames;
    }

    Author(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> nicknames, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.nicknames = nicknames;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    Author(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> nicknames, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.nicknames = nicknames;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
    }

    Author(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> nicknames, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.nicknames = nicknames;
        this.withSequence = withSequence;
    }
}
