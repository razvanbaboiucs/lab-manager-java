package model;

import java.time.LocalDate;

/**
 *
 */
public class Mark implements Entity<Integer> {
    private Double mark;
    private Integer id, studentId, homeworkId;
    private String professor;
    private LocalDate date;

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                ", id=" + id +
                ", professor='" + professor + '\'' +
                ", date=" + date +
                '}';
    }

    /**
     *
     * @return
     */
    public Double getMark() {
        return mark;
    }

    /**
     *
     * @param mark
     */
    public void setMark(Double mark) {
        this.mark = mark;
    }


    /**
     *
     * @return
     */
    public String getProfessor() {
        return professor;
    }

    /**
     *
     * @param professor
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     *
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    /**
     *
     * @param mark
     * @param professor
     * @param date
     */
    public Mark(Double mark, Integer studentId, Integer homeworkId, String professor, LocalDate date) {
        this.mark = mark;
        this.professor = professor;
        this.date = date;
        String id_aux = Integer.toString(studentId) + Integer.toString(homeworkId);
        this.id = Integer.parseInt(id_aux);
        this.studentId = studentId;
        this.homeworkId = homeworkId;
    }

    public Mark(Double mark, Integer id, String professor, LocalDate date) {
        this.mark = mark;
        this.professor = professor;
        this.date = date;
        this.id = id;
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
