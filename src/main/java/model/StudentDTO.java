package model;

public class StudentDTO implements Entity<Integer> {
    Integer id;
    String surname, name, email;

    public StudentDTO(Integer id, String surname, String name, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}
