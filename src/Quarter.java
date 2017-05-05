import java.io.PrintWriter;

/**
 * Created by Acer on 30.03.2017.
 */
public class Quarter {
    private final String sequenceName = "seq_quarter";

    private final String tableName = "QUARTER";

    private final String[] columnsNames = {"ID_QUARTER", "NUM_OF_Q"};

    private String outFilePath;

    private boolean withSequence = false;

    Quarter(String outFilepath, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.withSequence = withSequence;
    }

    Quarter(String outFilepath) {
        this.outFilePath = outFilepath;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < 4; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + "," + Integer.toString(i+1) + ");";
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
