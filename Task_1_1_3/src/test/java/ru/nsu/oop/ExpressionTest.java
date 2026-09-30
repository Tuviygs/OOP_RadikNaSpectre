package ru.nsu.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.expressions.Add;
import ru.nsu.oop.tuviygs.expressions.Div;
import ru.nsu.oop.tuviygs.expressions.Expression;
import ru.nsu.oop.tuviygs.expressions.Mul;
import ru.nsu.oop.tuviygs.expressions.Number;
import ru.nsu.oop.tuviygs.expressions.Sub;
import ru.nsu.oop.tuviygs.expressions.Variable;
import ru.nsu.oop.tuviygs.parser.StringParser;



/**
 * тесты.
 */
public class ExpressionTest {

    /**
     * тест константы.
     */
    @Test
    void parseSingleNumber() {
        Expression e = StringParser.parseExpression("42");
        assertInstanceOf(Number.class, e);
        assertEquals(42, ((ru.nsu.oop.tuviygs.expressions.Number) e).getValue());
    }


    /**
     * тест переменной.
     */
    @Test
    void parseSingleVariable() {
        Expression e = StringParser.parseExpression("x");
        assertInstanceOf(Variable.class, e);
        assertEquals("x", e.getExpressionVisual());
    }

    /**
     * тест многобуквенной переменноц.
     */
    @Test
    void parseMultiLetterVariable() {
        Expression e = StringParser.parseExpression("abc");
        assertInstanceOf(Variable.class, e);
        assertEquals("abc", e.getExpressionVisual());
    }

    /**
     * тест сложения.
     */
    @Test
    void parseAddition() {
        Expression e = StringParser.parseExpression("(2+3)");
        assertInstanceOf(Add.class, e);
        assertEquals("(2+3)", e.getExpressionVisual());
    }

    /**
     * тест разности.
     */
    @Test
    void parseSubtraction() {
        Expression e = StringParser.parseExpression("(5-2)");
        assertInstanceOf(Sub.class, e);
        assertEquals("(5-2)", e.getExpressionVisual());
    }

    /**
     * тест произведения.
     */
    @Test
    void parseMultiplication() {
        Expression e = StringParser.parseExpression("(4*7)");
        assertInstanceOf(Mul.class, e);
        assertEquals("(4*7)", e.getExpressionVisual());
    }

    /**
     * тест деления.
     */
    @Test
    void parseDivision() {
        Expression e = StringParser.parseExpression("(8/2)");
        assertInstanceOf(Div.class, e);
        assertEquals("(8/2)", e.getExpressionVisual());
    }


    @Test
    void parseNestedExpression() {
        Expression e = StringParser.parseExpression("(2+(3*x))");
        assertInstanceOf(Add.class, e);
        Add add = (Add) e;
        assertInstanceOf(Number.class, add.getExpression1());
        assertInstanceOf(Mul.class, add.getExpression2());
    }

}