package snowflake;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Acer on 10.04.2017.
 */
public class Group {

    private final String tableName = "g2_grupa";

    private final String[] columnsNames = {"nr_gr", "nazwa"};

    private String outFilePath;

    private final String[] groups = {"1ID11A", "1ID11B", "1ID12A", "1ID12B", "1ID13A", "1ID13B", "1ID14A", "1ID14B", "1ID15A", "1ID15B", "1ID16A", "1ID16B", "2ID11A", "2ID11B", "2ID12A", "2ID12B", "2ID13A", "2ID13B", "2ID14A", "2ID14B", "2ID15A", "2ID15B", "3ID11A", "3ID11B", "3ID12A", "3ID12B", "3ID13A", "3ID13B", "3ID14A", "3ID14B", "4ID11A", "4ID11B", "4ID12A", "4ID12B", "4ID13A", "4ID13B"};

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath, "UTF-8")) {
            for(int i = 0; i < this.groups.length; i++) {

                String insert = "INSERT INTO " + this.tableName + "(";
                String columns = "";
                for(int j = 0; j < this.columnsNames.length; j++) {
                    columns += this.columnsNames[j] + ",";
                }

                insert += columns.substring(0, columns.length()-1);
                insert += ") VALUES(";
                insert += (i+1) + ",";

                insert += "'" + this.groups[i] + "'";

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Group(String outFilePath) {
        this.outFilePath = outFilePath;
    }

}
