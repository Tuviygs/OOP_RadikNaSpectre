package ru.nsu.oop.tuviygs.IO;

import java.util.Scanner;

/**
 * контроллер ввода.
 */
public class InputController {

    /**
     * сканер.
     */
    private Scanner scanner;

    /**
     * получаемая строка.
     */
    private String input;

    /**
     * создание сканера.
     */
    public InputController() {
        this.scanner = new Scanner(System.in);
    }


    /**
     * получение ввода.
     */
    public String getInput() {
        input = scanner.nextLine();
        return input;
    }
}
