package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.lists.AdjacencyListsGraph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.representation.incidence.matrix.IncidenceMatrixGraph;
import ru.nsu.oop.tuviygs.stuff.Stuff;

/**
 * Тесты корректной работы методов фабрики класса Stuff.
 * Проверяет успешную инициализацию конкретных типов графов на основе пользовательского выбора.
 */
public class StuffTest {

    /**
     * Тест успешного создания графа на матрице смежности (вариант 1).
     * Проверяет поддержку пробельных символов вокруг числа.
     */
    @Test
    void testSetAdjacencyMatrixGraph() {
        Graph graph = Stuff.setMatrixRepresentation(" 1 \n");
        assertNotNull(graph);
        assertTrue(graph instanceof AdjacencyMatrixGraph);
    }

    /**
     * Тест успешного создания графа на списках смежности (вариант 2).
     */
    @Test
    void testSetAdjacencyListsGraph() {
        Graph graph = Stuff.setMatrixRepresentation("2");
        assertNotNull(graph);
        assertTrue(graph instanceof AdjacencyListsGraph);
    }

    /**
     * Тест успешного создания графа на матрице инцидентности (вариант 3).
     */
    @Test
    void testSetIncidenceMatrixGraph() {
        Graph graph = Stuff.setMatrixRepresentation("3");
        assertNotNull(graph);
        assertTrue(graph instanceof IncidenceMatrixGraph);
    }
}
