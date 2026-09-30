package ru.nsu.oop.tuviygs.io;

import ru.nsu.oop.tuviygs.expressions.Expression;

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

