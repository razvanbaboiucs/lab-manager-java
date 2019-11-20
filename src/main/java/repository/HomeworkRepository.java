package repository;

import model.Homework;
import model.YearStructure;
import validator.ValidationException;
import validator.Validator;

import java.time.LocalDate;

/**
 *
 */
public class HomeworkRepository extends AbstractCRUDRepository<Integer, Homework> {
    /**
     *
     * @param v
     */
    public HomeworkRepository(Validator<Homework> v) {
        super(v);
    }

    /**
     *
     * @param entity
     * entity must not be null
     * @return
     */
    @Override
    public Homework update(Homework entity) throws ValidationException {
        Homework result = super.update(entity);
        Integer currentWeek = YearStructure.getWeek(LocalDate.now());
        if(currentWeek > entity.getDeadlineWeek()) {
            throw new ValidationException("Deadline week is smaller than current week");
        }
        return result;
    }
}
