package repository;

import model.Entity;
import model.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import validator.ValidationException;
import validator.Validator;

public class StudentXMLRepository<ID, E extends Entity<ID>> extends AbstractXMLRepository<ID, E> {

    private String tagName;

    public StudentXMLRepository(Validator<E> validator, String fileName) {
        super(validator, fileName, "student");
        this.tagName = "student";
        readXml();
    }

    @Override
    protected E createEntity(Node node) {
        Element element = (Element) node;
        Integer id = Integer.parseInt(element.getAttribute("id"));
        String surname = element.getElementsByTagName("surname").item(0).getTextContent();
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String labTeacher = element.getElementsByTagName("labTeacher").item(0).getTextContent();
        Integer group = Integer.parseInt(element.getElementsByTagName("group").item(0).getTextContent());
        return (E) new Student(surname, name, email, labTeacher, group, id);
    }

    @Override
    protected Element writeEntity(E entity, Document document) {
        Element studentElement = document.createElement(tagName);
        Student student = (Student) entity;
        studentElement.setAttribute("id", Integer.toString(student.getId()));
        Element surname = document.createElement("surname");
        surname.appendChild(document.createTextNode(student.getSurname()));
        studentElement.appendChild(surname);
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(student.getName()));
        studentElement.appendChild(name);
        Element email = document.createElement("email");
        email.appendChild(document.createTextNode(student.getEmail()));
        studentElement.appendChild(email);
        Element labTeacher = document.createElement("labTeacher");
        labTeacher.appendChild(document.createTextNode(student.getLabProfessor()));
        studentElement.appendChild(labTeacher);
        Element group = document.createElement("group");
        group.appendChild(document.createTextNode(Integer.toString(student.getGroup())));
        studentElement.appendChild(group);
        return studentElement;
    }


}
