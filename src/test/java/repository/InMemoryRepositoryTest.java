/*package repository;

import junit.framework.TestCase;
import model.Student;
import model.YearStructure;
import validator.StudentValidator;
import validator.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryRepositoryTest extends TestCase {

    StudentValidator studentValidator = new StudentValidator();
    StudentRepository studentAbstractCRUDRepository = new StudentRepository(studentValidator);
    YearStructure yearStructure = YearStructure.getInstance(1, 14, 2019, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));


    public void testFindOne() throws ValidationException {
        studentAbstractCRUDRepository.save(new Student("s", "n", "sn@gmail.com", "l", 221, 1));
        assertThrows(IllegalArgumentException.class, () -> {
            studentAbstractCRUDRepository.findOne(null);
        });
        assertEquals(null, studentAbstractCRUDRepository.findOne(2000));
        assertEquals("s", studentAbstractCRUDRepository.findOne(1).getSurname());
    }

    public void testFindAll() {
        Integer size = 0;
        for(Student s : studentAbstractCRUDRepository.findAll()){
            size++;
        }
        assertEquals(java.util.Optional.of(1), size);
    }

    public void testSave() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            studentAbstractCRUDRepository.save(new Student("", "", "", "", null, null));
        });
        assertEquals(null, studentAbstractCRUDRepository.save(new Student("a", "b", "c@c.com", "ll", 222, 2)));
    }

    public void testDelete() {
        assertThrows(ValidationException.class, () -> {
            studentAbstractCRUDRepository.delete(null);
        });
        assertEquals("a", studentAbstractCRUDRepository.delete(2).getSurname());
    }

    public void testUpdate() {
    }

    public void testSize() {
    }
}

 */