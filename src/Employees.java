import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Acer on 30.03.2017.
 */
public class Employees {
    private final String sequenceName = "seq_employees";

    private final String tableName = "EMPLOYEES";

    private final String[] columnsNames = {"ID_EMPLOYEES", "NAME", "SURNAME", "ADDRESS", "PHONE_NUMBER", "EMAIL", "DATE_OF_EMPLOYMENT", "SALARY"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 300;

    private ArrayList<String> names;

    private ArrayList<String> surnames;

    private ArrayList<String> addresses;

    private ArrayList<String> phones;

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
                String tmpName = firstUpper;
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "'" + firstUpper + "',";

                firstUpper = this.surnames.get(this.generator.nextInt(this.surnames.size() -1));
                String tmpSurname = firstUpper;
                firstUpper = Character.toUpperCase(firstUpper.charAt(0)) + firstUpper.substring(1);
                insert += "'" + firstUpper + "',";

                //generate address from arraylist
                String address = this.addresses.get(this.generator.nextInt(this.addresses.size()-1)) + " ";
                //generate address number
                address += Integer.toString(this.generator.nextInt(500)) + " ";
                //generate zip code
                address += Integer.toString(this.generator.nextInt(99)) + "-" + Integer.toString(this.generator.nextInt(999)) + " ";
                address += this.addresses.get(this.generator.nextInt(this.addresses.size()-1));
                insert += "'" + address + "',";

                //generate phone number
                insert += "'+48 " + this.phones.get(this.generator.nextInt(this.phones.size()-1)) + "',";

                //generate email
                insert += "'" + tmpName + "." + tmpSurname + "@gmail.com',";

                int month = this.generator.nextInt(12)+1;
                int day = this.generator.nextInt(27)+1;
                int year = this.generator.nextInt(11) + 2006;

                insert += "to_date('" + (day < 10 ? "0" + Integer.toString(day) : Integer.toString(day)) + "/"  + (month < 10 ? "0" + Integer.toString(month) : Integer.toString(month)) + "/" + Integer.toString(year) + "', 'DD/MM/YYYY'),";

                double minSalary = 2000.00;
                double maxSalary = 4500.00;
                insert += Double.toString(Math.round(minSalary + (maxSalary - minSalary) * this.generator.nextDouble()));

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    Employees(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
    }

    Employees(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    Employees(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
    }

    Employees(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
        this.withSequence = withSequence;
    }
}
