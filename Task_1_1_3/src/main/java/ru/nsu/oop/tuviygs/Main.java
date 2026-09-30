package ru.nsu.oop.tuviygs;

import ru.nsu.oop.tuviygs.calculator.Calculation;
import ru.nsu.oop.tuviygs.exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.expressions.Add;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.expressions.Mul;
import ru.nsu.oop.tuviygs.expressions.Variable;
import ru.nsu.oop.tuviygs.expressions.Number;
import ru.nsu.oop.tuviygs.io.OutputController;
import ru.nsu.oop.tuviygs.differentioator.Differentiation;

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