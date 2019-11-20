package repository;

import model.Student;
import model.YearStructure;
import org.junit.Test;
import validator.StudentValidator;
import validator.ValidationException;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;



public class InMemoryRepoTest {
    StudentValidator studentValidator = new StudentValidator();
    StudentRepository studentAbstractCRUDRepository = new StudentRepository(studentValidator);
    YearStructure yearStructure = YearStructure.getInstance(1, 14, 2019, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));

    void init() {
        try {
            studentAbstractCRUDRepository.save(new Student("s", "n", "sn@gmail.com", "l", 221, 1));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findOne() throws ValidationException {
        init();
        assertEquals(null, studentAbstractCRUDRepository.findOne(2000));
        assertEquals("s", studentAbstractCRUDRepository.findOne(1).getSurname());
    }


    @Test
    public void findAll() {
        init();
        int size = 0;
        for(Student s : studentAbstractCRUDRepository.findAll()){
            size++;
        }
        assertEquals(1, size);
    }

    @Test
    public void save() throws ValidationException {
        init();
        assertEquals(null, studentAbstractCRUDRepository.save(new Student("a", "b", "c@c.com", "ll", 222, 2)));
    }

    @Test
    public void delete() {
        init();
        assertEquals("s", studentAbstractCRUDRepository.delete(1).getSurname());
    }

    @Test
    public void update() throws ValidationException {
        init();
        assertEquals(null, studentAbstractCRUDRepository.update(new Student("as", "as", "as@as.com", "as", 222, 1)));
        assertEquals("s", studentAbstractCRUDRepository.update(new Student("s", "s", "s@y.com", "s", 223, 2)).getSurname());
    }

    @Test
    public void size() {
        init();
        assertEquals(Integer.valueOf(1), studentAbstractCRUDRepository.size());
    }
}