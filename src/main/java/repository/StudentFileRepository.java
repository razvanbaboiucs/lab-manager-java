package repository;

import model.Student;
import validator.ValidationException;
import validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StudentFileRepository extends AbstractFileRepository<Integer, Student> {

    /**
     * @param v
     * @param filename
     */
    public StudentFileRepository(Validator v, String filename) {
        super(v, filename);
    }

    @Override
    protected void writeLine(Student student, Path path) {
        String str = "";
        Student entity = student;
        str += entity.getId() + "," + entity.getSurname() + "," + entity.getName() + "," + entity.getEmail() + "," + entity.getLabProfessor() + "," + entity.getGroup() +"\n";
        byte[] strToBytes = str.getBytes();
        try {
            Files.write(path, strToBytes, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void parseLine(String s) throws ValidationException {
        String[] args = s.split(",");
        Student toAdd = new Student(args[1],args[2],args[3],args[4],Integer.parseInt(args[5]), Integer.parseInt(args[0]));
        super.save(toAdd);
    }
}
