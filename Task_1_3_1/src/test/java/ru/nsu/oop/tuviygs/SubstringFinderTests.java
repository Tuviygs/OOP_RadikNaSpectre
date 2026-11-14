package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.nsu.oop.tuviygs.core.SubstringFinder;

/**
 * Тесты класса.
 */
class SubstringFinderTests {

    /**
     * Единый файндер.
     */
    private final SubstringFinder finder = new SubstringFinder();

    /**
     * Пути.
     */
    @TempDir
    Path tempDir;

    /**
     * Тест 1.
     *
     * @throws IOException исключения.
     */
    @Test
    void testBasicAndOverlappingSubstrings() throws IOException {
        Path file = tempDir.resolve("basic.txt");
        Files.writeString(file, "абаба_тест_аба", StandardCharsets.UTF_8);

        List<Integer> result = finder.findSubstring(file.toString(), "аба");

        assertEquals(List.of(0, 2, 11), result);
    }

    /**
     * Тест 2.
     *
     * @throws IOException исключения.
     */
    @Test
    void testDifferentAlphabetsUtf8() throws IOException {
        Path file = tempDir.resolve("utf8.txt");
        Files.writeString(file, "Hello 世界! Привет 世界!", StandardCharsets.UTF_8);

        List<Integer> result = finder.findSubstring(file.toString(), "世界");

        assertEquals(List.of(6, 17), result);
    }

    /**
     * Тест 3.
     *
     * @throws IOException исключения.
     */
    @Test
    void testNotFoundAndEdgeCases() throws IOException {
        Path file = tempDir.resolve("empty_or_short.txt");
        Files.writeString(file, "abc", StandardCharsets.UTF_8);

        assertTrue(finder.findSubstring(file.toString(), "xyz").isEmpty());
        assertTrue(finder.findSubstring(file.toString(), "abcdef").isEmpty());
        assertTrue(finder.findSubstring(file.toString(), "").isEmpty());
        assertTrue(finder.findSubstring(file.toString(), null).isEmpty());
    }

    /**
     * Тест 4.
     *
     * @throws IOException исключения.
     */
    @Test
    void testLargeFileGeneration() throws IOException {
        Path largeFile = tempDir.resolve("large.txt");
        String target = "NEEDLE";
        String filler = "0123456789".repeat(100);

        try (BufferedWriter writer = Files.newBufferedWriter(largeFile, StandardCharsets.UTF_8)) {
            writer.write(target);
            for (int i = 0; i < 50_000; i++) {
                writer.write(filler);
            }
            writer.write(target);
        }

        List<Integer> result = finder.findSubstring(largeFile.toString(), target);

        assertEquals(List.of(0, 50_000_006), result);
    }

    /**
     * Тест 5 (проверка Main при успешном поиске).
     *
     * @throws IOException исключения.
     */
    @Test
    void testMainFound() throws IOException {
        Path testFile = tempDir.resolve("main_found.txt");
        Files.writeString(testFile, "привет, мир! привет!", StandardCharsets.UTF_8);

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            String simulatedInput = testFile + System.lineSeparator() + "привет" + System.lineSeparator();
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

            Main.main(new String[]{});

            String consoleOutput = outputStream.toString(StandardCharsets.UTF_8);
            assertTrue(consoleOutput.contains(""));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Тест 6 (проверка Main при отсутствии вхождений).
     *
     * @throws IOException исключения.
     */
    @Test
    void testMainNotFound() throws IOException {
        Path testFile = tempDir.resolve("main_not_found.txt");
        Files.writeString(testFile, "просто текст", StandardCharsets.UTF_8);

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            String simulatedInput = testFile + System.lineSeparator() + "нет_такого" + System.lineSeparator();
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

            Main.main(new String[]{});

            String consoleOutput = outputStream.toString(StandardCharsets.UTF_8);
            assertTrue(consoleOutput.contains("Вхождения не найдены."));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
