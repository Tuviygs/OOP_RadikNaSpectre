package ru.nsu.oop.tuviygs.expressions;

/**
 * общий класс выражения.
 */
public class Expression {



    /**
     * выражение в форме строки.
     */
    protected String visual;

    public Expression() {
    }

    /**
     * создание нового выражения.
     *
     * @param stroke - его представление в виде строки.
     */
    public Expression(String stroke) {
        this.visual = stroke;
    }

    /**
     * получение текстового формата выражения.
     */
    public String getExpressionVisual() {
        return this.visual;
    }


}
