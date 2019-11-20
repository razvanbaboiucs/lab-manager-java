package repository;

import model.Entity;
import model.Homework;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import validator.Validator;

public class HomeworkXMLRepository<ID, E extends Entity<ID>> extends AbstractXMLRepository<ID, E> {
    private String tagName;

    public HomeworkXMLRepository(Validator<E> validator, String fileName) {
        super(validator, fileName, "homework");
        this.tagName = "homework";
        readXml();
    }

    @Override
    protected E createEntity(Node node) {
        Element element = (Element) node;
        Integer id = Integer.parseInt(element.getAttribute("id"));
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        Integer startWeek = Integer.parseInt(element.getElementsByTagName("startWeek").item(0).getTextContent());
        Integer deadlineWeek = Integer.parseInt(element.getElementsByTagName("deadlineWeek").item(0).getTextContent());
        Homework homework = new Homework(id, deadlineWeek, description);
        homework.setStartWeek(startWeek);
        return (E) homework;
    }

    @Override
    protected Element writeEntity(E entity, Document document) {
        Element element = document.createElement(tagName);
        Homework homework = (Homework) entity;
        element.setAttribute("id", Integer.toString(homework.getId()));
        Element description = document.createElement("description");
        description.appendChild(document.createTextNode(homework.getDescription()));
        element.appendChild(description);
        Element startWeek = document.createElement("startWeek");
        startWeek.appendChild(document.createTextNode(Integer.toString(homework.getStartWeek())));
        element.appendChild(startWeek);
        Element deadlineWeek = document.createElement("deadlineWeek");
        deadlineWeek.appendChild(document.createTextNode(Integer.toString(homework.getDeadlineWeek())));
        element.appendChild(deadlineWeek);
        return element;
    }
}
