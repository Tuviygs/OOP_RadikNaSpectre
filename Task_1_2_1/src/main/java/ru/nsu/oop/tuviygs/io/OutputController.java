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
     * выбор реализации графа.
     */
    public static void printLetChooseRepresentation() {
        System.out.println("Выберите реализацию графа:");
        System.out.println("1 - на матрице смежности");
        System.out.println("2 - на списках смежности");
        System.out.println("3 - на матрице инцидентности");
    }

    /**
     * вывод ошибки.
     * @param exception ошибка
     */
    public static void printException(RuntimeException exception) {
        System.out.println(exception.getMessage());
    }


    /**
     * запрос ввести имя файла.
     */
    public static void printAscFileName() {
        System.out.println("Введите имя файла (с расшрением)");
    }
}
