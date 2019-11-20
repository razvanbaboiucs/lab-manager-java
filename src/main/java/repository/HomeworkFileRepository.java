package repository;

import model.Homework;
import model.YearStructure;
import validator.ValidationException;
import validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class HomeworkFileRepository extends AbstractFileRepository<Integer, Homework> {
    /**
     * @param v
     * @param filename
     */
    public HomeworkFileRepository(Validator v, String filename) {
        super(v, filename);
    }

    @Override
    protected void writeLine(Homework homework, Path path) {
        String str = "";
        Homework entity = homework;
        str += entity.getId() +  "," + entity.getDeadlineWeek() + ","  + entity.getStartWeek() + "," + entity.getDescription() + "\n";
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
        Homework toAdd =  new Homework(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[3]);
        toAdd.setStartWeek(Integer.parseInt(args[2]));
        super.save(toAdd);
    }

    @Override
    public Homework update(Homework entity) throws ValidationException {
        Homework result = super.update(entity);
        Homework e = entity;
        int currWeek = YearStructure.getWeek(LocalDate.now());
        if(currWeek > e.getDeadlineWeek()){
            throw new ValidationException("Deadline week is smaller than current week.");
        }
        return result;
    }
}
