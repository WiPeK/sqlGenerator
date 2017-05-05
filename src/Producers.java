import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Acer on 30.03.2017.
 */
public class Producers {
    private final String sequenceName = "seq_producer";

    private final String tableName = "PRODUCER";

    private final String[] columnsNames = {"ID_PRODUCER", "NAME", "ADDRESS"};

    private String outFilePath;

    private boolean withSequence = false;

    private ArrayList<String> addresses;

    private ArrayList<String> producersList;

    private Random generator = new Random();

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.producersList.size(); i++) {

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }
                insert += columns.substring(0, columns.length()-1);

                insert += ") VALUES(";
                insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",";

                insert += "'" + this.producersList.get(i) + "',";

                //generate address from arraylist
                String address = this.addresses.get(this.generator.nextInt(this.addresses.size()-1)) + " ";
                //generate address number
                address += Integer.toString(this.generator.nextInt(500)) + " ";
                //generate zip code
                address += Integer.toString(this.generator.nextInt(99)) + "-" + Integer.toString(this.generator.nextInt(999)) + " ";
                address += this.addresses.get(this.generator.nextInt(this.addresses.size()-1));
                insert += "'" + address + "'";

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    Producers(String outFilePath, ArrayList<String> producersList, ArrayList<String> addresses) {
        this.outFilePath = outFilePath;
        this.producersList = producersList;
        this.addresses = addresses;
    }

    Producers(String outFilePath, ArrayList<String> producersList, ArrayList<String> addresses, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.producersList = producersList;
        this.addresses = addresses;
        this.withSequence = withSequence;
    }
}
