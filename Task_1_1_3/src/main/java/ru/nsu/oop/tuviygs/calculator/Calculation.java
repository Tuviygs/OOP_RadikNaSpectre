package ru.nsu.oop.tuviygs.calculator;


import ru.nsu.oop.tuviygs.exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.expressions.Add;
import ru.nsu.oop.tuviygs.expressions.Div;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.expressions.Mul;
import ru.nsu.oop.tuviygs.expressions.Number;
import ru.nsu.oop.tuviygs.expressions.Sub;
import ru.nsu.oop.tuviygs.expressions.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * вычисление при означивании.
 */
public class Calculation {

    /**
     * вычисление выражения при оначивании.
     *
     * @param expression - выражение
     * @param stroke - строка с означиванием
     */
    public static double calculateExpression(Expression expression, String stroke) {
        Map<String, Integer> variables = splitStroke(stroke);
        double result = calculate(expression, variables);
        return result;
    }

    /**
     * вычисление выражения при оначивании.
     *
     * @param expression - выражение
     * @param variables - означивание
     */
    private static double calculate(Expression expression, Map variables) {

        double result;
        if (expression.getClass() == Add.class) {
            result = calculateAdd(expression, variables);
        } else if (expression.getClass() == Sub.class) {
            result = calculateSub(expression, variables);
        } else if (expression.getClass() == Mul.class) {
            result = calculateMul(expression, variables);
        } else if (expression.getClass() == Div.class) {
            result = calculateDiv(expression, variables);
        } else if (expression.getClass() == Variable.class) {
            result = calculateVariable(expression, variables);
        } else {
            result = (double)((Number) expression).getValue();
        }
        return result;
    }


    /**
     * создание словаря с переменными.
     *
     * @param stroke - строка означивания
     */
    private static Map splitStroke(String stroke) {
        Map<String, Integer> variables = new HashMap<>();
        String[] substrokes = stroke.split("\\s*;\\s*");
        for (String substroke : substrokes) {
            String[] variable = substroke.split("\\s*=\\s*");
            int value = Integer.parseInt(variable[1]);
            variables.put(variable[0], value);
        }

        return variables;
    }

    /**
     * вычисление сложения.
     *
     * @param expression - выражение
     * @param variables - значения переменных
     */
    private static double calculateAdd(Expression expression, Map variables) {
        Expression expression1 = ((Add) expression).getExpression1();
        Expression expression2 = ((Add) expression).getExpression2();

        double result = calculate(expression1, variables)
                + calculate(expression2, variables);
        return result;
    }

    /**
     * вычисление ыфчитания.
     *
     * @param expression - выражение
     * @param variables - значения переменных
     */
    private static double calculateSub(Expression expression, Map variables) {
        Expression expression1 = ((Sub) expression).getExpression1();
        Expression expression2 = ((Sub) expression).getExpression2();

        double result = calculate(expression1, variables)
                - calculate(expression2, variables);
        return result;
    }


    /**
     * вычисление произведения.
     *
     * @param expression - выражение
     * @param variables - значения переменных
     */
    private static double calculateMul(Expression expression, Map variables) {
        Expression expression1 = ((Mul) expression).getExpression1();
        Expression expression2 = ((Mul) expression).getExpression2();

        double result = calculate(expression1, variables)
                * calculate(expression2, variables);
        return result;
    }


    /**
     * вычисление деления.
     *
     * @param expression - выражение
     * @param variables - значения переменных
     */
    private static double calculateDiv(Expression expression, Map variables) {
        Expression expression1 = ((Div) expression).getExpression1();
        Expression expression2 = ((Div) expression).getExpression2();

        if (calculate(expression2, variables) == 0.0) {
            throw new ExpressionException("Деление на ноль!!!\n");
        }

        double result = calculate(expression1, variables)
                / calculate(expression2, variables);
        return result;
    }

    /**
     * означивание переменной..
     *
     * @param expression - выражение
     * @param variables - значения переменных
     */
    private static double calculateVariable(Expression expression, Map variables) {
        double result;

        String name = expression.getExpressionVisual();
        if (variables.containsKey(name)) {
            result = ((Integer) variables.get(name)).doubleValue();
        } else {
            throw new ExpressionException("Не все переменные были означены.\n");
        }


        return result;
    }

}
