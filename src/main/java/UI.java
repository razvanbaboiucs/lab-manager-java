import model.*;
import service.FilterService;
import service.HomeworkService;
import service.MarkService;
import service.StudentService;
import validator.*;


import java.util.*;
import java.util.stream.StreamSupport;

public class UI {
    private StudentService studentService;
    private HomeworkService homeworkService;
    private MarkService markService;
    private FilterService filterService;

    public UI(StudentService studentService, HomeworkService homeworkService, MarkService markService, FilterService filterService) {
        this.studentService = studentService;
        this.homeworkService = homeworkService;
        this.markService = markService;
        this.filterService = filterService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while(option != "x") {
            printUI();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    printStudentUI();
                    break;
                case "2":
                    printHomeworkUI();
                    break;
                case "3":
                    printMarksUI();
                    break;
                case "4":
                    printFilterUI();
                    break;
                case "x":
                    System.out.println("Good bye!");
                    return;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }
    }

    private void printFilterUI() {
        System.out.println("Choose an option:\n" +
                "1. Get all students from a group\n" +
                "2. Get all students with given homework done\n" +
                "3. Get all students with given homework and prof\n" +
                "4. Get all marks with given homework and week");
        String option = "";
        option = getString();
        switch (option){
            case "1":
                printStudentsFromGroup();
                break;
            case "2":
                printStudentsWithHomework();
                break;
            case "3":
                printStudentsWithHomeworkAndProf();
                break;
            case "4":
                printMarksWithHomeworkWeek();
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void printMarksWithHomeworkWeek() {
        System.out.println("Give homework: ");
        Integer homeworkId = getInteger();
        System.out.println("Give week: ");
        Integer week = getInteger();
        List<Mark> result = new ArrayList<Mark>();
        filterService.getMarksForHomeworkWeekGiven(homeworkId, week).forEach(result::add);
        result.forEach(System.out::println);
    }

    private void printStudentsWithHomeworkAndProf() {
        System.out.println("Give homework: ");
        Integer homeworkId = getInteger();
        System.out.println("Give prof: ");
        String prof = getString();
        List<Student> result = new ArrayList<Student>();
        filterService.getStudentsWithHomeworkProfGiven(homeworkId, prof).forEach(result::add);
        result.forEach(System.out::println);
    }

    private void printStudentsWithHomework() {
        System.out.println("Give homework: ");
        Integer homeworkId = getInteger();
        List<Student> result = new ArrayList<Student>();
        filterService.getStudentWithHomeworkGiven(homeworkId).forEach(result::add);
        result.forEach(System.out::println);
    }

    private void printStudentsFromGroup() {
        System.out.println("Give group: ");
        Integer group = getInteger();
        List<StudentDTO> result = new ArrayList<StudentDTO>();
        filterService.getStudentsFromGroup(group).forEach(result::add);
        result.forEach(System.out::println);
    }

    private void printMarksUI() {
        String option = "";
        while(option != "x") {
            System.out.println(
                    "Choose an option:\n" +
                            "1. Add Mark\n" +
                            "2. Remove Mark\n" +
                            "3. Update Mark\n" +
                            "4. Get mark\n" +
                            "5. Get all marks\n" +
                            "x. Exit marks"
            );
            option = getString();
            try {
                switch (option) {
                    case "1":
                        addGrade();
                        break;
                    case "2":
                        removeMark();
                        break;
                    case "3":
                        updateMark();
                        break;
                    case "4":
                        findOneMark();
                        break;
                    case "5":
                        findAllMarks();
                        break;
                    case "x":
                        System.out.println("Exiting marks");
                        return;
                    default:
                        System.out.println("Wrong command");
                        break;

                }
            }
            catch (ValidationException e) {
                System.out.println(e);
            }

        }

    }

    private void updateMark() throws ValidationException {
        System.out.println("Give student id: ");
        Integer sid = getInteger();
        System.out.println("Give homework id: ");
        Integer hid = getInteger();
        System.out.println("Give professor:");
        String professor = getString();
        System.out.println("Give mark:");
        Double mark = getDouble();
        Mark updated = markService.update(sid, hid, mark, professor);
        if(updated == null) {
            System.out.println("Mark added!");
        }
        else {
            System.out.println("Mark updated!");
        }
    }

    private void addGrade() throws ValidationException {
        System.out.println("Give student id: ");
        Integer sid = getInteger();
        System.out.println("Give homework id: ");
        Integer hid = getInteger();
        Integer currentWeek = markService.getCurrentWeek();
        Integer weeksAfterDeadline = markService.getWeeksAfterDeadline(currentWeek, sid, hid);
        if(weeksAfterDeadline > 0) {
            System.out.println("Did you forget to assign marks on time? [y/n]");
            String onTime = getString();
            if(onTime.equals("y")) {
                System.out.println("Give the week when marks should have been added");
                currentWeek = getInteger();
                weeksAfterDeadline = markService.getWeeksAfterDeadline(currentWeek, sid, hid);
            }
        }
        System.out.println("Give mark:");
        Double auxMark = getDouble();
        Double mark = markService.getFinalMark(auxMark, weeksAfterDeadline);
        if(weeksAfterDeadline > 0 && weeksAfterDeadline <=2) {
            System.out.println(weeksAfterDeadline + " weeks passed since homework's deadline. Student's final grade is " + mark);
        }
        else if(weeksAfterDeadline > 2) {
            System.out.println("More than 2 weeks passed since homework's deadline. Does this student have an excused absence? [y/n]");
            String option = getString();
            mark = markService.excusedAbsence(option, auxMark);
        }

        System.out.println("Give professor:");
        String professor = getString();
        System.out.println("Give homework's number:");
        Integer homeworkNumber = getInteger();
        System.out.println("Give feedback: ");
        String feedback = getString();
        Mark added = markService.save(sid, hid, mark, professor, feedback, homeworkNumber, currentWeek);
        if(added == null) {
            System.out.println("Mark added!");
        }
        else {
            System.out.println("Mark already exists!");
        }
    }

    private void removeMark() {
        System.out.println("Give student id:");
        Integer sid = getInteger();
        System.out.println("Give homework id:");
        Integer hid = getInteger();
        Mark mark = markService.delete(sid, hid);
        if(mark == null) {
            System.out.println("Mark doesn't exist!");
        }
        else {
            System.out.println("Mark removed!");
        }
    }

    private void findOneMark() {
        System.out.println("Give Student id:");
        Integer sid = getInteger();
        System.out.println("Give Homework id:");
        Integer hid = getInteger();
        Mark mark = markService.findOne(sid, hid);
        if(mark == null){
            System.out.println("Mark doesn't exist!");
        }
        else {
            System.out.println(mark);
        }

    }

    private void findAllMarks() {
        Iterable<Mark> iterable = markService.findAll();
        for (Mark e : iterable) {
            System.out.println(e);
        }
    }

    private Double getDouble() {
        Scanner scanner = new Scanner(System.in);
        Double i = scanner.nextDouble();
        return i;
    }

    private void printHomeworkUI() {
        String option = "";
        while(option != "x") {
            System.out.println(
                    "Choose option:\n" +
                            "1. Add homework\n" +
                            "2. Remove homework\n" +
                            "3. Update homework\n" +
                            "4. Get homework\n" +
                            "5. Get all homework\n" +
                            "x. Exit homework"
            );
            option = getString();
            try {
                switch (option) {
                    case "1":
                        addHomework();
                        break;
                    case "2":
                        deleteHomework();
                        break;
                    case "3":
                        updateHomework();
                        break;
                    case "4":
                        findOneHomework();
                        break;
                    case "5":
                        findAllHomework();
                        break;
                    case "x":
                        System.out.println("Exiting homework");
                        return;
                    default:
                        System.out.println("Wrong command");
                        break;

                }
            }
            catch(ValidationException e) {
                System.out.println(e);
            }

        }

    }

    private void findAllHomework() {
        Iterable<Homework> iterable = homeworkService.findAll();
        for (Homework e:
                iterable) {
            System.out.println(e);
        }
    }

    private void findOneHomework() {
        System.out.println("Give id: ");
        Integer id = getInteger();
        Homework h = homeworkService.findOne(id);
        if(h == null) {
            System.out.println("Homework doesn't exist!");
        }
        else {
            System.out.println(h);
        }
    }

    private void updateHomework() throws ValidationException {
        System.out.println("Give id: ");
        Integer id = getInteger();
        System.out.println("Give description");
        String description = getString();
        System.out.println("Give deadline week:");
        Integer deadlineWeek = getInteger();
        Homework updated = homeworkService.update(id, description, deadlineWeek);
        if (updated != null){
            System.out.println("Homework added successfully.");
        }
        else{
            System.out.println("Homework wupdated successfully.");
        }
    }


    private void deleteHomework() {
        System.out.println("Give id: ");
        Integer id = getInteger();
        Homework removed = homeworkService.delete(id);
        if(removed == null) {
            System.out.println("Homework doesn't exist!");
        }
        else {
            System.out.println("Homework removed!");
        }
    }

    private void addHomework() throws ValidationException {
        System.out.println("Give id: ");
        Integer id = getInteger();
        System.out.println("Give description");
        String description = getString();
        System.out.println("Give deadline week:");
        Integer deadlineWeek = getInteger();
        Homework added = homeworkService.save(id, description, deadlineWeek);
        if(added == null) {
            System.out.println("Homework added!");
        }
        else {
            System.out.println("Homework already exists!");
        }
    }


    private String getString() {
        String option;
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextLine();
        return option;
    }

    private void addStudent() throws ValidationException {
        System.out.println("Give surname:");
        String surname = getString();
        System.out.println("Give name:");
        String name = getString();
        System.out.println("Give email:");
        String email = getString();
        System.out.println("Give lab professor:");
        String professor = getString();
        System.out.println("Give group:");
        Integer group = getInteger();
        System.out.println("Give id:");
        Integer id = getInteger();
        Student added = studentService.save(id, name, surname, email, professor, group);
        if(added == null) {
            System.out.println("Student added successfully!");
        }
        else {
            System.out.println("Student already exists!");
        }
    }


    private void printStudentUI() {
        String option = "";
        while(option != "x") {
            System.out.println(
                    "Choose option:\n" +
                            "1. Add Student\n" +
                            "2. Remove Student\n" +
                            "3. Update student\n" +
                            "4. Get Student\n" +
                            "5. Get all Students\n" +
                            "x. Exit students"
            );
            option = getString();
            try {
                switch (option) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        removeStudent();
                        break;
                    case "3":
                        updateStudent();
                        break;
                    case "4":
                        findStudent();
                        break;
                    case "5":
                        findAllStudent();
                        break;
                    case "x":
                        System.out.println("Exiting students");
                        return;
                    default:
                        System.out.println("Wrong command");
                        break;

                }
            }
            catch (ValidationException e){
                System.out.println(e);
            }
        }

    }

    private void findAllStudent() {
        Iterable<Student> iterable = studentService.findAll();
        for (Student e : iterable) {
            System.out.println(e);
        }
    }

    private void findStudent() {
        System.out.println("Give id: ");
        Integer id = getInteger();
        Student st = studentService.findOne(id);
        if(st == null) {
            System.out.println("Student not found!");
        }
        else {
            System.out.println(st);
        }
    }

    private void updateStudent() throws ValidationException {
        System.out.println("Give surname:");
        String surname = getString();
        System.out.println("Give name:");
        String name = getString();
        System.out.println("Give email:");
        String email = getString();
        System.out.println("Give lab professor:");
        String professor = getString();
        System.out.println("Give group:");
        Integer group = getInteger();
        System.out.println("Give id:");
        Integer id = getInteger();
        Student updated = studentService.update(id, name, surname, email, professor, group);
        if(updated == null) {
            System.out.println("Student updated successfully!");
        }
        else {
            System.out.println("Student added successfully!");
        }
    }

    private void removeStudent() {
        System.out.println("Give id: ");
        Integer id = getInteger();
        Student deleted = studentService.delete(id);
        if(deleted == null) {
            System.out.println("Student does not exist!");
        }
        else {
            System.out.println("Student deleted successfully!");
        }
    }

    private Integer getInteger() {
        Scanner scanner = new Scanner(System.in);
        Integer i = scanner.nextInt();
        return i;
    }

    private void printUI() {
        System.out.println(
                "Choose an option:\n" +
                        "1. Students\n" +
                        "2. Homework\n" +
                        "3. Marks\n" +
                        "4. Filtering\n" +
                        "x. Exit"
        );
    }

}
