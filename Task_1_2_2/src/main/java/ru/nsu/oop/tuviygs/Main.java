package ru.nsu.oop.tuviygs;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        HashTable<String, String> table = new HashTable<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите 5 пар (ключ и значение через пробел):");
        for (int i = 1; i <= 5; i++) {
            String key = scanner.next();
            String value = scanner.next();
            table.add(key, value);
        }
        System.out.println("\nТекущее содержимое таблицы:");
        System.out.println(table);

        System.out.println("Введите КЛЮЧ пары, которую хотите изменить, и его НОВОЕ значение через пробел:");
        String keyToUpdate = scanner.next();
        String newValue = scanner.next();
        try {
            table.setValue(keyToUpdate, newValue);
        } catch (HashTableException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nТаблица после изменения:");
        System.out.println(table);

        System.out.println("Для удаления введите КЛЮЧ и ТЕКУЩЕЕ ЗНАЧЕНИЕ пары через пробел:");
        String keyToDelete = scanner.next();
        String valueToDelete = scanner.next();
        try {
            table.delete(keyToDelete, valueToDelete);
            System.out.println("Пара успешно удалена!");
        } catch (HashTableException e) {
            System.out.println("Ошибка удаления: " + e.getMessage());
        }

        System.out.println("\nФинальное содержимое таблицы:");
        System.out.println(table);

        scanner.close();


    }
}