package ru.nsu.oop.tuviygs.expressions;

/**
 * сложение.
 */
public class Add extends BinExpression {

    /**
     * создание сложения.
     */
    public Add(Expression expression1, Expression expression2) {
        super(expression1, expression2);
        this.visual = "(" + expression1.getExpressionVisual()
                + "+" + expression2.getExpressionVisual() + ")";
    }

}
