package tu_varna.bg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

// Класът TextElementExtractor се използва за извличане на текста на елемент от XML файл по зададено ID.
public class TextElementExtractor {

    // Методът extractElementTextById приема път до файл и ID на елемент като аргументи и извлича текста на елемента с това ID.
    public void extractElementTextById(String filePath, String elementId) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("*");
            // Обхождане на всички елементи
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // Проверка дали текущият възел е елемент
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // Проверка дали ID атрибута на текущия елемент съвпада с търсеното ID
                    if (element.getAttribute("id").equals(elementId)) {
                        System.out.println("Extracted element:");
                        System.out.println("Text: " + element.getTextContent());
                        return;
                    }
                }
            }
            System.out.println("Element with ID " + elementId + " not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

