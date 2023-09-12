package tu_varna.bg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Класът FileParser се използва за четене и обработка на файл.
public class FileParser {

    public void parseFile(String filePath) {
        File file = new File(filePath);

        // Проверка дали файлът съществува и дали е файл (не е директория)
        if (file.exists() && file.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                // Пропускане на първия ред
                reader.readLine();

                System.out.println("The contents of a file are:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                reader.close();
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("The file does not exist or is not a file.");
        }
    }
}

