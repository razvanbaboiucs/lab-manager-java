package repository;

import model.Entity;
import model.Mark;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import validator.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MarkXMLRepository<ID, E extends Entity<ID>> extends AbstractXMLRepository<ID, E> {
    private String tagName;

    public MarkXMLRepository(Validator<E> validator, String fileName) {
        super(validator, fileName, "mark");
        this.tagName = "mark";
        readXml();
    }

    @Override
    protected E createEntity(Node node) {
        Element element = (Element) node;
        Integer studentId = Integer.parseInt(element.getAttribute("studentId"));
        Integer homeworkId = Integer.parseInt(element.getAttribute("homeworkId"));
        Double mark = Double.parseDouble(element.getElementsByTagName("grade").item(0).getTextContent());
        String dateString = element.getElementsByTagName("date").item(0).getTextContent();
        String[] args = dateString.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        String teacher = element.getElementsByTagName("teacher").item(0).getTextContent();
        return (E) new Mark(mark, studentId, homeworkId, teacher, date);
    }

    @Override
    protected Element writeEntity(E entity, Document document) {
        Element element = document.createElement(tagName);
        Mark mark = (Mark) entity;
        element.setAttribute("studentId", Integer.toString(mark.getStudentId()));
        element.setAttribute("homeworkId", Integer.toString(mark.getHomeworkId()));
        Element grade = document.createElement("grade");
        grade.appendChild(document.createTextNode(Double.toString(mark.getMark())));
        element.appendChild(grade);
        Element date = document.createElement("date");
        date.appendChild(document.createTextNode(mark.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))));
        element.appendChild(date);
        Element teacher = document.createElement("teacher");
        teacher.appendChild(document.createTextNode(mark.getProfessor()));
        element.appendChild(teacher);
        return element;
    }
}
