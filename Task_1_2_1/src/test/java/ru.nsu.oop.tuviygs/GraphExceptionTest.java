package ru.nsu.oop.tuviygs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ru.nsu.oop.tuviygs.adjacencyMatrixGraph.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graphExceptions.GraphException;
import ru.nsu.oop.tuviygs.graphInterface.Graph;



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
