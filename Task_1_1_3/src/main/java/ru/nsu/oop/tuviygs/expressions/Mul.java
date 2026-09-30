package ru.nsu.oop.tuviygs.expressions;

/**
 * умножение.
 */
public class Mul extends BinExpression {

    /**
     * создание умножения.
     */
    public Mul(Expression expression1, Expression expression2) {
        super(expression1, expression2);
        this.visual = "(" + expression1.getExpressionVisual()
                + "*" + expression2.getExpressionVisual() + ")";
    }
}
