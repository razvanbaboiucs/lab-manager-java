package repository;

import model.Entity;
import validator.ValidationException;
import validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends AbstractCRUDRepository<ID, E>{

    private String filename;
    /**
     * @param v
     */
    public AbstractFileRepository(Validator v, String filename)  {
        super(v);
        this.filename = filename;
        readFromFile();
    }

    private void readFromFile() {
        try{
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                parseLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(){
        Path path = Paths.get(filename);
        try {
            Files.write(path, "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.findAll().forEach(x->writeLine(x, path));
    }

    protected abstract void writeLine(E e, Path path);

    protected abstract void parseLine(String s) throws ValidationException;

    @Override
    public E save(E entity) throws ValidationException {
        E result = super.save(entity);
        writeToFile();
        return result;
    }

    @Override
    public E delete(ID id) {
        E result = super.delete(id);
        writeToFile();
        return result;
    }

    @Override
    public E update(E entity) throws ValidationException {
        E result = super.update(entity);
        writeToFile();
        return  result;
    }
}
