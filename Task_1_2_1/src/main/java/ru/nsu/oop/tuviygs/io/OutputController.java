package ru.nsu.oop.tuviygs.io;

import java.util.List;

/**
 * вывод.
 */
public class OutputController {

    /**
     * вывод списка интов.
     *
     * @param list - список
     */
    public static void printIntegerList(List<Integer> list) {
        String string = "";
        for (int number : list) {
            string += Integer.toString(number) + " ";
        }
        System.out.println(string);
    }


    /**
     * запрос ввести имя файла.
     */
    public static void printAscFileName() {
        System.out.println("Введите имя файла (с расшрением)");
    }
}
