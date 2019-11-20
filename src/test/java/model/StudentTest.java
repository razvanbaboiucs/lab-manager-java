package model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class StudentTest {
    private Student s = new Student("Pop", "Ioan", "pop.ion@gmail.com","prof", 226, 1);
    YearStructure yearStructure = YearStructure.getInstance(1, 14, 2, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));

    @Test
    public void getSurname() {
        assertEquals("Pop", s.getSurname());
    }

    @Test
    public void setSurname() {
        s.setSurname("Popa");
        assertEquals("Popa", s.getSurname());
    }

    @Test
    public void getName() {
        assertEquals("Ioan", s.getName());
    }

    @Test
    public void setName() {
        s.setName("I");
        assertEquals("I", s.getName());
    }

    @Test
    public void getEmail() {
        assertEquals("pop.ion@gmail.com", s.getEmail());
    }

    @Test
    public void setEmail() {
        s.setEmail("a@a.com");
        assertEquals("a@a.com", s.getEmail());
    }

    @Test
    public void getLabProfessor() {
        assertEquals("prof", s.getLabProfessor());
    }

    @Test
    public void setLabProfessor() {
        s.setLabProfessor("Prof");
        assertEquals("Prof", s.getLabProfessor());
    }

    @Test
    public void getGroup() {
        assertEquals((Integer) 226, s.getGroup());
    }

    @Test
    public void setGroup() {
        s.setGroup(227);
        assertEquals((Integer) 227, s.getGroup());
    }

    @Test
    public void getId() {
        assertEquals((Integer) 1, s.getId());
    }

    @Test
    public void setId() {
        s.setId(2);
        assertEquals((Integer) 2, s.getId());
    }
}
