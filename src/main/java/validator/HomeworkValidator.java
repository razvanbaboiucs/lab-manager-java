package validator;

import model.Homework;

/**
 *
 */
public class HomeworkValidator implements Validator<Homework> {

    /**
     *
     * @param h
     * @throws ValidationException
     */
    @Override
    public void validate(Homework h) throws ValidationException {
        if(h.getId() == null)
            throw new ValidationException("ID invalid.\n");
        if(h.getStartWeek() >= h.getDeadlineWeek())
            throw new ValidationException("Start week greater than deadline week");
        if(h.getDescription() == "")
            throw new ValidationException("Description invalid.");
        if(h.getStartWeek() == -1)
            throw new ValidationException("Cannot add homework while in vacation!");
        if(h.getStartWeek() <= 0 || h.getStartWeek() > 14)
            throw new ValidationException("Start week invalid!");
        if(h.getDeadlineWeek() <= 0 || h.getDeadlineWeek() > 14)
            throw new ValidationException("Deadline week invalid!");
    }
}
