package projekt;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Acer on 19.04.2017.
 */
public class Substitutes {

    private final String sequenceName = "SUBSTITUTES_SEQ";

    private final String tableName = "SUBSTITUTES";

    private final String[] columnsNames = {"ID_SUBSTITUTE", "\"BODY\"", "\"DATE\"", "ID_ADMIN"};

    private String outFilePath;

    private int maxIdAdmin = 5;

    private int howManyRecordsGenerate = 50;

    private boolean withSequence = false;

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
                insert += (this.withSequence ? this.sequenceName + ".NEXTVAL" : (i+1)) + ",";

                insert += "'Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker',";

                int month = this.generator.nextInt(12)+1;
                int day = this.generator.nextInt(27)+1;
                int year = this.generator.nextInt(17) + 2000;

                insert += "to_date('" + (day < 10 ? "0" + Integer.toString(day) : Integer.toString(day)) + "/"  + (month < 10 ? "0" + Integer.toString(month) : Integer.toString(month)) + "/" + Integer.toString(year) + "', 'DD/MM/YYYY'),";

                insert += this.generator.nextInt(this.maxIdAdmin)+1;

                insert += ");";

                out.println(insert);
            }
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Substitutes(String outFilePath, int maxIdAdmin) {
        this.outFilePath = outFilePath;
        this.maxIdAdmin = maxIdAdmin;
    }

    public Substitutes(String outFilePath, int maxIdAdmin, int howManyRecordsGenerate) {
        this.outFilePath = outFilePath;
        this.maxIdAdmin = maxIdAdmin;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
    }

    public Substitutes(String outFilePath, int maxIdAdmin, int howManyRecordsGenerate, boolean withSequence) {
        this.outFilePath = outFilePath;
        this.maxIdAdmin = maxIdAdmin;
        this.howManyRecordsGenerate = howManyRecordsGenerate;
        this.withSequence = withSequence;
    }
}
