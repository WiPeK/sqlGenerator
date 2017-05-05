package projekt;

import java.util.ArrayList;
import myReader.FileReader;


/**
 * Created by Acer on 19.04.2017.
 */
public class Creator {

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

    private final static ArrayList<String> subjectsList = FileReader.readFile("./resources/in/subjects.txt");

    public static void main(String[] args) {
        int startYear = 2013;
        int endYear = 2017;
        SchoolYears schoolYears = new SchoolYears("./resources/out/projekt/school_years_insert.sql", startYear, endYear);
        schoolYears.generateAllRecords();
        int generatedSchoolYearsRecords = schoolYears.getGeneratedRecords();
        Semesters semesters = new Semesters("./resources/out/projekt/semesters_insert.sql", startYear, endYear);
        semesters.generateAllRecords();
        int generatedSemestersRecords = semesters.getGeneratedRecords();
//        Users users = new Users("./resources/out/projekt/users_insert.sql", namesList, surnamesList);
//        users.generateAllRecords();

//        Teachers teachers = new Teachers("./resources/out/projekt/teachers_insert.sql", 50);
//        teachers.generateAllRecords();
//        Substitutes substitutes = new Substitutes("./resources/out/projekt/substitutes_insert.sql", 5);
//        substitutes.generateAllRecords();

//        Classifieds classifieds = new Classifieds("./resources/out/projekt/classifieds_insert.sql", 5);
//        classifieds.generateAllRecords();
//        Classes classes = new Classes("./resources/out/projekt/classes_insert.sql", 3, 2);
//        classes.generateAllRecords();
//        Students students = new Students("./resources/out/projekt/students_insert.sql", 100, 48, 1000);
//        students.generateAllRecords();
//        Subjects subjects = new Subjects("./resources/out/projekt/subjects_insert.sql", subjectsList);
//        subjects.generateAllRecords();
        StudentsClasses studentsClasses = new StudentsClasses("./resources/out/projekt/students_classes_insert.sql");
        studentsClasses.generateAllRecords();
    }
}
