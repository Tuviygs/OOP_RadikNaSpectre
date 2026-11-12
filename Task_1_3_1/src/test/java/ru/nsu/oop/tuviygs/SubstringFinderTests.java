package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.nsu.oop.tuviygs.core.SubstringFinder;

/**
 * тесты класса.
 */
class SubstringFinderTests {

    /**
     * единый файндер.
     */
    private final SubstringFinder finder = new SubstringFinder();

    /**
     * пути.
     */
    @TempDir
    Path tempDir;

    /**
     * тест1.
     *
     * @throws IOException исключения.
     */
    @Test
    void testBasicAndOverlappingSubstrings() throws IOException {
        Path file = tempDir.resolve("basic.txt");
        // Проверяем вхождения в начале, середине, конце и с пересечением ("аба" в "абаба")
        Files.writeString(file, "абаба_тест_аба", StandardCharsets.UTF_8);

        List<Integer> result = finder.findSubstring(file.toString(), "аба");

        assertEquals(List.of(0, 2, 11), result);
    }

    /**
     * тест2.
     *
     * @throws IOException иселючения.
     */
    @Test
    void testDifferentAlphabetsUtf8() throws IOException {
        Path file = tempDir.resolve("utf8.txt");
        Files.writeString(file, "Hello 世界! Привет 世界!", StandardCharsets.UTF_8);

        List<Integer> result = finder.findSubstring(file.toString(), "世界");

        assertEquals(List.of(6, 17), result);
    }

    /**
     * тест3.
     *
     * @throws IOException иселючения.
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
     * тест 4.
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
}

