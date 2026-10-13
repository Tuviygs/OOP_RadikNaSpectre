package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;

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
