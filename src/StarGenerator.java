import myReader.FileReader;
import star.Credit;
import star.Student;
import star.Teacher;

import java.util.ArrayList;

/**
 * Created by Krszysztof Adamczyk on 28.05.2017.
 */
public class StarGenerator {

    private final static ArrayList<String> namesList = FileReader.readFile("./resources/in/imiona.txt");

    private final static ArrayList<String> surnamesList = FileReader.readFile("./resources/in/nazw.txt");

    public static void main(String[] args) {
        int howManyStudents = 10000;
        Student student = new Student("./resources/out/star/g1_student_insert.csv", namesList, surnamesList, howManyStudents);
        student.generateAllRecords();
        int howManyTeachers = 100;
        Teacher teacher = new Teacher("./resources/out/star/g1_wykladowca_insert.csv", namesList, surnamesList, howManyTeachers);
        teacher.generateAllRecords();
        Credit credit = new Credit("./resources/out/star/g1_credit_insert.csv");
        credit.generateAllRecords();
    }
}
