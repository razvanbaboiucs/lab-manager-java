package validator;

import model.Mark;

/**
 *
 */
public class MarkValidator implements Validator<Mark>{
    /**
     *
     * @param entity
     * @throws ValidationException
     */
    @Override
    public void validate(Mark entity) throws ValidationException {
        if(entity.getId() == null) {
            throw new ValidationException("Id invalid!");
        }
        if(entity.getMark() < 0 || entity.getMark() > 10) {
            throw new ValidationException("Mark invalid");
        }
        if(entity.getProfessor() == "") {
            throw new ValidationException("Professor invalid");
        }
    }
}
