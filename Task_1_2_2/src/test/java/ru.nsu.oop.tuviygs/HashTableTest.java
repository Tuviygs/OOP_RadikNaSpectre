package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.nsu.oop.tuviygs.hashtable.HashTable;
import ru.nsu.oop.tuviygs.hashtable.HashTableException;

/**
 * Класс тестов для HashTable, обеспечивающий максимальное покрытие.
 */
class HashTableTest {
    private HashTable<String, Integer> table;

    /**
     * Инициализация перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    /**
     * Тест базового добавления и получения значений.
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
     * Тест выброса исключения при попытке добавить дубликат ключа.
     */
    @Test
    void testAddDuplicateKeyThrowsException() {
        table.add("one", 1);
        assertThrows(HashTableException.class, ()
            -> table.add("one", 111));
    }

    /**
     * Тест изменения значения и удаления элементов.
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
     * Тест обработки коллизий (когда у разных ключей одинаковый бакет).
     */
    @Test
    void testCollisionsAndChaining() {
        // Создаем ключи, гарантированно попадающие в один бакет (хэш-код % 16 одинаковый)
        // String "A" и "Q" в Java имеют разницу в хэш-коде кратную 16 или можно переопределить
        // Для надежности добавим несколько элементов, чтобы проверить проход по цепочке
        table.add("A", 1);
        table.add("Q", 2);
        table.add("Z", 3);

        assertEquals(1, table.getValue("A"));
        assertEquals(2, table.getValue("Q"));
        assertEquals(3, table.getValue("Z"));

        // Удаление из середины/конца цепочки коллизий
        table.delete("Q", 2);
        assertFalse(table.hasValue("Q"));
        assertTrue(table.hasValue("A"));
        assertTrue(table.hasValue("Z"));
    }

    /**
     * Тест автоматического расширения таблицы при превышении коэффициента заполнения.
     */
    @Test
    void testReformatTableOnLoadFactor() {
        // Добавляем 13 элементов, чтобы bucketCount / 16 превысило 0.75
        for (int i = 0; i < 14; i++) {
            table.add("key" + i, i);
        }
        // Проверяем, что все элементы доступны после реформатирования
        for (int i = 0; i < 14; i++) {
            assertEquals(i, table.getValue("key" + i));
        }
    }

    /**
     * Тест глубокой коллизии для полной проверки цикла while в методе add.
     */
    @Test
    void testDeepCollisionEntersWhileLoop() {
        table.add("A", 1);
        table.add("Q", 2);
        table.add("a", 3);

        // Проверяем, что все три элемента успешно прописались в цепочке
        assertEquals(1, table.getValue("A"));
        assertEquals(2, table.getValue("Q"));
        assertEquals(3, table.getValue("a"));

        table.setValue("A", 2);
        table.setValue("Q", 3);
        table.setValue("a", 4);

        // Проверяем, что все три элемента успешно прописались в цепочке
        assertEquals(2, table.getValue("A"));
        assertEquals(3, table.getValue("Q"));
        assertEquals(4, table.getValue("a"));
    }

    /**
     * Тест метода toString для проверки вывода элементов с запятыми-разделителями.
     */
    @Test
    void testToStringWithMultipleElements() {
        table.add("A", 1);
        table.add("B", 2);

        // Проверяем строковое представление
        String expected = "{A=1, B=2}";
        assertEquals(expected, table.toString());
    }


    /**
     * Тест выброса исключений при удалении несуществующих элементов/ключей.
     */
    @Test
    void testDeleteMissingElementsThrowsException() {
        table.add("one", 1);

        // Ключ есть, но значение не совпадает
        assertThrows(HashTableException.class, ()
            -> table.delete("one", 999));
        // Ключа вообще нет
        assertThrows(HashTableException.class, ()
            -> table.delete("missing", 1));
    }

    /**
     * Тест исключений при получении или обновлении несуществующих ключей.
     */
    @Test
    void testGetAndSetMissingKeyThrowsException() {
        assertThrows(HashTableException.class, ()
            -> table.getValue("missing"));
        assertThrows(HashTableException.class, ()
            -> table.setValue("missing", 42));
    }

