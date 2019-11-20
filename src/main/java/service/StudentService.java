package service;

import model.Student;
import repository.CrudRepository;
import repository.StudentRepository;
import validator.ValidationException;

public class StudentService {
    CrudRepository<Integer, Student> studentRepository;

    public StudentService(CrudRepository<Integer, Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findOne(Integer id) {
        return studentRepository.findOne(id);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Integer id, String name, String surname, String email, String labProf, Integer group) throws ValidationException {
        Student student = new Student(surname, name, email, labProf, group, id);
        return studentRepository.save(student);
    }

    public Student delete(Integer id) {
        return studentRepository.delete(id);
    }

    public Student update(Integer id, String name, String surname, String email, String labProf, Integer group) throws ValidationException {
        Student student = new Student(surname, name, email, labProf, group, id);
        return studentRepository.update(student);
    }
}
