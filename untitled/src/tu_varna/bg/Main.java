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
            System.out.println("Меню:");
            System.out.println("1. Отваряне на XML файл");
            System.out.println("2. Показване на съдържанието на файла");
            System.out.println("3. Извличане на елемент по ID");
            System.out.println("4. Промяна на стойност на атрибут по ID и ключ");
            System.out.println("5. Извличане на атрибути на вложените елементи по ID");
            System.out.println("6. Достъп до n-тия наследник на елемент");
            System.out.println("7. Извличане на текста на елемент по ID");
            System.out.println("8. Изтриване на атрибут по ID и ключ");
            System.out.println("9. Добавяне на нов наследник на елемент");
            System.out.println("10. Изход");

            System.out.print("Моля, изберете опция: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Изчистване на новия ред

            switch (choice) {
                case 1:
                    System.out.print("Моля, въведете пътя до XML файла: ");
                    filePath = scanner.nextLine();
                    fileParser.parseFile(filePath);
                    break;
                case 2:
                    if (filePath != null) {
                        fileDisplayer.displayFileContent(filePath);
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 3:
                    if (filePath != null) {
                        System.out.print("Моля, въведете ID на елемента: ");
                        elementId = scanner.nextLine();
                        xmlElementExtractor.extractElementById(filePath, elementId);
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 4:
                    System.out.print("Въведете ID на елемента: ");
                    elementId = scanner.nextLine();
                    System.out.print("Въведете ключа на атрибута: ");
                    String attributeKey = scanner.nextLine();
                    System.out.print("Въведете новата стойност на атрибута: ");
                    String attributeValue = scanner.nextLine();
                    xmlAttributeUpdater.updateAttributeById(filePath, elementId, attributeKey, attributeValue);
                    break;
                case 5:
                    if (filePath != null) {
                        System.out.print("Моля, въведете ID на елемента: ");
                        elementId = scanner.nextLine();

                        // Използване на XmlChildrenAttributesExtractor за извличане на атрибутите
                        List<String> attributes = xmlChildrenAttributesExtractor.extractChildAttributesById(filePath, elementId);

                        if (attributes.isEmpty()) {
                            System.out.println("Няма вложени елементи с атрибути за това ID.");
                        } else {
                            System.out.println("Атрибутите на вложените елементи са:");
                            for (String attribute : attributes) {
                                System.out.println(attribute);
                            }
                        }
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 6:
                    if (filePath != null) {
                        System.out.print("Моля, въведете ID на елемента: ");
                        elementId = scanner.nextLine();
                        System.out.print("Моля, въведете номера на наследника, който искате да достъпите: ");
                        nthChild = scanner.nextInt();
                        scanner.nextLine(); // Изчистване на новия ред
                        Element child = xmlChildAccess.getChild(filePath, elementId, nthChild);
                        if (child != null) {
                            System.out.println("Намереният наследник е: " + child);
                        } else {
                            System.out.println("Не беше намерен наследник с указания номер.");
                        }
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 7:
                    if (filePath != null) {
                        System.out.print("Моля, въведете ID на елемента: ");
                        elementId = scanner.nextLine();
                        textElementExtractor.extractElementTextById(filePath, elementId);
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 8:
                    if (filePath != null) {
                        System.out.print("Моля, въведете ID на елемента: ");
                        elementId = scanner.nextLine();
                        System.out.print("Моля, въведете ключа на атрибута: ");
                        key = scanner.nextLine();
                        xmlAttributeDeleter.deleteAttributeByKey(filePath, elementId, key);
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 9:
                    if (filePath != null) {
                        System.out.print("Моля, въведете ID на родителския елемент: ");
                        String parentId = scanner.nextLine();
                        System.out.print("Моля, въведете ID на новия елемент: ");
                        String childId = scanner.nextLine();
                        System.out.print("Моля, въведете името на новия елемент: ");
                        String childElementName = scanner.nextLine();
                        xmlElementAdder.addChildElement(filePath, parentId, childId, childElementName);
                    } else {
                        System.out.println("Няма отворен файл. Моля, отворете XML файла първо.");
                    }
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Невалидна опция.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}


