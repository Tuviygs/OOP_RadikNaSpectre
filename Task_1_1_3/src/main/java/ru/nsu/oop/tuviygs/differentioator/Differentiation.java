package ru.nsu.oop.tuviygs.differentioator;

import ru.nsu.oop.tuviygs.expressions.Add;
import ru.nsu.oop.tuviygs.expressions.Div;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.expressions.Mul;
import ru.nsu.oop.tuviygs.expressions.Number;
import ru.nsu.oop.tuviygs.expressions.Sub;

/**
 * дифференцирование выражений.
 */
public class Differentiation {

    /**
     * дифференцирование по переменной.
     *
     * @param expression - начальное выражение
     * @param variable - переменная дифференцирования
     */
    public static Expression differentiateExpression(Expression expression, String variable) {
        Expression newExpression;
        if (expression.getClass() == Add.class) {
            newExpression = differentiateAddExpression(expression, variable);
        } else if (expression.getClass() == Sub.class) {
            newExpression = differentiateSubExpression(expression, variable);
        } else if (expression.getClass() == Mul.class) {
            newExpression = differentiateMulExpression(expression, variable);
        } else if (expression.getClass() == Div.class) {
            newExpression = differentiateDivExpression(expression, variable);
        } else if (expression.getClass() == Number.class) {
            newExpression = new Number(0);
        } else {
            if (expression.getExpressionVisual().equals(variable)) {
                newExpression = new Number(1);
            } else {
                newExpression = new Number(0);
            }
        }

        return newExpression;
    }

    /**
     * дифференцирование сложения.
     *
     * @param expression - выражение.
     * @param variable - переменная дифференцирования.
     */
    private static Expression differentiateAddExpression(Expression expression, String variable) {
        Expression expression1 = ((Add) expression).getExpression1();
        Expression expression2 = ((Add) expression).getExpression2();

        Expression newExpression = new Add(differentiateExpression(expression1, variable),
                differentiateExpression(expression2, variable));
        return newExpression;
    }

    /**
     * дифференцирование разности.
     *
     * @param expression - выражение
     * @param variable - переменная дифференцирования
     */
    private static Expression differentiateSubExpression(Expression expression, String variable) {
        Expression expression1 = ((Sub) expression).getExpression1();
        Expression expression2 = ((Sub) expression).getExpression2();

        Expression newExpression = new Sub(differentiateExpression(expression1, variable),
                differentiateExpression(expression2, variable));
        return newExpression;
    }


    /**
     * дифференцирование произведения.
     *
     * @param expression - выражение
     * @param variable - переменная дифференцирования
     */
    private static Expression differentiateMulExpression(Expression expression, String variable) {
        Expression expression1 = ((Mul) expression).getExpression1();
        Expression expression2 = ((Mul) expression).getExpression2();

        Expression newExpression = new Add(
                new Mul(differentiateExpression(expression1, variable),
                        expression2),
                new Mul(expression1,
                        differentiateExpression(expression2, variable))
        );
        return newExpression;
    }


    /**
     * дифференцирование деления.
     *
     * @param expression - выражение
     * @param variable - переменная дифференцирования
     */
    private static Expression differentiateDivExpression(Expression expression, String variable) {
        Expression expression1 = ((Div) expression).getExpression1();
        Expression expression2 = ((Div) expression).getExpression2();

        Expression numerator = new Sub(
                new Mul(differentiateExpression(expression1, variable),
                        expression2),
                new Mul(expression1,
                        differentiateExpression(expression2, variable))
        );
        Expression newExpression = new Div(numerator,
                new Mul(expression2, expression2)
        );
        return newExpression;
    }
}
