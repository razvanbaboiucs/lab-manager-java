package repository;

import validator.ValidationException;
import validator.Validator;
import model.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @param <ID>
 * @param <E>
 */
public class AbstractCRUDRepository<ID, E extends Entity<ID>> implements CrudRepository<ID, E> {
    Map<ID, E> entities;
    Validator<E> validator;

    /**
     *
     * @param v
     */
    public AbstractCRUDRepository(Validator v) {
        entities = new HashMap<ID, E>();
        validator = v;
    }

    /**
     *
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return
     */
    @Override
    public E findOne(ID id) {
        if(id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return entities.get(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    /**
     *
     * @param entity
     * entity must be not null
     * @return
     * @throws ValidationException
     */
    @Override
    public E save(E entity) throws ValidationException {
        if(entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        validator.validate(entity);
        E found = entities.get(entity.getId());
        if(found == null) {
            entities.put(entity.getId(), entity);
            return null;
        }
        return entity;
    }

    /**
     *
     * @param id
     * id must be not null
     * @return
     */
    @Override
    public E delete(ID id) {
        if(id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        E found = entities.get(id);
        if(found == null) {
            return null;
        }
        return entities.remove(id);
    }

    /**
     *
     * @param entity
     * entity must not be null
     * @return
     */
    @Override
    public E update(E entity) throws ValidationException {
        if(entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        validator.validate(entity);
        E found = entities.get(entity.getId());
        if(found == null) {
            entities.put(entity.getId(), entity);
            return entity;
        }
        entities.remove(entity.getId());
        entities.put(entity.getId(), entity);
        return null;
    }

    /**
     *
     * @return
     */
    public Integer size() {
        return entities.size();
    }
}

