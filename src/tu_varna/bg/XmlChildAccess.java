package tu_varna.bg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

// Клас за достъп до под-елементи (children) в XML файл.
public class XmlChildAccess {

    // Метод за вземане на n-тия под-елемент на елемент със специфично ID.
    public Element getChild(String filePath, String id, int n) {
        try {
            File inputFile = new File(filePath);

            // Създаване на DocumentBuilder инстанция за четене на XML файл
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("person");

            // Обхождане на всеки елемент
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // Проверка дали елементът има зададеното ID
                    if (element.getAttribute("id").equals(id)) {
                        // Ако елементът има зададеното ID, вземаме всички негови деца
                        NodeList children = element.getChildNodes();
                        int count = 0;
                        // Обхождане на децата на елемента
                        for (int j = 0; j < children.getLength(); j++) {
                            Node child = children.item(j);
                            if (child.getNodeType() == Node.ELEMENT_NODE) {
                                // Броим елементите-деца и връщаме n-тия, ако го намерим
                                count++;
                                if (count == n) {
                                    return (Element) child;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



