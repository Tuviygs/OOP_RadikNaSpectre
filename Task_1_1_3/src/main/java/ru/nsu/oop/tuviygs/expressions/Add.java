package ru.nsu.oop.tuviygs.expressions;

import java.util.Map;

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

    /**
     * вычисление сложения.
     *
     * @param variables - значения переменных
     */
    double calculateAdd(Map<String, Integer> variables) {

        return this.expression1.calculate(variables)
                + this.expression2.calculate(variables);
    }


    /**
     * дифференцирование сложения.
     *
     * @param variable - переменная дифференцирования.
     */
    Expression differentiateAdd(String variable) {

        return new Add(this.expression1.differentiateExpression(variable),
                this.expression2.differentiateExpression(variable));
    }

}
