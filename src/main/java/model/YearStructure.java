package model;

import utils.Period;

import java.time.LocalDate;

public class YearStructure implements Entity<Integer> {
    private Integer year, id;
    private SemesterStructure sem1, sem2;
    private static YearStructure instance = null;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public SemesterStructure getSem1() {
        return sem1;
    }

    public void setSem1(SemesterStructure sem1) {
        this.sem1 = sem1;
    }

    public SemesterStructure getSem2() {
        return sem2;
    }

    public void setSem2(SemesterStructure sem2) {
        this.sem2 = sem2;
    }

    public static Integer getWeek(LocalDate date) {
        Integer i = 0;
        for(Period week : instance.sem1.getWeeks()) {
            if(week.isInPeriod(date)) {
                return i + 1;
            }
            i++;
        }
        i = 0;
        for(Period week : instance.sem2.getWeeks()) {
            if(week.isInPeriod(date)) {
                return i + 1;
            }
            i++;
        }
        return -1;
    }

    public YearStructure(Integer id, Integer numberOfWeeks, Integer year, Integer sem1StartHoliday, Integer sem1EndHoliday, LocalDate sem1Start, Integer sem2StartHoliday, Integer sem2EndHoliday, LocalDate sem2Start) {
        this.id = id;
        this.year = year;
        sem1 = new SemesterStructure(numberOfWeeks, sem1StartHoliday, sem1EndHoliday, sem1Start);
        sem2 = new SemesterStructure(numberOfWeeks, sem2StartHoliday, sem2EndHoliday, sem2Start);
    }

    public static YearStructure getInstance(Integer id, Integer numberOfWeeks, Integer year, Integer sem1StartHoliday, Integer sem1EndHoliday, LocalDate sem1Start, Integer sem2StartHoliday, Integer sem2EndHoliday, LocalDate sem2Start) {
        if(instance == null) {
            instance = new YearStructure(id, numberOfWeeks, year, sem1StartHoliday, sem1EndHoliday, sem1Start, sem2StartHoliday, sem2EndHoliday, sem2Start);
            return instance;
        }
        instance.sem1.setNumberOfWeeks(numberOfWeeks);
        instance.sem1.setStartHoliday(sem1StartHoliday);
        instance.sem1.setEndHoliday(sem1EndHoliday);
        instance.sem1.setStart(sem1Start);
        instance.sem2.setNumberOfWeeks(numberOfWeeks);
        instance.sem2.setStartHoliday(sem2StartHoliday);
        instance.sem2.setEndHoliday(sem2EndHoliday);
        instance.sem2.setStart(sem2Start);
        instance.setYear(year);
        return instance;
    }

    public YearStructure() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "YearStructure{" +
                "year=" + year +
                ", id=" + id +
                '}';
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}