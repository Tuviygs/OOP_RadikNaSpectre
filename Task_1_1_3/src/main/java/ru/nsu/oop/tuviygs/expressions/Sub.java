package ru.nsu.oop.tuviygs.expressions;


import java.util.Map;

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
                + "-" + expression2.getExpressionVisual() + ")";
    }


    /**
     * вычисление ыфчитания.
     *
     * @param variables - значения переменных
     */
    double calculateSub(Map<String, Integer> variables) {

        return this.expression1.calculate(variables)
                - this.expression2.calculate(variables);
    }

    /**
     * дифференцирование сложения.
     *
     * @param variable - переменная дифференцирования.
     */
    Expression differentiateSub(String variable) {

        return new Sub(this.expression1.differentiateExpression(variable),
                this.expression2.differentiateExpression(variable));
    }


}
