package snowflake;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Acer on 09.04.2017.
 */
public class StudentSF {

    private final String tableName = "g2_student";

    private final String[] columnsNames = {"nr_albumu", "nazwisko", "imie", "wiek", "nr_gr"};

    private String outFilePath;

    private int howManyRecordsGenerate = 10000;

    private ArrayList<String> names;

    private ArrayList<String> surnames;

    private Random generator = new Random();

    private int numberOfGroups;

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
                insert += (i+1000) + ",";

                String firstUpper = this.surnames.get(this.generator.nextInt(this.surnames.size() -1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "'" + firstUpper + "',";

                firstUpper = this.names.get(this.generator.nextInt(this.names.size()-1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "'" + firstUpper + "',";

                insert += (this.generator.nextInt(10)+20) + ",";

                int groupNumber = this.generator.nextInt(this.numberOfGroups-1)+1;
                insert += groupNumber+1;

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public StudentSF(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, int numberOfGroups) {
        this.outFilePath = outFilePath;
        this.names = names.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.surnames = surnames.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.numberOfGroups = numberOfGroups;
    }

    public StudentSF(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, int numberOfGroups, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.names = names.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.surnames = surnames.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.numberOfGroups = numberOfGroups;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }
}
