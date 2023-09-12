package tu_varna.bg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlChildrenAttributesExtractor {
    // Методът извлича атрибутите от децата на елемента с подаденото ID
    public List<String> extractChildAttributesById(String filePath, String id) {
        List<String> attributesList = new ArrayList<>();

        try {
            File inputFile = new File(filePath);

            // Създаваме DocumentBuilder инстанция, за да можем да анализираме XML файла
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("*");

            // Обхождаме всички елементи
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // Проверяваме дали текущият Node е от тип Element
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Проверяваме дали ID-то на елемента съвпада с подаденото
                    if (element.getAttribute("id").equals(id)) {
                        NodeList childNodes = element.getChildNodes();

                        // Обхождаме всички деца на елемента
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node childNode = childNodes.item(j);

                            // Проверяваме дали детето е от тип Element
                            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) childNode;

                                // Вземаме името и стойността на елемента и ги добавяме в листа
                                String elementName = childElement.getTagName();
                                String elementValue = childElement.getTextContent();
                                attributesList.add(elementName + ": " + elementValue);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Връщаме листа с атрибутите
        return attributesList;
    }
}
