import myReader.FileReader;
import snowflake.*;

import java.util.ArrayList;

/**
 * Created by Krzysztof Adamczyk on 29.03.2017.
 */
public class Generator {

    private final static ArrayList<String> namesList = FileReader.readFile("./resources/in/imiona.txt");

    private final static ArrayList<String> surnamesList = FileReader.readFile("./resources/in/nazw.txt");

    private final static ArrayList<String> productsList = FileReader.readFile("./resources/in/ksia.txt");

    private final static ArrayList<String> addressesList = FileReader.readFile("./resources/in/miejsc.txt");

    private final static ArrayList<String> nicknamesList = FileReader.readFile("./resources/in/nicknames.txt");

    private final static ArrayList<String> phonesList = FileReader.readFile("./resources/in/phones.txt");

    private final static ArrayList<String> channelsList = FileReader.readFile("./resources/in/channel.txt");

    private final static ArrayList<String> paymentList = FileReader.readFile("./resources/in/payment.txt");

    private final static ArrayList<String> categoriesList = FileReader.readFile("./resources/in/kat.txt");

    private final static ArrayList<String> producersList = FileReader.readFile("./resources/in/prod.txt");


    public static void main(String[] args) {
        int numberOfAuthorRecords = 1000;
        int numberOfClientsRecords = 5000;
        int numberOfEmployeesRecords = 300;
        int numberOfChannelsRecords = channelsList.size();
        int numberOfPaymentsRecords = paymentList.size();
        int numberOfProductsRecords = productsList.size();
//        DayOfWeek dayOfWeek = new DayOfWeek("./resources/out/5_dayOfWeekInserts.sql");
//        dayOfWeek.generateAllRecords();
//        Author author = new Author("./resources/out/1_authorInserts.sql", namesList, surnamesList, nicknamesList, numberOfAuthorRecords);
//        author.generateAllRecords();
//        Clients clients = new Clients("./resources/out/4_clientsInserts.sql", namesList, surnamesList, addressesList, phonesList, numberOfClientsRecords);
//        clients.generateAllRecords();
//        Employees employees = new Employees("./resources/out/11_employeesInserts.sql", namesList, surnamesList, addressesList, phonesList, numberOfEmployeesRecords);
//        employees.generateAllRecords();
//        Month month = new Month("./resources/out/8_monthsInserts.sql");
//        month.generateAllRecords();
//        Quarter quarter = new Quarter("./resources/out/7_quarterInserts.sql");
//        quarter.generateAllRecords();
//        Year year = new Year("./resources/out/6_yearsInserts.sql", 2006, 2017);
//        year.generateAllRecords();
//        Channels channels = new Channels("./resources/out/3_channelsInserts.sql", channelsList);
//        channels.generateAllRecords();
//        Payments payments = new Payments("./resources/out/10_paymentsInserts.sql", paymentList);
//        payments.generateAllRecords();
//        Categories categories = new Categories("./resources/out/2_categoriesInserts.sql", categoriesList);
//        categories.generateAllRecords();
//        Producers producers = new Producers("./resources/out/12_producersInserts.sql", producersList, addressesList);
//        producers.generateAllRecords();
//        Products products = new Products("./resources/out/13_productsInserts.sql", productsList, categoriesList.size()-1, producersList.size()-1, numberOfAuthorRecords);
//        products.generateAllRecords();
//        Data data = new Data("./resources/out/9_datasInserts.sql", 2006, 2017);
//        data.generateAllRecords();
//        int numberOfDataRecords = data.getNumberOfCreatedRecords();
//        int numberToGenerate = 1000000;
//        Sales sales = new Sales("./resources/out/15_salesInserts.sql", numberOfClientsRecords, numberOfEmployeesRecords, numberOfDataRecords, numberOfProductsRecords, numberOfPaymentsRecords, numberOfChannelsRecords, numberToGenerate);
//        if(numberToGenerate > 5000) {
//            sales.setManyFilePath("./resources/out/15_sales/salesInserts.sql");
//        }
//        sales.generateAllRecords();

        int howManyStudents = 10000;
//        Student student = new Student("./resources/star/g1_student_insert.sql", namesList, surnamesList, howManyStudents);
//        student.generateAllRecords();
        int howManyTeachers = 100;
//        Teacher teacher = new Teacher("./resources/star/g1_wykladowca_insert.sql", namesList, surnamesList, howManyTeachers);
//        teacher.generateAllRecords();
        int howManyTermins = 10000;
//        Termin termin = new Termin("./resources/star/g1_termin_insert.sql", howManyTermins);
//        termin.generateAllRecords();
        int howManySubjects = 10;
        int howManyClasses = 15;
        int howManyCredits = 100000;
//        Credit credit = new Credit("./resources/star/g1_credit_insert.sql", howManySubjects, howManyStudents, howManyTeachers, howManyClasses, howManyTermins-1, howManyCredits);
//        credit.generateAllRecords();

//        Group group = new Group("./resources/out/snowflake/g2_group_insert.sql");
//        group.generateAllRecords();
        int numberOfGroups = 36;
//        snowflake.Data data = new Data("./resources/out/snowflake/g2_data_insert.sql", 2006, 2016);
//        data.generateAllRecords();
        int numberOfDates = 3697;
//        TerminSF terminSF = new TerminSF("./resources/out/snowflake/g2_termin_insert.sql", howManyTermins, numberOfDates);
//        terminSF.generateAllRecords();
        StudentSF studentSF = new StudentSF("./resources/out/snowflake/g2_student_insert.sql", namesList, surnamesList, numberOfGroups, howManyStudents);
        studentSF.generateAllRecords();

    }
}
