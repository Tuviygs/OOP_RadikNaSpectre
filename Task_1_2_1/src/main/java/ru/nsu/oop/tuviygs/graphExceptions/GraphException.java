package ru.nsu.oop.tuviygs.graphExceptions;

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
