package utils;

import java.time.LocalDate;

public class Period {
    private LocalDate startDate, endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isInPeriod(LocalDate date) {
        java.time.Period first = java.time.Period.between(startDate, date);
        if(first.isNegative()) {
            return false;
        }
        java.time.Period second = java.time.Period.between(date, endDate);
        if(second.isNegative()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
