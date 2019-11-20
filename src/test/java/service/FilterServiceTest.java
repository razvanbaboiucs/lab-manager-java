package service;

import model.YearStructure;
import repository.HomeworkRepository;
import repository.MarkRepository;
import repository.StudentRepository;
import validator.HomeworkValidator;
import validator.MarkValidator;
import validator.StudentValidator;

import java.time.LocalDate;

public class FilterServiceTest {
    YearStructure instance = YearStructure.getInstance(1, 14, 2, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));
    StudentValidator studentValidator = new StudentValidator();
    HomeworkValidator homeworkValidator = new HomeworkValidator();
    MarkValidator markValidator = new MarkValidator();
    StudentRepository studentRepository = new StudentRepository(studentValidator);
    HomeworkRepository homeworkRepository = new HomeworkRepository(homeworkValidator);
    MarkRepository markRepository = new MarkRepository(markValidator);
    StudentService studentService = new StudentService(studentRepository);
    HomeworkService homeworkService = new HomeworkService(homeworkRepository);
    MarkService markService = new MarkService(markRepository, homeworkRepository, studentRepository, "markData");
    FilterService filterService = new FilterService(studentService, homeworkService, markService);


}