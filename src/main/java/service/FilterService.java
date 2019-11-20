package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FilterService {
    StudentService studentService;
    HomeworkService homeworkService;
    MarkService markService;

    public FilterService(StudentService studentService, HomeworkService homeworkService, MarkService markService) {
        this.studentService = studentService;
        this.homeworkService = homeworkService;
        this.markService = markService;
    }

    public Iterable<StudentDTO> getStudentsFromGroup(Integer group) {
        List<Student> result = new ArrayList<Student>();
        studentService.findAll().forEach(result::add);
        return result
                .stream()
                .filter(student -> student.getGroup().equals(group))
                .map(x -> new StudentDTO(x.getId(), x.getSurname(), x.getName(), x.getEmail()))
                .collect(Collectors.toList());
    }

    public Iterable<Student> getStudentWithHomeworkGiven(Integer homeworkId) {
        List<Student> result = new ArrayList<Student>();
        studentService.findAll().forEach(result::add);
        return result
                .stream()
                .filter(student -> {
                    List<Mark> marks = new ArrayList<Mark>();
                    markService.findAll().forEach(marks::add);
                    for (Mark mark : marks) {
                        if(mark.getHomeworkId().equals(homeworkId) && mark.getStudentId().equals(student.getId())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public Iterable<Student> getStudentsWithHomeworkProfGiven(Integer homeworkId, String prof) {
        List<Student> result = new ArrayList<Student>();
        studentService.findAll().forEach(result::add);
        return result
                .stream()
                .filter(student -> {
                    List<Mark> marks = new ArrayList<Mark>();
                    markService.findAll().forEach(marks::add);
                    for (Mark mark : marks) {
                        if(mark.getHomeworkId().equals(homeworkId) && mark.getStudentId().equals(student.getId()) && mark.getProfessor().equals(prof)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public Iterable<Mark> getMarksForHomeworkWeekGiven(Integer homeworkId, Integer week) {
        List<Mark> result = new ArrayList<Mark>();
        markService.findAll().forEach(result::add);
        return result
                .stream()
                .filter(mark -> {
                    final boolean b = mark.getHomeworkId().equals(homeworkId) && YearStructure.getWeek(mark.getDate()).equals(week);
                    return b;
                })
                .collect(Collectors.toList());
    }
}
