package model;

/**
 *
 * @param <ID>
 */
public interface Entity<ID> {
    /**
     *
     * @return
     */
    ID getId();

    /**
     *
     * @param id
     */
    void setId(ID id);
}
