package tu_varna.bg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Класът FileDisplayer се използва за извеждане на съдържанието на файл.
public class FileDisplayer {

    // Методът displayFileContent приема път до файл като аргумент и извежда съдържанието му.
    public void displayFileContent(String filePath) {
        File file = new File(filePath);

        // Проверка дали файлът съществува и дали е файл (не е директория)
        if (file.exists() && file.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

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

