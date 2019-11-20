package service;

import model.Homework;
import repository.CrudRepository;
import repository.HomeworkRepository;
import validator.ValidationException;

public class HomeworkService {
    CrudRepository<Integer, Homework> homeworkRepository;

    public HomeworkService(CrudRepository<Integer, Homework> homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public Homework findOne(Integer id) {
        return homeworkRepository.findOne(id);
    }

    public Iterable<Homework> findAll() {
        return homeworkRepository.findAll();
    }

    public Homework save(Integer id, String description, Integer deadline) throws ValidationException {
        Homework homework = new Homework(id, deadline, description);
        return homeworkRepository.save(homework);
    }

    public Homework delete(Integer id) {
        return homeworkRepository.delete(id);
    }

    public Homework update(Integer id, String description, Integer deadline) throws ValidationException {
        Homework homework = new Homework(id, deadline, description);
        return homeworkRepository.update(homework);
    }
}
