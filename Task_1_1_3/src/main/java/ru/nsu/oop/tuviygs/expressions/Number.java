package ru.nsu.oop.tuviygs.expressions;

/**
 * константы.
 */
public class Number extends Expression {

    /**
     * значение.
     */
    private int value;


    /**
     * создание константы.
     *
     * @param value - её значение.
     */
    public Number(int value) {
        this.value = value;
        this.visual = Integer.toString(value);
    }

    /**
     * получение значения.
     */
    public int getValue() {
        return this.value;
    }

}
