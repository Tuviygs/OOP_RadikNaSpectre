package ru.nsu.oop.tuviygs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * тесты.
 */
class HashTableTest {
    private HashTable<String, Integer> table;

    /**
     * передб каждым.
     */
    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    /**
     * тест добавления.
     */
    @Test
    void testAddAndGetValue() {
        table.add("one", 1);
        table.add("two", 2);

        assertTrue(table.hasValue("one"));
        assertTrue(table.hasValue("two"));
        assertEquals(1, table.getValue("one"));
        assertEquals(2, table.getValue("two"));
    }

    /**
     * дест добавления дубликата.
     */
    @Test
    void testAddDuplicateKeyThrowsException() {
        table.add("one", 1);
        assertThrows(HashTableException.class, () -> table.add("one", 111));
    }

    /**
     * тест удаления и установки значения.
     */
    @Test
    void testDeleteAndSetValue() {
        table.add("one", 1);
        table.setValue("one", 10);
        assertEquals(10, table.getValue("one"));

        table.delete("one", 10);
        assertFalse(table.hasValue("one"));
    }

    /**
     * тест работы итератора.
     */
    @Test
    void testIteratorWorks() {
        table.add("A", 1);
        table.add("B", 2);

        int count = 0;
        for (Node<String, Integer> node : table) {
            assertNotNull(node.getKey());
            assertNotNull(node.getValue());
            count++;
        }
        assertEquals(2, count);
    }

    /**
     * тест ошибки итератора.
     */
    @Test
    void testIteratorConcurrentModification() {
        table.add("A", 1);

        assertThrows(ConcurrentModificationException.class, () -> {
            for (Node<String, Integer> node : table) {
                table.add("B", 2); // Изменение структуры во время итерации
            }
        });
    }
}
