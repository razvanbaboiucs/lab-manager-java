package repository;

import model.Student;
import validator.Validator;

/**
 *
 */
public class StudentRepository extends AbstractCRUDRepository<Integer, Student> {
    /**
     *
     * @param v
     */
    public StudentRepository(Validator<Student> v) {
        super(v);
    }
}
