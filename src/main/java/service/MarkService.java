package service;

import model.*;
import repository.CrudRepository;
import repository.HomeworkRepository;
import repository.MarkRepository;
import repository.StudentRepository;
import validator.ValidationException;

import java.io.*;
import java.time.LocalDate;

public class MarkService {
    CrudRepository<Integer, Mark> markRepository;
    CrudRepository<Integer, Homework> homeworkRepository;
    CrudRepository<Integer, Student> studentRepository;
    String folderName;

    public MarkService(CrudRepository<Integer, Mark> markRepository, CrudRepository<Integer, Homework> homeworkRepository, CrudRepository<Integer, Student> studentRepository, String folderName) {
        this.markRepository = markRepository;
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
        this.folderName = folderName;
    }

    private Integer generateMarkId(Integer sid, Integer hid) {
        String id_aux = Integer.toString(sid) + Integer.toString(hid);
        Integer markId = Integer.parseInt(id_aux);
        return markId;
    }

    public Mark findOne(Integer sid, Integer hid) {
        Integer markId = generateMarkId(sid, hid);
        return markRepository.findOne(markId);
    }

    public Iterable<Mark> findAll() {
        return markRepository.findAll();
    }

    public Mark delete(Integer sid, Integer hid) {
        Integer markId = generateMarkId(sid, hid);
        return markRepository.delete(markId);
    }

    public Mark update(Integer sid, Integer hid, Double mark, String prof) throws ValidationException {
        Mark mark_aux = new Mark(mark, sid, hid, prof, LocalDate.now());
        return markRepository.update(mark_aux);
    }

    public Mark save(Integer sid, Integer hid, Double mark, String prof, String feedback, Integer homeworkNumber, Integer weekNumber) throws ValidationException {
        Mark toAdd = new Mark(mark, sid, hid, prof, LocalDate.now());
        Mark result = markRepository.save(toAdd);
        if(result == null) {
            Student student = studentRepository.findOne(sid);
            Homework homework = homeworkRepository.findOne(hid);
            Feedback toInsert = new Feedback(homeworkNumber, homework.getDeadlineWeek(), weekNumber, mark, feedback);
            writeToPersonalFile(folderName + "/" + student.getSurname() + student.getName() + ".txt", toInsert);
        }
        return result;
    }

    private void writeToPersonalFile(String fileName, Feedback feedback) {
        String dataWithNewLine = feedback + System.getProperty("\n ------------ \n");
        try {
            File file = new File(fileName);
            if(!(file.exists() && !file.isDirectory())) {
                file.createNewFile();
                writeData(file, dataWithNewLine);
            }
            else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String str = "";
                String data = "";
                while((str = br.readLine()) != null) {
                    data += str + "\n";
                }
                data += dataWithNewLine;
                writeData(file, data);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData(File file, String data) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getCurrentWeek() {
        return YearStructure.getWeek(LocalDate.now());
    }

    public Integer getWeeksAfterDeadline(Integer currentWeek, Integer sid, Integer hid) throws ValidationException {
        Student student = studentRepository.findOne(sid);
        Homework homework = homeworkRepository.findOne(hid);
        if(student == null || homework == null) {
            throw new ValidationException("Id not good!");
        }
        else {
            return currentWeek - homework.getDeadlineWeek();
        }
    }

    public Double getFinalMark(Double auxMark, Integer weeksAfterDeadline) {
        if(weeksAfterDeadline <= 0) {
            return auxMark;
        }
        return auxMark - weeksAfterDeadline;
    }

    public Double excusedAbsence(String option, Double auxMark) {
        if(option == "n") {
            return 1.0;
        }
        else {
            return auxMark;
        }
    }
}
