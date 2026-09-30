package ru.nsu.oop.tuviygs.expressions;

import ru.nsu.oop.tuviygs.exceptions.ExpressionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * общий класс выражения.
 */
public class Expression {



    /**
     * выражение в форме строки.
     */
    protected String visual;

    public Expression() {
    }

    /**
     * создание нового выражения.
     *
     * @param stroke - его представление в виде строки.
     */
    public Expression(String stroke) {
        this.visual = stroke;
    }

    /**
     * получение текстового формата выражения.
     */
    public String getExpressionVisual() {
        return this.visual;
    }


    /**
     * вычисление выражения при оначивании.
     *
     * @param string - строка с означиванием
     */
    public  double calculateExpression(String string) {
        Map<String, Integer> variables = splitStroke(string);
        double result = this.calculate(variables);
        return result;
    }

    /**
     * вычисление выражения при оначивании.
     *
     * @param variables - означивание
     */
    double calculate(Map<String, Integer> variables) {

        return switch (this) {
            case Add add       -> add.calculateAdd(variables);
            case Sub sub       -> sub.calculateSub(variables);
            case Mul mul       -> mul.calculateMul(variables);
            case Div div       -> div.calculateDiv(variables);
            case Variable var  -> var.calculateVariable(variables);
            case Number num    -> (double) num.getValue();

            default -> throw new ExpressionException("Неизвестный тип выражения: " + this.getClass());
        };
    }

    /**
     * дифференцирование по переменной.
     *
     * @param variable - переменная дифференцирования
     */
    public  Expression differentiateExpression(String variable) {

        return switch (this) {
            case Add add       -> add.differentiateAdd(variable);
            case Sub sub       -> sub.differentiateSub(variable);
            case Mul mul       -> mul.differentiateMul(variable);
            case Div div       -> div.differentiateDiv(variable);
            case Variable var  -> var.differentiateVariable(variable);
            case Number num    -> new Number(0);

            default -> throw new ExpressionException("Неизвестный тип выражения: " + this.getClass());
        };

    }
    /**
     * создание словаря с переменными.
     *
     * @param string - строка означивания
     */
    private static Map<String, Integer> splitStroke(String string) {
        Map<String, Integer> variables = new HashMap<>();
        List<String> substrokes = List.of(string.split("\\s*;\\s*"));
        for (String substroke : substrokes) {
            List<String> variable = List.of(substroke.split("\\s*=\\s*"));
            int value = Integer.parseInt(variable.get(1));
            variables.put(variable.get(0), value);
        }

        return variables;
    }

}
