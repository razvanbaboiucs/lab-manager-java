package model;


import org.junit.Test;

import java.time.LocalDate;


public class MarkTest {
    YearStructure yearStructure = YearStructure.getInstance(1, 14, 2, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));
    LocalDate date = LocalDate.now();
    Student s = new Student("asd", "asd", "asd@asd.com", "asd", 123, 1);
    Homework h = new Homework(1, 10, "asd");
    Mark m = new Mark(7.3, 11,"asd", date);

    /**
     *
     */
    @Test
    public void testGetId() {
        assert m.getId() == 11;
    }

    /**
     *
     */
    @Test
    public void testSetId(){
        m.setId(12);
        assert m.getId() == 12;
    }


    @Test
    public void testGetMark() {
        assert m.getMark() == 7.3;
    }

    /**
     *
     */
    @Test
    public void testSetMark() {
        Double m2 = 8.1;
        m.setMark(m2);
        assert m.getMark() == m2;
    }

    /**
     *
     */
    @Test
    public void testGetDate() {
        assert m.getDate() == date;
    }

    /**
     *
     */
    @Test
    public void testSetDate() {
        LocalDate date1 = LocalDate.now();
        m.setDate(date1);
        assert m.getDate() == date1;
    }
}
