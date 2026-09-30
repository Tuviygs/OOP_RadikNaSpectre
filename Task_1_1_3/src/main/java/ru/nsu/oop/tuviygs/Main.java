package ru.nsu.oop.tuviygs;

import ru.nsu.oop.tuviygs.exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.expressions.Add;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.expressions.Mul;
import ru.nsu.oop.tuviygs.expressions.Variable;
import ru.nsu.oop.tuviygs.expressions.Number;
import ru.nsu.oop.tuviygs.io.InputController;
import ru.nsu.oop.tuviygs.io.OutputController;
import ru.nsu.oop.tuviygs.parser.StringParser;

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

        InputController inputController = new InputController();

        String inputString = inputController.getInput();
        Expression e = StringParser.parseExpression(inputString);
        OutputController.printExpression(e);

        inputString = inputController.getInput();
        double value = e.calculateExpression(inputString);
        OutputController.printValue(value);

        inputString = inputController.getInput();
        Expression ed = e.differentiateExpression(inputString);
        OutputController.printExpression(ed);
    }
}