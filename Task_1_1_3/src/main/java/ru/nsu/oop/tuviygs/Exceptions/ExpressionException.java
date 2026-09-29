package ru.nsu.oop.tuviygs.Exceptions;


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