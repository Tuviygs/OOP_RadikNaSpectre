package ru.nsu.oop;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import ru.nsu.oop.tuviygs.io.InputController;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * тест ввода
 */
class InputControllerTest {

    /**
     * подмена потока ввода.
     */
    private InputStream originalIn;

    /**
     * тест ввода.
     */
    @Test
    void readsSingleLine() {

        System.setIn(originalIn);

        System.setIn(new ByteArrayInputStream("hello\n".getBytes()));

        InputController controller = new InputController();

        assertEquals("hello", controller.getInput());
        originalIn = System.in;
    }



}