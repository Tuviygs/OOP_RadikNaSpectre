package ru.nsu.oop.tuviygs.parser;

import ru.nsu.oop.tuviygs.exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.expressions.Add;
import ru.nsu.oop.tuviygs.expressions.Div;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.expressions.Mul;
import ru.nsu.oop.tuviygs.expressions.Number;
import ru.nsu.oop.tuviygs.expressions.Sub;
import ru.nsu.oop.tuviygs.expressions.Variable;


/**
 * парсинг входящего выражения.
 */
public class StrokeParser {

    /**
     * разбиение строки на 2 слагаемых.
     *
     * @param startStroke - входная строка.
     */
    // (2+(3+(2*x)))
    // (((2*4)+3)+(2+4))+3)
    public static Expression parseExpression(String startStroke) {
        String stroke = ParseUtils.deleteExtraBrackets(startStroke);
        int dividingOperationIndex = ParseUtils.getDividingOperationIndex(stroke);

        Expression expression;
        if (dividingOperationIndex == -1) {
            expression = recognizeNumberOrVariable(stroke);

        } else {
            String expressionStroke1 = stroke.substring(0, dividingOperationIndex);
            String expressionStroke2 = stroke.substring(dividingOperationIndex + 1);
            Expression expression1 = parseExpression(expressionStroke1);
            Expression expression2 = parseExpression(expressionStroke2);
            if (stroke.charAt(dividingOperationIndex) == '+') {
                expression = new Add(expression1, expression2);
            }  else if (stroke.charAt(dividingOperationIndex) == '-') {
                expression = new Sub(expression1, expression2);
            } else if (stroke.charAt(dividingOperationIndex) == '*') {
                expression = new Mul(expression1, expression2);
            } else {
                expression = new Div(expression1, expression2);
            }

        }
        return expression;
    }


    private static Expression recognizeNumberOrVariable(String stroke) {
        int digitCounter = 0;
        int letterCounter = 0;
        Expression expression;
        for (char symbol : stroke.toCharArray()) {
            if (Character.isDigit(symbol)) {
                digitCounter++;
            } else if (Character.isLetter(symbol)) {
                letterCounter++;
            } else {
                throw new ExpressionException("Некорректный ввод.\n"
                    + "Обнаружен символ " + symbol
                    + ";\nДопустимы только цифры, буквы и символы {/, *, +, -, (, )}.\n"
                    + "Любая операция должна быть в скобках.\n");
            }
        }
        if (digitCounter == 0) {
            expression = new Variable(stroke);
        } else if (letterCounter == 0) {
            expression = new Number(Integer.parseInt(stroke));
        } else {
            throw new ExpressionException("Некорректное имя переменной: "
                + stroke
                + "\nNмя переменной должно содержать только буквы.\n");
        }
        return expression;
    }


}
