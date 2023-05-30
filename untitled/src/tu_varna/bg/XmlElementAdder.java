package tu_varna.bg;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class XmlElementAdder {

    public void addChildElement(String filePath, String parentId, String childId, String childElementName) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Създаване на обект XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Създаване на XPath израз за намиране на родителския елемент по id
            String expression = String.format("//*[@id='%s']", parentId);
            XPathExpression xpathExpression = xpath.compile(expression);

            // Оценяване на XPath израза, за да се намери родителския елемент
            Element parentElement = (Element) xpathExpression.evaluate(doc, XPathConstants.NODE);

            // Ако родителският елемент съществува, добавяме новия наследник
            if (parentElement != null) {
                Document ownerDocument = parentElement.getOwnerDocument();
                Element childElement = ownerDocument.createElementNS(null, childElementName);
                childElement.setAttribute("id", childId);
                parentElement.appendChild(childElement);

                // Записване на актуализирания XML обратно във файла
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filePath));
                transformer.transform(source, result);
            } else {
                System.out.println("Родителският елемент с id '" + parentId + "' не е намерен.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




