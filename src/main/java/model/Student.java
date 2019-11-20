package model;


public class Student implements Entity<Integer> {
    private String surname, name, email, labProfessor;
    private Integer group, id;

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", labProfessor='" + labProfessor + '\'' +
                ", group=" + group +
                ", id=" + id +
                '}';
    }

    /**
     *
     * @param surname
     * @param name
     * @param email
     * @param labProfessor
     * @param group
     * @param id
     */
    public Student(String surname, String name, String email, String labProfessor, Integer group, Integer id) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.labProfessor = labProfessor;
        this.group = group;
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getLabProfessor() {
        return labProfessor;
    }

    /**
     *
     * @param labProfessor
     */
    public void setLabProfessor(String labProfessor) {
        this.labProfessor = labProfessor;
    }

    /**
     *
     * @return
     */
    public Integer getGroup() {
        return group;
    }

    /**
     *
     * @param group
     */
    public void setGroup(Integer group) {
        this.group = group;
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

    public boolean isInGroup(Integer group) {
        return this.group == group;
    }
}
