package tu_varna.bg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

// Класът XmlAttributeDeleter се използва за изтриване на атрибут от XML елемент по зададено ID.
public class XmlAttributeDeleter {

    // Методът deleteAttributeByKey приема път до файл, ID на елемент и име на атрибут като аргументи и изтрива атрибута от елемента с това ID.
    public void deleteAttributeByKey(String filePath, String elementId, String attributeName) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Извличане на всички елементи в документа
            NodeList nodeList = doc.getElementsByTagName("*");

            // Обхождане на всички елементи
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // Проверка дали текущият възел е елемент
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Проверка дали ID атрибута на текущия елемент съвпада с търсеното ID
                    if (element.getAttribute("id").equals(elementId)) {
                        // Ако съвпада, изтриваме атрибута със зададеното име
                        element.removeAttribute(attributeName);
                    }
                }
            }

            // Запазване на промените в XML файла
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(inputFile);
            transformer.transform(source, result);

            System.out.println("Атрибутът '" + attributeName + "' на елемент с ID '" + elementId + "' е изтрит успешно.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


