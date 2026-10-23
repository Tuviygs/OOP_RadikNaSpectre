package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.lists.AdjacencyListsGraph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.representation.incidence.matrix.IncidenceMatrixGraph;
import ru.nsu.oop.tuviygs.stuff.Stuff;

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


    /**
     * Тест выброса исключения при попытке удалить вершину с некорректным номером.
     * Проверяется выход за границы текущего количества вершин графа.
     */
    @Test
    void deleteVertexInvalidIndexThrows() {
        Graph graph = new AdjacencyListsGraph(2);
        assertThrows(GraphException.class,
            () -> graph.deleteVertex(2));
        assertThrows(GraphException.class,
            () -> graph.deleteVertex(-1));
    }

    /**
     * Тест выброса исключения при попытке удаления несуществующего ребра
     * или ребра между несуществующими вершинами.
     */
    @Test
    void deleteEdgeInvalidVertexThrows() {
        Graph graph = new AdjacencyListsGraph(2);
        assertThrows(GraphException.class,
            () -> graph.deleteEdge(0, 5));
    }

    /**
     * Тест выброса исключения при запросе списка смежности (соседей)
     * для вершины, которой нет в графе.
     */
    @Test
    void getNeighboursInvalidVertexThrows() {
        Graph graph = new AdjacencyListsGraph(1);
        assertThrows(GraphException.class,
            () -> graph.getNeighbours(1));
    }

    /**
     * Тест выброса исключения при вводе нечисловой строки.
     * Проверяет, что NumberFormatException перехватывается и выбрасывается GraphException.
     */
    @Test
    void setMatrixRepresentationInvalidNumberFormatThrows() {
        assertThrows(GraphException.class,
            () -> Stuff.setMatrixRepresentation("abc"));
        assertThrows(GraphException.class,
            () -> Stuff.setMatrixRepresentation(""));
    }

    /**
     * Тест выброса исключения при выборе несуществующего пункта меню.
     * Ожидается GraphException, так как число выходит за рамки доступных реализаций.
     */
    @Test
    void setMatrixRepresentationUnknownOptionThrows() {
        assertThrows(GraphException.class,
            () -> Stuff.setMatrixRepresentation("4"));
        assertThrows(GraphException.class,
            () -> Stuff.setMatrixRepresentation("0"));
        assertThrows(GraphException.class,
            () -> Stuff.setMatrixRepresentation("-1"));
    }


    @Test
    void addEdgeInvalidVertex1Throws() {
        Graph graph = new IncidenceMatrixGraph(2);
        assertThrows(GraphException.class, () -> graph.addEdge(-1, 1));
        assertThrows(GraphException.class, () -> graph.addEdge(2, 1));
    }

    /**
     * Покрывает проверку на петли (vertex1 == vertex2).
     */
    @Test
    void addEdgeLoopThrows() {
        Graph graph = new IncidenceMatrixGraph(2);
        assertThrows(GraphException.class, () -> graph.addEdge(1, 1));
    }

    /**
     * Покрывает ветки валидации vertex2 при добавлении ребра.
     */
    @Test
    void addEdgeInvalidVertex2Throws() {
        Graph graph = new IncidenceMatrixGraph(2);
        assertThrows(GraphException.class, () -> graph.addEdge(0, -1));
        assertThrows(GraphException.class, () -> graph.addEdge(0, 2));
    }


    /**
     * Покрывает валидацию индексов при удалении ребра.
     */
    @Test
    void deleteEdgeInvalidVerticesThrows() {
        Graph graph = new IncidenceMatrixGraph(2);
        assertThrows(GraphException.class, () -> graph.deleteEdge(-1, 1));
        assertThrows(GraphException.class, () -> graph.deleteEdge(2, 1));
        assertThrows(GraphException.class, () -> graph.deleteEdge(0, -1));
        assertThrows(GraphException.class, () -> graph.deleteEdge(0, 2));
    }

}
