package tu_varna.bg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.StringWriter;

public class XmlElementExtractor {
    // Метод, който извлича XML елемент по дадено ID
    public void extractElementById(String filePath, String elementId) {
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(file);
                doc.getDocumentElement().normalize();

                // Извикваме помощния метод за извличане на елемент по ID
                Element element = getElementById(doc, elementId);
                if (element != null) {
                    // Извеждаме информация за намерения елемент
                    System.out.println("Element with ID " + elementId + ":");
                    System.out.println(elementToString(element));
                } else {
                    System.out.println("No element found with ID " + elementId);
                }
            } catch (Exception e) {
                System.out.println("Error retrieving the item: " + e.getMessage());
            }
        } else {
            System.out.println("The file does not exist or is not a file.");
        }
    }
    // Метод, който извлича елемент по дадено ID с помощта на XPath
    private Element getElementById(Document doc, String elementId) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression expression = xPath.compile("//*[@id='" + elementId + "']");
        NodeList nodeList = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        if (nodeList.getLength() > 0) {
            return (Element) nodeList.item(0);
        }
        return null;
    }

    // Метод, който конвертира елемент в стринг формат
    private String elementToString(Element element) {
        StringWriter sw = new StringWriter();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(element), new StreamResult(sw));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}


