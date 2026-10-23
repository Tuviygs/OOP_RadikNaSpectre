package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.io.OutputController;

/**
 * Класс для тестирования контроллера вывода OutputController.
 * Перехватывает стандартный поток вывода System.out для проверки текстовых сообщений.
 */
public class OutputControllerTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Настройка тестового окружения перед каждым тестом.
     * Перенаправляет поток System.out во внутренний массив байт для последующего анализа.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Восстановление тестового окружения после каждого теста.
     * Возвращает стандартный поток вывода системы в исходное состояние.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Тест вывода списка целых чисел.
     * Проверяет, что числа выводятся через пробел с финальным переводом строки.
     */
    @Test
    void testPrintIntegerList() {
        List<Integer> list = List.of(0, 1, 2, 3);
        OutputController.printIntegerList(list);

        // trim() убирает системные переводы строк (\n или \r\n) в конце
        assertEquals("0 1 2 3", outputStreamCaptor.toString().trim());
    }

    /**
     * Тест вывода пустого списка целых чисел.
     * Проверяет, что при пустом списке выводится пустая строка.
     */
    @Test
    void testPrintEmptyIntegerList() {
        OutputController.printIntegerList(List.of());
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    /**
     * Тест вывода меню выбора реализации графа.
     * Проверяет наличие всех ключевых пунктов меню в консольном выводе.
     */
    @Test
    void testPrintLetChooseRepresentation() {
        OutputController.printLetChooseRepresentation();
        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Выберите реализацию графа:"));
        assertTrue(output.contains("1 - на матрице смежности"));
        assertTrue(output.contains("2 - на списках смежности"));
        assertTrue(output.contains("3 - на матрице инцидентности"));
    }

    /**
     * Тест вывода сообщения об ошибке.
     * Проверяет, что метод выводит именно текст сообщения из переданного исключения.
     */
    @Test
    void testPrintException() {
        RuntimeException exception = new RuntimeException("Тестовая ошибка графа");
        OutputController.printException(exception);

        assertEquals("Тестовая ошибка графа", outputStreamCaptor.toString().trim());
    }

    /**
     * Тест вывода запроса имени файла.
     * Проверяет точное совпадение приглашения к вводу.
     */
    @Test
    void testPrintAscFileName() {
        OutputController.printAscFileName();
        assertEquals("Введите имя файла (с расшрением)", outputStreamCaptor.toString().trim());
    }
}
