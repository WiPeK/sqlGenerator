import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Acer on 30.03.2017.
 */
public class Channels {
    private final String sequenceName = "seq_channel";

    private final String tableName = "CHANNEL";

    private final String[] columnsNames = {"ID_CHANNEL", "TYPE"};

    private String outFilePath;

    private boolean withSequence = false;

    private ArrayList<String> channelList;


    Channels(String outFilepath, ArrayList<String> channelList, boolean withSequence) {
        this.outFilePath = outFilepath;
        this.channelList = channelList;
        this.withSequence = withSequence;
    }

    Channels(String outFilepath, ArrayList<String> channelList) {
        this.outFilePath = outFilepath;
        this.channelList = channelList;
    }

    public void generateAllRecords() {
        try(PrintWriter out = new PrintWriter(this.outFilePath)) {
            for(int i = 0; i <= this.channelList.size()-1; i++) {
                String insert = "INSERT INTO " + this.tableName + "(" + this.columnsNames[0] + "," + this.columnsNames[1] +") VALUES(" + (this.withSequence ?  this.sequenceName + ".NEXTVAL" : Integer.toString(i+1)) + ",'" + this.channelList.get(i) + "');";
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
