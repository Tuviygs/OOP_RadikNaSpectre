package ru.nsu.oop.tuviygs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * тесты ошибок.
 */
public class GraphExceptionTest {

    /**
     * тест добавления ребра к несуществующей вершине.
     */
    @Test
    void addEdgeToInvalidVertexThrows() {
        Graph graph = new AdjacencyMatrixGraph(3);
        assertThrows(GraphException.class,
                () -> graph.addEdge(0, 5));
    }

}
