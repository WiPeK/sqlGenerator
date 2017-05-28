package star;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Student {

    private final String tableName = "g1_student";

    private final String[] columnsNames = {"nr_albumu", "nazwisko", "imie", "wiek"};

    private String outFilePath;

    private int howManyRecordsGenerate = 10000;

    private ArrayList<String> names;

    private ArrayList<String> surnames;

    private Random generator = new Random();

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.howManyRecordsGenerate; i++) {

                String insert = "";

                insert += (i+1000) + ",";

                String firstUpper = this.surnames.get(this.generator.nextInt(this.surnames.size() -1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "\"" + firstUpper + "\",";

                firstUpper = this.names.get(this.generator.nextInt(this.names.size()-1));
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "\"" + firstUpper + "\",";

                insert += (this.generator.nextInt(10)+20);

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Student(String outFilePath, ArrayList<String> names, ArrayList<String> surnames) {
        this.outFilePath = outFilePath;
        this.names = names.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.surnames = surnames.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
    }

    public Student(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.names = names.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.surnames = surnames.stream().filter(s -> s.length() < 15).collect(Collectors.toCollection(ArrayList::new));
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }
}
