package repository;

import model.Mark;
import validator.Validator;

/**
 *
 */
public class MarkRepository extends AbstractCRUDRepository<Integer, Mark> {
    /**
     *
     * @param v
     */
    public MarkRepository(Validator<Mark> v) {
        super(v);
    }
}
