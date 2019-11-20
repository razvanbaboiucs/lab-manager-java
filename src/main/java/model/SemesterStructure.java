package model;
import utils.Period;

import java.time.LocalDate;
import java.util.ArrayList;

public class SemesterStructure {
    private Integer numberOfWeeks, startHoliday, endHoliday;
    private ArrayList<Period> weeks;
    private LocalDate start;

    public Integer getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(Integer numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public Integer getStartHoliday() {
        return startHoliday;
    }

    public void setStartHoliday(Integer startHoliday) {
        this.startHoliday = startHoliday;
    }

    public Integer getEndHoliday() {
        return endHoliday;
    }

    public void setEndHoliday(Integer endHoliday) {
        this.endHoliday = endHoliday;
    }

    public ArrayList<Period> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<Period> weeks) {
        this.weeks = weeks;
    }

    public LocalDate getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "SemesterStructure{" +
                "numberOfWeeks=" + numberOfWeeks +
                ", startHoliday=" + startHoliday +
                ", endHoliday=" + endHoliday +
                ", start=" + start +
                '}';
    }

    public void setStart(LocalDate start) {
        this.start = start;
        configSemester();
    }

    public SemesterStructure(Integer numberOfWeeks, Integer startHoliday, Integer endHoliday, LocalDate start) {
        this.start = start;
        this.numberOfWeeks = numberOfWeeks;
        this.startHoliday = startHoliday;
        this.endHoliday = endHoliday;
        weeks = new ArrayList<>(numberOfWeeks);
        configSemester();
    }

    private void configSemester() {
        weeks.clear();
        int i = 0, j = 0;
        for(i = 0; i < startHoliday; ++i) {
            weeks.add(i, new Period(start.plusDays(7 * i), start.plusDays(7 * i + 6)));
        }
        for(j = endHoliday + 1; i < numberOfWeeks; j++, i++) {
            weeks.add(i, new Period(start.plusDays(7 * j), start.plusDays(7 * j + 6)));
        }
    }
}