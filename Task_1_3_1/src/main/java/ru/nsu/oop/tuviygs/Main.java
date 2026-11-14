package ru.nsu.oop.tuviygs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import ru.nsu.oop.tuviygs.core.SubstringFinder;

/**
 * мейн.
 */
public class Main {

    /**
     * мейн.
     *
     * @param args параметры
     */
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.print("Введите путь до файла: ");
            String filePath = scanner.nextLine();

            System.out.print("Введите искомую подстроку: ");
            String substring = scanner.nextLine();

            SubstringFinder finder = new SubstringFinder();
            List<Integer> indices = finder.findSubstring(filePath, substring);

            if (indices.isEmpty()) {
                System.out.println("Вхождения не найдены.");
            } else {
                System.out.println("Найденные индексы вхождений: " + indices);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }


    }
}