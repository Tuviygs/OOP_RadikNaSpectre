package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.lists.AdjacencyListsGraph;

/**
 * Класс для тестирования корректного поведения графа на списках смежности.
 * Проверяет основную логику добавления/удаления элементов и целостность структуры.
 */
public class AdjacencyListsGraphTest {

    /**
     * Тест работы конструкторов графа и корректности инициализации
     * начального счётчика вершин.
     */
    @Test
    void testConstructorAndVertexCount() {
        Graph emptyGraph = new AdjacencyListsGraph();
        assertEquals(0, emptyGraph.getVertexCount());

        Graph graphWithVertices = new AdjacencyListsGraph(5);
        assertEquals(5, graphWithVertices.getVertexCount());
    }

    /**
     * Тест динамического добавления новых вершин в граф
     * и изменения общего счётчика вершин.
     */
    @Test
    void testAddVertex() {
        Graph graph = new AdjacencyListsGraph();
        graph.addVertex();
        assertEquals(1, graph.getVertexCount());

        graph.addVertex();
        assertEquals(2, graph.getVertexCount());
    }

    /**
     * Тест успешного добавления направленных рёбер и получения списка
     * смежных вершин для конкретного узла.
     */
    @Test
    void testAddAndGetEdges() {
        Graph graph = new AdjacencyListsGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        List<Integer> neighbours = graph.getNeighbours(0);
        assertEquals(2, neighbours.size());
        assertTrue(neighbours.contains(1));
        assertTrue(neighbours.contains(2));

        assertTrue(graph.getNeighbours(1).isEmpty());
    }

    /**
     * Тест удаления существующего ребра между двумя вершинами графа.
     */
    @Test
    void testDeleteEdge() {
        Graph graph = new AdjacencyListsGraph(2);
        graph.addEdge(0, 1);
        assertEquals(1, graph.getNeighbours(0).size());

        graph.deleteEdge(0, 1);
        assertTrue(graph.getNeighbours(0).isEmpty());
    }

    /**
     * Тест удаления вершины из графа. Проверяет корректность внутренней
     * логики списков смежности: смещение индексов оставшихся вершин,
     * пересчёт их внутренних номеров и очистку старых связей.
     */
    @Test
    void testDeleteVertexAndIndexShifting() {
        Graph graph = new AdjacencyListsGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);

        graph.deleteVertex(1);

        assertEquals(2, graph.getVertexCount());

        List<Integer> neighboursOfZero = graph.getNeighbours(0);
        assertEquals(1, neighboursOfZero.size());
        assertTrue(neighboursOfZero.contains(1));
    }
}
