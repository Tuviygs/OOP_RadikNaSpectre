package ru.nsu.oop.tuviygs.studentsGrading;

/**
 * исключения для зачётной книжки.
 */
public class GradebookException extends RuntimeException {

    /**
     * установка исключения.
     * @param message сообщение
     */
    public GradebookException(String message) {
        super(message);
    }
}
