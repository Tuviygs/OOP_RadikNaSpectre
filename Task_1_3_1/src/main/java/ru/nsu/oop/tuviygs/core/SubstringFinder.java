package ru.nsu.oop.tuviygs.core;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * поиск подстроки.
 */
public class SubstringFinder {

    /**
     * поиск вхождений подстроки в файле.
     *
     * @param filePath путь до файла.
     * @param substring подстрока.
     * @throws IOException исключение.
     */
    public List<Integer> findSubstring(String filePath, String substring)
        throws IOException {

        if (substring == null || substring.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {

            int charCode;
            int currentIndex = 0;
            StringBuilder currentString = new StringBuilder();

            for (int i = 0; i < substring.length(); i++) {
                charCode = reader.read();;
                if (charCode != -1) {
                    currentString.append((char) charCode);
                } else {
                    return list;
                }
            }

            if (currentString.toString().equals(substring)) {
                list.add(currentIndex);
            }

            while ((charCode = reader.read()) != -1) {

                currentIndex++;
                currentString.deleteCharAt(0);
                currentString.append((char) charCode);

                if (currentString.toString().equals(substring)) {
                    list.add(currentIndex);
                }
            }


        }
        return list;
    }


}