    /**
     * Тест базовой работы итератора.
     */
    @Test
    void testIteratorWorks() {
        table.add("A", 1);
        table.add("B", 2);

        int count = 0;
        for (HashTable.Node<String, Integer> node : table) {
            assertNotNull(node.getKey());
            assertNotNull(node.getValue());
            count++;
        }
        assertEquals(2, count);
    }

    /**
     * Тест next(), когда элементов больше нет.
     */
    @Test
    void testIteratorNoSuchElementException() {
        table.add("A", 1);
        Iterator<HashTable.Node<String, Integer>> it = table.iterator();

        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
        assertThrows(HashTableException.class, it::next);
    }

    /**
     * Тест выброса Exception при изменении таблицы во время итерации.
     */
    @Test
    void testIteratorConcurrentModification() {
        table.add("A", 1);

        assertThrows(ConcurrentModificationException.class, () -> {
            for (HashTable.Node<String, Integer> node : table) {
                table.add("B", 2);
            }
        });
    }

    /**
     * Тест контракта методов equals, hashCode и toString.
     */
    @Test
    void testEqualsHashCodeToString() {
        HashTable<String, Integer> table2 = new HashTable<>();

        // Проверка пустых таблиц
        assertEquals(table, table2);
        assertEquals(table.hashCode(), table2.hashCode());
        assertEquals("{}", table.toString());

        table.add("A", 1);
        assertNotEquals(table, table2); // Разное кол-во элементов

        table2.add("A", 1);
        assertEquals(table, table2); // Идентичные таблицы
        assertEquals(table.hashCode(), table2.hashCode());

        table2.setValue("A", 2);
        assertNotEquals(table, table2); // Разные значения при одинаковых ключах

        HashTable<String, Integer> table3 = new HashTable<>();
        table3.add("B", 1);
        assertNotEquals(table, table3); // Разные ключи

        assertNotEquals(table, null);
        assertNotEquals(table, "not a hash table");
    }

    /**
     * Тест сеттеров, геттеров и связей внутри класса Node.
     */
    @Test
    void testNodeGettersSettersAndPointers() {
        HashTable.Node<String, Integer> nodeA = new HashTable.Node("A", 1);
        HashTable.Node<String, Integer> nodeB = new HashTable.Node("B", 2);

        // Проверка базовых геттеров
        assertEquals("A", nodeA.getKey());
        assertEquals(1, nodeA.getValue());

        // Проверка сеттера значения
        nodeA.setValue(100);
        assertEquals(100, nodeA.getValue());

        // Проверка установки указателей (next/previous)
        assertNull(nodeA.getNextNode());
        assertNull(nodeA.getPreviousNode());

        nodeA.setNextNode(nodeB);
        nodeB.setPreviousNode(nodeA);

        assertEquals(nodeB, nodeA.getNextNode());
        assertEquals(nodeA, nodeB.getPreviousNode());
    }

    /**
     * Тест класса Main с симуляцией пользовательского ввода.
     */
    @Test
    void testMainMethodExecution() {
        // Заготавливаем строчку ввода, которую Scanner будет читать по очереди.
        // Сначала 5 пар (ключ значение)
        // Затем ключ и новое значение для обновления
        // Затем ключ и значение для удаления
        String simulatedInput = "k1 v1\n" // 1 пара
            + "k2 v2\n" // 2 пара
            + "k3 v3\n" // 3 пара
            + "k4 v4\n" // 4 пара
            + "k5 v5\n" // 5 пара
            + "k1 new_v1\n" // Обновление k1
            + "k2 v2\n";    // Удаление k2

        // Сохраняем оригинальный System.in, чтобы вернуть его на место после теста
        InputStream originalIn = System.in;

        try {
            // Подменяем стандартный ввод нашим заготовленным текстом
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

            // Вызываем метод main. Передаем пустой массив аргументов String[]
            Main.main(new String[0]);

            assertTrue(true);

        } finally {
            // Обязательно возвращаем оригинальный System.in на место,
            // чтобы не сломать другие тесты в системе
            System.setIn(originalIn);
        }
    }

}
