import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Acer on 30.03.2017.
 */
public class Payments {
    private final String sequenceName = "seq_payment";

    private final String tableName = "PAYMENT";

    private final String[] columnsNames = {"ID_PAYMENT", "TYPE"};

    private String outFilePath;

    private boolean withSequence = false;

    private ArrayList<String> paymentsList;


    Payments(String outFilepath, ArrayList<String> paymentsList, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.paymentsList = paymentsList;
        this.withSequence = withSequence;
    }

    Payments(String outFilepath, ArrayList<String> paymentsList) {
        this.outFilePath = outFilepath;
        this.paymentsList = paymentsList;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i <= this.paymentsList.size()-1; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",'" + this.paymentsList.get(i) + "');";
                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getColumnsNames() {
        return columnsNames;
    }
}
