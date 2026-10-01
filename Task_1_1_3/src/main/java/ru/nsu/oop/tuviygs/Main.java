package ru.nsu.oop.tuviygs;

import ru.nsu.oop.tuviygs.exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.expressions.Expression;
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
        Expression e;
        try {
            e = StringParser.parseExpression(inputString);
            OutputController.printExpression(e);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
            return;
        }


        inputString = inputController.getInput();
        try {
            double value = e.calculateExpression(inputString);
            OutputController.printValue(value);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
        }



        inputString = inputController.getInput();
        try {
            Expression ed = e.differentiateExpression(inputString);
            OutputController.printExpression(ed);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
        }

    }
}