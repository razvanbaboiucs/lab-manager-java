package model;

public class Feedback {
    private Integer homeworkNumber, deadlineWeek, weekGiven;
    private Double mark;
    private String feedback;

    public Feedback(Integer homeworkNumber, Integer deadlineWeek, Integer weekGiven, Double mark, String feedback) {
        this.homeworkNumber = homeworkNumber;
        this.deadlineWeek = deadlineWeek;
        this.weekGiven = weekGiven;
        this.mark = mark;
        this.feedback = feedback;
    }

    public Integer getHomeworkNumber() {
        return homeworkNumber;
    }

    public Integer getDeadlineWeek() {
        return deadlineWeek;
    }

    public Integer getWeekGiven() {
        return weekGiven;
    }

    public Double getMark() {
        return mark;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return " Homework number: " + homeworkNumber +
                "\n Deadline week : " + deadlineWeek +
                "\n Week given: " + weekGiven +
                "\n Mark: " + mark +
                "\n Feedback: '" + feedback + "'\n" ;
    }
}
