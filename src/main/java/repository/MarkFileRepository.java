package repository;

import model.Mark;
import validator.ValidationException;
import validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MarkFileRepository extends AbstractFileRepository<Integer, Mark> {
    /**
     * @param v
     * @param filename
     */
    public MarkFileRepository(Validator v, String filename) {
        super(v, filename);
    }

    @Override
    protected void writeLine(Mark mark, Path path) {
        String str = "";
        Mark entity = mark;
        str += entity.getId()  + "," + entity.getMark() + "," + entity.getProfessor() + "," + entity.getDate().format(DateTimeFormatter.ofPattern("YYYY,MM,dd")) + "\n";
        byte[] strToBytes = str.getBytes();
        try {
            Files.write(path,strToBytes, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void parseLine(String s) throws ValidationException {
        String[] args = s.split(",");
        LocalDate date = LocalDate.of(Integer.parseInt(args[3]),Integer.parseInt(args[4]),Integer.parseInt(args[5]));
        Mark toAdd =  new Mark(Double.parseDouble(args[1]),Integer.parseInt(args[0]),args[2], date);
        super.save(toAdd);
    }
}
