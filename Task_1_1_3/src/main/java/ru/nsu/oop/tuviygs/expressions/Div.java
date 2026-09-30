package ru.nsu.oop.tuviygs.expressions;

/**
 * деление.
 */
public class Div extends BinExpression {

    /**
     * создание деления.
     */
    public Div(Expression expression1, Expression expression2) {
        super(expression1, expression2);
        this.visual = "(" + expression1.getExpressionVisual()
                + "/" + expression2.getExpressionVisual() + ")";
    }

}
