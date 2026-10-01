package ru.nsu.oop.tuviygs.io;

import java.util.Scanner;

/**
 * контроллер ввода.
 */
public class InputController {

    private final Scanner scanner;

    /**
     * инициализация сканера.
     */
    public InputController() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Считывание строки.
     *
     * @return Введенная строка.
     */
    public String getInput() {
        return scanner.nextLine();
    }
}
