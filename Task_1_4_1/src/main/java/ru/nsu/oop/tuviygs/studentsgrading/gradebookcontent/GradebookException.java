package ru.nsu.oop.tuviygs.studentsgrading.gradebookcontent;

/**
 * исключения для зачётной книжки.
 */
public class GradebookException extends RuntimeException {

    /**
     * установка исключения.
     *
     * @param message сообщение
     */
    public GradebookException(String message) {
        super(message);
    }
}
