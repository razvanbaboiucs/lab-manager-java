package model;

import java.time.LocalDate;

/**
 *
 */
public class Homework implements Entity<Integer> {
    private Integer id, startWeek, deadlineWeek;
    private String description;

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", startWeek=" + startWeek +
                ", deadlineWeek=" + deadlineWeek +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     *
     * @return
     */
    public Integer getStartWeek() {
        return startWeek;
    }

    /**
     *
     * @param startWeek
     */
    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    /**
     *
     * @return
     */
    public Integer getDeadlineWeek() {
        return deadlineWeek;
    }

    /**
     *
     * @param deadlineWeek
     */
    public void setDeadlineWeek(Integer deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param id
     * @param startWeek
     * @param deadlineWeek
     * @param description
     */
    public Homework(Integer id, Integer deadlineWeek, String description) {
        this.id = id;
        this.startWeek = YearStructure.getWeek(LocalDate.now());
        this.deadlineWeek = deadlineWeek;
        this.description = description;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     *
     * @param integer
     */
    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}
