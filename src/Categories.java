import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Acer on 30.03.2017.
 */
public class Categories {
    private final String sequenceName = "seq_category";

    private final String tableName = "CATEGORY";

    private final String[] columnsNames = {"ID_CATEGORY", "NAME"};

    private String outFilePath;

    private boolean withSequence = false;

    private ArrayList<String> categoriesList;


    Categories(String outFilepath, ArrayList<String> categoriesList, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.categoriesList = categoriesList;
        this.withSequence = withSequence;
    }

    Categories(String outFilepath, ArrayList<String> categoriesList) {
        this.outFilePath = outFilepath;
        this.categoriesList = categoriesList;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            for(int i = 0; i <= this.categoriesList.size()-1; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",'" + this.categoriesList.get(i) + "');";
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
