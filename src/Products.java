import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Acer on 30.03.2017.
 */
public class Products {

    private final String sequenceName = "seq_product";

    private final String tableName = "PRODUCT";

    private final String[] columnsNames = {"ID_PRODUCT", "ID_CATEGORY", "ID_PRODUCER", "ID_AUTHOR", "NAME", "PRICE"};

    private String outFilePath;

    private boolean withSequence = false;

    private ArrayList<String> productsList;

    private int categoryMaxRange;

    private int producerMaxRange;

    private int authorMaxRange;

    private Random generator = new Random();

    Products(String outFilePath, ArrayList<String> productsList, int categoryMaxRange, int producerMaxRange, int authorMaxRange) {
        this.outFilePath = outFilePath;
        this.productsList = productsList;
        this.categoryMaxRange = categoryMaxRange;
        this.producerMaxRange = producerMaxRange;
        this.authorMaxRange = authorMaxRange;
    }

    Products(String outFilePath, ArrayList<String> productsList, int categoryMaxRange, int producerMaxRange, int authorMaxRange, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.productsList = productsList;
        this.categoryMaxRange = categoryMaxRange;
        this.producerMaxRange = producerMaxRange;
        this.authorMaxRange = authorMaxRange;
        this.withSequence = withSequence;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.productsList.size(); i++) {

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",";

                insert += Integer.toString(this.generator.nextInt(this.categoryMaxRange)+1) + ",";
                insert += Integer.toString(this.generator.nextInt(this.producerMaxRange)+1) + ",";
                insert += Integer.toString(this.generator.nextInt(this.authorMaxRange)+1) + ",";
                insert += "'" + this.productsList.get(i) + "',";

                double minPrice = 1.00;
                double maxPrice = 100.00;
                insert += Double.toString(Math.round(minPrice + (maxPrice - minPrice) * this.generator.nextDouble()));

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
