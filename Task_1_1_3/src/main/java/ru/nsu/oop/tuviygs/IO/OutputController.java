package ru.nsu.oop.tuviygs.IO;

import ru.nsu.oop.tuviygs.Exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.Expressions.Expression;

/**
 * контроллер вывода.
 */
public class OutputController {

    /**
     * вывод внешнего вида выражения в консоль.
     *
     * @param expression - выражение
     */
    public static void printExpression(Expression expression) {
        System.out.println(expression.getExpressionVisual());
    }

    /**
     * вывод ошибки.
     *
     * @param exception - ошибка
     */
    public static void printException(RuntimeException exception) {
        System.out.println(exception.getMessage());
    }
}

