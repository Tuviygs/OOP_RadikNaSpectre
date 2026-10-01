package ru.nsu.oop.tuviygs.exceptions;


/**
 * ошибки при работе с выражениями.
 */
public class ExpressionException extends RuntimeException {

    /**
     * какая-либо ошибка.
     *
     * @param message - сообщение об ошибке.
     */
    public ExpressionException(String message) {
        super(message);
    }

}