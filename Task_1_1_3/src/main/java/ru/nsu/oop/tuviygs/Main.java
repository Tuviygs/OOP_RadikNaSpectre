package ru.nsu.oop.tuviygs;

import ru.nsu.oop.tuviygs.Calculator.Calculation;
import ru.nsu.oop.tuviygs.Exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.Expressions.Add;
import ru.nsu.oop.tuviygs.Expressions.Expression;
import ru.nsu.oop.tuviygs.Expressions.Mul;
import ru.nsu.oop.tuviygs.Expressions.Variable;
import ru.nsu.oop.tuviygs.Expressions.Number;
import ru.nsu.oop.tuviygs.IO.OutputController;
import ru.nsu.oop.tuviygs.Differentioator.Differentiation;

/**
 * мейн.
 */
public class Main {

    /**
     * мейн.
     *
     * @param args - параметры.
     */
    public static void main(String[] args) {

        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));

        OutputController.printExpression(e);

        try {
            Expression de = Differentiation.differentiateExpression(e, "x");
            OutputController.printExpression(de);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
            return;
        }

        try {
            double result = Calculation.calculateExpression(e, "x = 10; y = 13");
            System.out.println(result);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
            return;
        }

    }
}