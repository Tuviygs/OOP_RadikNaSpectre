package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.representation.text.TextGraph;
import ru.nsu.oop.tuviygs.graph.stuff.GraphFileReader;
import ru.nsu.oop.tuviygs.graph.stuff.Edge;

/**
 * Тесты корректной работы утилиты чтения графа из файла.
 * Проверяет успешный парсинг структуры TextGraph при валидных входных данных.
 */
public class GraphFileReaderTest {

    /**
     * Тест успешного парсинга тестового файла графа graphExample1.txt.
     * Проверяет точное совпадение количества вершин и всех считанных рёбер.
     */
    @Test
    void testReadGraphExample1() {
        // Считываем граф из файла ресурсов
        TextGraph graph = GraphFileReader.readGraphFromFile("graphExample1.txt");

        // Проверяем, что объект успешно создался
        assertNotNull(graph);

        // Проверяем количество вершин (первая строка файла равна 7)
        assertEquals(7, graph.vertexCount());

        // Проверяем, что считались все 8 рёбер
        assertNotNull(graph.edges());
        assertEquals(8, graph.edges().size());

        // Проверяем точечно несколько рёбер (предполагая стандартные геттеры или поля у Edge)
        // Если в твоем классе Edge используются рекорды или другие названия, замени .from()/.to()
        Edge firstEdge = graph.edges().get(0);
        assertEquals(0, firstEdge.from());
        assertEquals(1, firstEdge.to());

        Edge lastEdge = graph.edges().get(7);
        assertEquals(5, lastEdge.from());
        assertEquals(6, lastEdge.to());
    }
}
