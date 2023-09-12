package tu_varna.bg;

import org.w3c.dom.Element;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileParser fileParser = new FileParser();
        XmlElementExtractor xmlElementExtractor = new XmlElementExtractor();
        XmlAttributeUpdater xmlAttributeUpdater = new XmlAttributeUpdater();
        XmlChildAccess xmlChildAccess = new XmlChildAccess();
        XmlChildrenAttributesExtractor xmlChildrenAttributesExtractor = new XmlChildrenAttributesExtractor();
        FileDisplayer fileDisplayer = new FileDisplayer();
        TextElementExtractor textElementExtractor = new TextElementExtractor();
        XmlAttributeDeleter xmlAttributeDeleter = new XmlAttributeDeleter();
        XmlElementAdder xmlElementAdder = new XmlElementAdder();

        String filePath = null;
        String elementId;
        String key;
        int nthChild;
        boolean exit = false;

        while (!exit) {

            System.out.println("Welcome to the program");
            System.out.print("Please write a command: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "open_the_file":
                    System.out.print("Please enter the path to the XML file: ");
                    filePath = scanner.nextLine();
                    fileParser.parseFile(filePath);
                    break;
                case "display_the_content":
                    if (filePath != null) {
                        fileDisplayer.displayFileContent(filePath);
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "extract_by_id":
                    if (filePath != null) {
                        System.out.print("Please enter the element ID: ");
                        elementId = scanner.nextLine();
                        xmlElementExtractor.extractElementById(filePath, elementId);
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "update_attribute":
                    System.out.print("Enter the element ID: ");
                    elementId = scanner.nextLine();
                    System.out.print("Enter the attribute key: ");
                    String attributeKey = scanner.nextLine();
                    System.out.print("Enter the new attribute value: ");
                    String attributeValue = scanner.nextLine();
                    xmlAttributeUpdater.updateAttributeById(filePath, elementId, attributeKey, attributeValue);
                    break;
                case "extract_attributes":
                    if (filePath != null) {
                        System.out.print("Please enter the element ID: ");
                        elementId = scanner.nextLine();
                        List<String> attributes = xmlChildrenAttributesExtractor.extractChildAttributesById(filePath, elementId);
                        if (attributes.isEmpty()) {
                            System.out.println("No child elements with attributes found for this ID.");
                        } else {
                            System.out.println("Attributes of child elements are:");
                            for (String attribute : attributes) {
                                System.out.println(attribute);
                            }
                        }
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "access_the_child":
                    if (filePath != null) {
                        System.out.print("Please enter the element ID: ");
                        elementId = scanner.nextLine();
                        System.out.print("Please enter the number of the child you want to access: ");
                        nthChild = scanner.nextInt();
                        scanner.nextLine();
                        Element child = xmlChildAccess.getChild(filePath, elementId, nthChild);
                        if (child != null) {
                            System.out.println("The retrieved child is: " + child);
                        } else {
                            System.out.println("No child was found with the specified number.");
                        }
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "extract_the_text":
                    if (filePath != null) {
                        System.out.print("Please enter the element ID: ");
                        elementId = scanner.nextLine();
                        textElementExtractor.extractElementTextById(filePath, elementId);
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "delete_attribute":
                    if (filePath != null) {
                        System.out.print("Please enter the element ID: ");
                        elementId = scanner.nextLine();
                        System.out.print("Please enter the attribute key: ");
                        key = scanner.nextLine();
                        xmlAttributeDeleter.deleteAttributeByKey(filePath, elementId, key);
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "add_child":
                    if (filePath != null) {
                        System.out.print("Please enter the parent element ID: ");
                        String parentId = scanner.nextLine();
                        System.out.print("Please enter the new element ID: ");
                        String childId = scanner.nextLine();
                        System.out.print("Please enter the name of the new element: ");
                        String childElementName = scanner.nextLine();
                        xmlElementAdder.addChildElement(filePath, parentId, childId, childElementName);
                    } else {
                        System.out.println("No file is open. Please open an XML file first.");
                    }
                    break;
                case "exit":
                    exit = true;
                    break;
                case "help":
                    System.out.println(
                            "\nMenu:\n"+
                    "1.\"open_the_file\"               Opens an XML file\n"+
                    "2.\"display_the_content\"         Displays the file content\n"+
                    "3.\"extract_by_id\"               Extracts an element by ID\n"+
                    "4.\"update_attribute\"            Updates attribute value by ID and key\n"+
                    "5.\"extract_attributes\"          Extracts attributes of child elements by ID\n"+
                    "6.\"access_the_child\"            Gives access to the nth child of an element\n"+
                    "7.\"extract_the_text\"            Extracts text of an element by ID\n"+
                    "8.\"delete_attribute\"            Deletes an attribute by ID and key\n"+
                    "9.\"add_child\"                   Adds a new child element\n"+
                    "10. Exit\n"+
                    "11. Help\n"
                    );
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}



