import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Acer on 30.03.2017.
 */
public class Clients {

    private final String sequenceName = "seq_clients";

    private final String tableName = "CLIENTS";

    private final String[] columnsNames = {"ID_CLIENTS", "NAME", "SURNAME", "ADDRESS", "PHONE", "EMAIL"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 1000;

    private ArrayList<String> names;

    private ArrayList<String> surnames;

    private ArrayList<String> addresses;

    private ArrayList<String> phones;

    private Random generator = new Random();

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
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
                insert += "'" + tmpName + "." + tmpSurname + "@gmail.com'";

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    Clients(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
    }

    Clients(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    Clients(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
    }

    Clients(String outFilePath, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> addresses, ArrayList<String> phones, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.names = names;
        this.surnames = surnames;
        this.addresses = addresses;
        this.phones = phones;
        this.withSequence = withSequence;
    }
}
