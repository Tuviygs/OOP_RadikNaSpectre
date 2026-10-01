package ru.nsu.oop.tuviygs.expressions;


import java.util.Map;

import ru.nsu.oop.tuviygs.exceptions.ExpressionException;

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

    /**
     * вычисление деления.
     *
     * @param variables - значения переменных
     */
    double calculateDiv(Map<String, Integer> variables) {
        if (this.expression2.calculate(variables) == 0.0) {
            throw new ExpressionException("Деление на ноль!!!\n");
        }

        return this.expression1.calculate(variables)
                / this.expression2.calculate(variables);
    }


    /**
     * дифференцирование деления.
     *
     * @param variable - переменная дифференцирования
     */
    Expression differentiateDiv(String variable) {

        Expression numerator = new Sub(
                new Mul(this.expression1.differentiateExpression(variable),
                        expression2),
                new Mul(expression1,
                        this.expression2.differentiateExpression(variable))
        );
        return new Div(numerator,
                new Mul(expression2, expression2)
        );
    }

}
