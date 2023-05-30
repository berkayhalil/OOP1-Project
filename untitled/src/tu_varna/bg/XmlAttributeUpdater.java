package tu_varna.bg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

// Класът XmlAttributeUpdater се използва за обновяване на атрибут в XML файл по зададено ID на елемент.
public class XmlAttributeUpdater {

    // Методът updateAttributeById приема път до файл, ID на елемент, име на атрибут и стойност на атрибут като аргументи и обновява стойността на атрибута.
    public void updateAttributeById(String filePath, String elementId, String attributeKey, String attributeValue) {
        try {
            File file = new File(filePath);
            Path path = file.toPath();

            // Четене на всички редове от файла
            List<String> lines = Files.readAllLines(path);

            // Обхождане на всички редове
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                // Проверка дали текущият ред съдържа зададеното ID на елемента
                if (line.contains("id=\"" + elementId + "\"")) {

                    // Ако съдържа, обновяваме атрибута със зададеното име
                    String updatedLine = updateAttribute(line, attributeKey, attributeValue);
                    // Заменяме стария ред с новия
                    lines.set(i, updatedLine);
                    break;
                }
            }

            // Записваме промените във файла
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Атрибутът е успешно променен.");
        } catch (IOException e) {
            System.out.println("Грешка при промяна на атрибута: " + e.getMessage());
        }
    }

    // Методът updateAttribute обновява стойността на атрибута в даден ред и връща обновения ред.
    private String updateAttribute(String line, String attributeKey, String attributeValue) {
        String updatedLine = line;
        // Намиране на началната и крайната позиция на стойността на атрибута
        int startIndex = line.indexOf(attributeKey + "=\"") + attributeKey.length() + 2;
        int endIndex = line.indexOf("\"", startIndex);
        // Проверка дали атрибутът е намерен в реда
        if (startIndex > attributeKey.length() + 2 && endIndex > startIndex) {
            // Ако атрибутът е намерен, обновяваме стойността му
            updatedLine = line.substring(0, startIndex) + attributeValue + line.substring(endIndex);
        }
        return updatedLine;
    }
}



