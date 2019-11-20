package repository;

import model.Entity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import validator.ValidationException;
import validator.Validator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class AbstractXMLRepository<ID, E extends Entity<ID>> extends AbstractCRUDRepository<ID, E> {
    private String fileName;
    private String tagName;

    public AbstractXMLRepository(Validator v, String fileName, String tagName) {
        super(v);
        this.fileName = fileName;
        this.tagName = tagName;
    }


    @Override
    public E save(E entity) throws ValidationException {
        E returnValue = super.save(entity);
        if(returnValue == null) {
            writeXml();
        }
        return returnValue;
    }

    protected void writeXml() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try{
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(tagName + "s");
        document.appendChild(root);
        super.findAll().forEach(entity -> {
            Element child = writeEntity(entity, document);
            root.appendChild(child);
        });
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    protected void readXml() {
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(fileName));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(tagName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    E entity = null;
                    try{
                        entity = createEntity(node);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(entity != null) {
                        super.save(entity);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract E createEntity(Node node);

    protected abstract Element writeEntity(E entity, Document document);

    @Override
    public E delete(ID id) {
        E returnValue = super.delete(id);
        if(returnValue != null) {
            writeXml();
        }
        return returnValue;
    }

    @Override
    public E update(E entity) throws ValidationException {
        E returnValue = super.update(entity);
        if(returnValue == null) {
            writeXml();
        }
        return returnValue;
    }


}
