package ru.nsu.oop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.exceptions.ExpressionException;
import ru.nsu.oop.tuviygs.parser.StringParser;



/**
 * тесты ошибок.
 */
public class ExceptionsTest {

    /**
     * тест неверного символа.
     */
    @Test
    void invalidCharacterThrows() {
        assertThrows(ExpressionException.class,
                () -> StringParser.parseExpression("(2+3$)"));
    }

    /**
     * тест неверного названия переменной.
     */
    @Test
    void mixedLettersAndDigitsInOperandThrows() {
        assertThrows(ExpressionException.class,
                () -> StringParser.parseExpression("(x1+y)"));
    }
}
