package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testMainInteractiveScenario() {
        // Эмулируем ввод пользователя:
        // true (бюджет) -> 1 (добавить оценку) -> OOP -> 2 (EXAM) -> 1 (EXCELLENT)
        // -> 3 (посмотреть оценки) -> 4 (средний балл) -> 2 (след. семестр) -> 0 (выход)
        String simulatedInput = String.join("\n",
            "true",
            "1",
            "OOP",
            "2",
            "1",
            "3",
            "4",
            "2",
            "0"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        Main.main(new String[]{});

        String output = outContent.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Зачётка создана! Текущий семестр: 1, бюджет: true"));
        assertTrue(output.contains("Оценка успешно добавлена!"));
        assertTrue(output.contains("OOP [EXAM]: EXCELLENT"));
        assertTrue(output.contains("Текущий средний балл: 5,00")
            || output.contains("Текущий средний балл: 5.00"));
        assertTrue(output.contains("Перешли на 2 семестр."));
        assertTrue(output.contains("Выход из программы."));
    }

    @Test
    void testMainHandlesInvalidInputGracefully() {
        String simulatedInput = String.join("\n",
            "false",
            "99",      // Неизвестная команда
            "1",       // Добавить оценку
            "Math",
            "99",      // Неверный номер типа контроля -> IllegalArgumentException
            "0"        // Выход
        ) + "\n";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        Main.main(new String[]{});

        String output = outContent.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Неизвестная команда, попробуйте снова."));
        assertTrue(output.contains("[Ошибка ввода]: неверный номер типа контроля или оценки."));
        assertTrue(output.contains("Выход из программы."));
    }
}
