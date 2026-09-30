package ru.nsu.oop.tuviygs.expressions;

import java.util.Map;

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


    /**
     * вычисление произведения.
     *
     * @param variables - значения переменных
     */
    double calculateMul(Map<String, Integer> variables) {

        return this.expression1.calculate(variables)
                * this.expression2.calculate(variables);
    }

    /**
     * дифференцирование произведения.
     *
     * @param variable - переменная дифференцирования
     */
    Expression differentiateMul(String variable) {


        return new Add(
                new Mul(this.expression1.differentiateExpression(variable),
                        expression2),
                new Mul(expression1,
                        this.expression2.differentiateExpression(variable))
        );
    }
}
