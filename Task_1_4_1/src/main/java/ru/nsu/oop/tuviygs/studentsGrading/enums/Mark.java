package ru.nsu.oop.tuviygs.studentsGrading.enums;

public enum Mark {


    EXCELLENT(5),
    GOOD(4),
    SATISFACTORY(3),
    UNSATISFACTORY(2),
    PASSED(1),
    FAILED(0);


    /**
     * значение оценки.
     */
    private final int value;

    /**
     * установка значения оценки.
     *
     * @param value значение.
     */
    Mark(int value) {
        this.value = value;
    }

    /**
     * получение значения оценки.
     */
    public int getValue() {
        return this.value;
    }


}
