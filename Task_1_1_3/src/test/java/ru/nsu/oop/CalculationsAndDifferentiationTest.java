package ru.nsu.oop;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.parser.StringParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * тесты вычисления и дифференцирования.
 */
public class CalculationsAndDifferentiationTest {

    /**
     * вычисления + и *.
     */
    @Test
    void calculationTest() {
        Expression e = StringParser.parseExpression("((2+x)*3)");
        double val = e.calculateExpression("x = 6");
        assertEquals(24.0, val);
    }

    /**
     * вычисления -..
     */
    @Test
    void calculationTest2() {
        Expression e = StringParser.parseExpression("((5-x)*(x*3))");
        double val = e.calculateExpression("x = 2");
        assertEquals(18.0, val);
    }

    /**
     * вычисления /.
     */
    @Test
    void calculationTest3() {
        Expression e = StringParser.parseExpression("((8/4)*x)");
        double val = e.calculateExpression("x = 6");
        assertEquals(12.0, val);
    }


    /**
     * дифференцирования + и *.
     */
    @Test
    void differentiationTest() {
        Expression e = StringParser.parseExpression("((2+x)*3)");
        Expression de = e.differentiateExpression("x");
        assertEquals("(((0+1)*3)+((2+x)*0))", de.getExpressionVisual());
    }

    /**
     * вычисления - и /.
     */
    @Test
    void differentiationTest2() {
        Expression e = StringParser.parseExpression("((2-x)/3)");
        Expression de = e.differentiateExpression("x");
        assertEquals("((((0-1)*3)-((2-x)*0))/(3*3))", de.getExpressionVisual());
    }
}
