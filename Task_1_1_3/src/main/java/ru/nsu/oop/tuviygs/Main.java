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
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
            return;
        }
        OutputController.printExpression(e);


        inputString = inputController.getInput();
        double value;
        try {
            value = e.calculateExpression(inputString);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
            return;
        }
        OutputController.printValue(value);



        inputString = inputController.getInput();
        Expression ed;
        try {
            ed = e.differentiateExpression(inputString);
        } catch (ExpressionException exception) {
            OutputController.printException(exception);
            return;
        }
        OutputController.printExpression(ed);

    }
}