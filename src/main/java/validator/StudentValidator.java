package validator;

import model.Student;

/**
 *
 */
public class StudentValidator implements Validator<Student> {
    /**
     *
     * @param s
     * @throws ValidationException
     */
    @Override
    public void validate(Student s) throws ValidationException {
        if(s.getId()== null)
            throw new ValidationException("Id invalid.\n");
        if(s.getGroup() == null || s.getGroup() <= 0)
            throw new ValidationException("Group invalid.\n");
        if(s.getName() == "")
            throw new ValidationException("Name invalid.\n");
        if(s.getEmail() == "" || !s.getEmail().contains("@")  || !s.getEmail().contains(".com"))
            throw new ValidationException("Email invalid.\n");
        if(s.getLabProfessor() == "")
            throw new ValidationException("Professor invalid.\n");
    }
}
