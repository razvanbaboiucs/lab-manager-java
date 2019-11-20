import model.Homework;
import model.Student;
import model.YearStructure;
import repository.*;
import service.FilterService;
import service.HomeworkService;
import service.MarkService;
import service.StudentService;
import validator.HomeworkValidator;
import validator.MarkValidator;
import validator.StudentValidator;

import java.time.LocalDate;

/**
 *
 */
public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        YearStructure instance = YearStructure.getInstance(1, 14, 2, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));
        StudentValidator studentValidator = new StudentValidator();
        HomeworkValidator homeworkValidator = new HomeworkValidator();
        MarkValidator markValidator = new MarkValidator();
        /*
        InMemoryRepo

        StudentRepository studentRepository = new StudentRepository(studentValidator);
        HomeworkRepository homeworkRepository = new HomeworkRepository(homeworkValidator);
        MarkRepository markRepository = new MarkRepository(markValidator);
        */
        /*
        InFileRepo

        StudentFileRepository studentRepository = new StudentFileRepository(studentValidator, "data/students.txt");
        HomeworkFileRepository homeworkRepository = new HomeworkFileRepository(homeworkValidator, "data/homework.txt");
        MarkFileRepository markRepository = new MarkFileRepository(markValidator, "data/marks.txt");
        */
        /*
        XMLRepo
         */
        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "data/students.xml");
        HomeworkXMLRepository homeworkRepository = new HomeworkXMLRepository(homeworkValidator, "data/homework.xml");
        MarkXMLRepository markRepository = new MarkXMLRepository(markValidator, "data/marks.xml");


        StudentService studentService = new StudentService(studentRepository);
        HomeworkService homeworkService = new HomeworkService(homeworkRepository);
        MarkService markService = new MarkService(markRepository, homeworkRepository, studentRepository, "markData");
        FilterService filterService = new FilterService(studentService, homeworkService, markService);
	    UI ui = new UI(studentService, homeworkService, markService, filterService);
	    ui.run();
    }
}
