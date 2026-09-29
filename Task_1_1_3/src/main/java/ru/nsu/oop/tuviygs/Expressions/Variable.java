package ru.nsu.oop.tuviygs.Expressions;

/**
 * переменные.
 */
public class Variable extends Expression {

    /**
     * создание переменной.
     *
     * @param name - её название.
     */
    public Variable(String name) {
        this.visual = name;
    }
}
