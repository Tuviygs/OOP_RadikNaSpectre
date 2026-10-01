package ru.nsu.oop.tuviygs.expressions;


import java.util.Map;

import ru.nsu.oop.tuviygs.exceptions.ExpressionException;

/**
 * переменные.
 */
public class Variable extends Expression {

    /**
     * создание переменной.
     *
     * @param name - её название.
     */
    public Variable(String name) {
        this.visual = name;
    }


    /**
     * означивание переменной..
     *
     * @param variables - значения переменных
     */
    double calculateVariable(Map<String, Integer> variables) {

        String name = this.getExpressionVisual();
        if (variables.containsKey(name)) {
            return ((Integer) variables.get(name)).doubleValue();
        } else {
            throw new ExpressionException("Не все переменные были означены.\n");
        }
    }



    /**
     * дифференцирование переменной.
     *
     * @param variable - переменная дифференцирования.
     */
    Expression differentiateVariable(String variable) {
        if (this.getExpressionVisual().equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }
}
