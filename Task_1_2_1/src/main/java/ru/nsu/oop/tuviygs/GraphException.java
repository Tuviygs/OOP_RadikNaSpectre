package ru.nsu.oop.tuviygs;

/**
 * ошибки при работе с графом.
 */
public class GraphException extends RuntimeException {

    /**
     * ошибка.
     *
     * @param message - текст ошибки
     */
    public GraphException(String message) {
        super(message);
    }
}
