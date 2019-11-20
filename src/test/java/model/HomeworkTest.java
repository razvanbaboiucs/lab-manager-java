package model;


import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class HomeworkTest {
    YearStructure yearStructure = YearStructure.getInstance(1, 14, 2, 12, 13, LocalDate.of(2019, 9, 30), 9, 9, LocalDate.of(2020, 2, 25));
    private Homework h = new Homework(1, 2, "asd");


    @Test
    public void getStartWeek() {
        assertEquals((Integer) YearStructure.getWeek(LocalDate.now()), h.getStartWeek());
    }

    @Test
    public void setStartWeek() {
        h.setStartWeek(1);
        assertEquals((Integer) 1, h.getStartWeek());
    }

    @Test
    public void getDeadlineWeek() {
        assertEquals((Integer) 2, h.getDeadlineWeek());
    }

    @Test
    public void setDeadlineWeek() {
        h.setDeadlineWeek(4);
        assertEquals((Integer) 4, h.getDeadlineWeek());
    }

    @Test
    public void getDescription() {
        assertEquals("asd", h.getDescription());
    }

    @Test
    public void setDescription() {
        h.setDescription("ads");
        assertEquals("ads", h.getDescription());
    }

    @Test
    public void getId() {
        assertEquals((Integer) 1, h.getId());
    }

    @Test
    public void setId() {
        h.setId(2);
        assertEquals((Integer) 2, h.getId());
    }
}
