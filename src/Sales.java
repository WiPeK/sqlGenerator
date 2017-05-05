import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 30.03.2017.
 */
public class Sales {

    private final String sequenceName = "seq_sales";

    private final String tableName = "SALES";

    private final String[] columnsNames = {"ID_SALES", "ID_CLIENTS", "ID_EMPLOYEES", "ID_DATA", "ID_PRODUCT", "ID_PAYMENT", "ID_CHANNEL", "QUANTITY", "ID_INVOICE"};

    private String outFilePath;

    private boolean withSequence = false;

    private int howManyRecordsGenerate = 5000;

    private Random generator = new Random();

    private int maxClientsRange;

    private int maxEmployeesRange;

    private int maxDataRange;

    private int maxProductRange;

    private int maxPaymentRange;

    private int maxChannelRange;

    private String manyFilePath;

    Sales(String outFilePath, int maxClientsRange, int maxEmployeesRange, int maxDataRange, int maxProductRange, int maxPaymentRange, int maxChannelRange) {
        this.outFilePath = outFilePath;
        this.maxClientsRange = maxClientsRange;
        this.maxEmployeesRange= maxEmployeesRange;
        this.maxDataRange = maxDataRange;
        this.maxProductRange = maxProductRange;
        this.maxPaymentRange = maxPaymentRange;
        this.maxChannelRange = maxChannelRange;
    }

    Sales(String outFilePath, int maxClientsRange, int maxEmployeesRange, int maxDataRange, int maxProductRange, int maxPaymentRange, int maxChannelRange, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.withSequence = withSequence;
        this.maxClientsRange = maxClientsRange;
        this.maxEmployeesRange= maxEmployeesRange;
        this.maxDataRange = maxDataRange;
        this.maxProductRange = maxProductRange;
        this.maxPaymentRange = maxPaymentRange;
        this.maxChannelRange = maxChannelRange;

    }

    Sales(String outFilePath, int maxClientsRange, int maxEmployeesRange, int maxDataRange, int maxProductRange, int maxPaymentRange, int maxChannelRange, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
        this.maxClientsRange = maxClientsRange;
        this.maxEmployeesRange= maxEmployeesRange;
        this.maxDataRange = maxDataRange;
        this.maxProductRange = maxProductRange;
        this.maxPaymentRange = maxPaymentRange;
        this.maxChannelRange = maxChannelRange;
    }

    Sales(String outFilePath, int maxClientsRange, int maxEmployeesRange, int maxDataRange, int maxProductRange, int maxPaymentRange, int maxChannelRange, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.maxClientsRange = maxClientsRange;
        this.maxEmployeesRange= maxEmployeesRange;
        this.maxDataRange = maxDataRange;
        this.maxProductRange = maxProductRange;
        this.maxPaymentRange = maxPaymentRange;
        this.maxChannelRange = maxChannelRange;
    }

    public void generateAllRecords() {
        if(this.howManyRecordsGenerate <= 5000) {
            try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
                int idIterator = 1;
                int invoiceIterator = 1;
                while(idIterator <= this.howManyRecordsGenerate){

                    int howManyPositionInTransaction = this.generator.nextInt(10)+1;
                    int clientId = this.generator.nextInt(this.maxClientsRange)+1;
                    int employeeId = this.generator.nextInt(this.maxEmployeesRange)+1;
                    int dataId = this.generator.nextInt(this.maxDataRange)+1;
                    int paymentId = this.generator.nextInt(this.maxPaymentRange)+1;
                    int channelId = this.generator.nextInt(this.maxChannelRange)+1;
                    int maxQuantityInTransaction = 10;


                    for(int k = 0; k < howManyPositionInTransaction; k++) {
                        String insert = "INSERT INTO " + this.tableName + "(";
                        String columns = "";
                        for(int j = 0; j < this.columnsNames.length; j++) {
                            columns += this.columnsNames[j] + ",";
                        }

                        insert += columns.substring(0, columns.length()-1);
                        insert += ") VALUES(";
                        insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : idIterator) + ",";

                        insert += clientId + ",";
                        insert += employeeId + ",";
                        insert += dataId + ",";
                        insert += this.generator.nextInt(this.maxProductRange)+1 + ",";
                        insert += paymentId + ",";
                        insert += channelId + ",";
                        insert += this.generator.nextInt(maxQuantityInTransaction)+1 + ",";
                        insert += invoiceIterator;
                        insert += ");";

                        out.println(insert);
                        idIterator++;
                    }
                    invoiceIterator++;
                }
                out.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.howManyRecordsGenerate > 5000) {
            this.generateToManyFiles();
        }
    }

    private void generateToManyFiles() {
        int numbersOfFiles = Math.round(this.howManyRecordsGenerate / 15000)+1;
        String[] filesNames = new String[numbersOfFiles];
        for(int i = 0; i < numbersOfFiles; i++) {
            filesNames[i] = this.manyFilePath.substring(0, this.manyFilePath.length()-4) + i + ".sql";
        }
        PrintWriter[] out = new PrintWriter[numbersOfFiles+1];

        int outIterator = 1;
        try{
            out[outIterator-1] = new PrintWriter(filesNames[outIterator-1], "UTF-8");
            int idIterator = 1;
            int invoiceIterator = 1;
            while(idIterator <= this.howManyRecordsGenerate){

                int howManyPositionInTransaction = this.generator.nextInt(10)+2;
                int clientId = this.generator.nextInt(this.maxClientsRange)+1;
                int employeeId = this.generator.nextInt(this.maxEmployeesRange)+1;
                int dataId = this.generator.nextInt(this.maxDataRange)+1;
                int paymentId = this.generator.nextInt(this.maxPaymentRange)+1;
                int channelId = this.generator.nextInt(this.maxChannelRange)+1;
                int maxQuantityInTransaction = 10;

                for(int k = 0; k < howManyPositionInTransaction; k++) {
                    if(idIterator % 15000 == 0) {
                        out[outIterator-1].close();
                        out[outIterator] = new PrintWriter(filesNames[outIterator]);
                        outIterator++;
                    }
                    String insert = "INSERT INTO " + this.tableName + "(";
                    String columns = "";
                    for(int j = 0; j < this.columnsNames.length; j++) {
                        columns += this.columnsNames[j] + ",";
                    }

                    insert += columns.substring(0, columns.length()-1);
                    insert += ") VALUES(";
                    insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : idIterator) + ",";

                    insert += clientId + ",";
                    insert += employeeId + ",";
                    insert += dataId + ",";
                    insert += this.generator.nextInt(this.maxProductRange-1)+1 + ",";
                    insert += paymentId + ",";
                    insert += channelId + ",";
                    insert += this.generator.nextInt(maxQuantityInTransaction)+1 + ",";
                    insert += invoiceIterator;
                    insert += ");";

                    out[outIterator-1].println(insert);
                    out[outIterator-1].flush();
                    idIterator++;
                }
                invoiceIterator++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setManyFilePath(String manyFilePath) {
        this.manyFilePath = manyFilePath;
    }
}
