package ru.nsu.oop.tuviygs.expressions;


/**
 * разность.
 */
public class Sub extends BinExpression {

    /**
     * создание разности.
     */
    public Sub(Expression expression1, Expression expression2) {
        super(expression1, expression2);
        this.visual = "(" + expression1.getExpressionVisual()
                + "*" + expression2.getExpressionVisual() + ")";
    }


}
