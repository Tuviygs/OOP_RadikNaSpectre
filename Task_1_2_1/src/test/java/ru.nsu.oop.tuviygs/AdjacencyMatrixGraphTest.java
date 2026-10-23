package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;

/**
 * Тесты корректной работы графа на матрице смежности.
 * Проверяет инициализацию структуры, добавление/удаление компонентов и смещение матрицы.
 */
public class AdjacencyMatrixGraphTest {

    /**
     * Тест конструкторов и корректности инициализации размера матрицы смежности.
     */
    @Test
    void testConstructorAndVertexCount() {
        Graph emptyGraph = new AdjacencyMatrixGraph();
        assertEquals(0, emptyGraph.getVertexCount());

        Graph graphWithVertices = new AdjacencyMatrixGraph(4);
        assertEquals(4, graphWithVertices.getVertexCount());
    }

    /**
     * Тест динамического добавления вершины.
     * Проверяет увеличение счётчика и корректность расширения размерности матрицы.
     */
    @Test
    void testAddVertex() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addVertex();
        assertEquals(1, graph.getVertexCount());

        graph.addVertex();
        assertEquals(2, graph.getVertexCount());

        // Рёбер нет, списки соседей должны быть пустыми
        assertTrue(graph.getNeighbours(0).isEmpty());
        assertTrue(graph.getNeighbours(1).isEmpty());
    }

    /**
     * Тест добавления рёбер и корректности возвращаемых списков смежности.
     */
    @Test
    void testAddAndGetEdges() {
        Graph graph = new AdjacencyMatrixGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        List<Integer> neighbours = graph.getNeighbours(0);
        assertEquals(2, neighbours.size());
        assertTrue(neighbours.contains(1));
        assertTrue(neighbours.contains(2));

        // Граф ориентированный, обратной связи (1 -> 0) быть не должно
        assertTrue(graph.getNeighbours(1).isEmpty());
    }

    /**
     * Тест удаления существующего ребра.
     * Проверяет, что ячейка матрицы корректно сбрасывается в 0.
     */
    @Test
    void testDeleteEdge() {
        Graph graph = new AdjacencyMatrixGraph(2);
        graph.addEdge(0, 1);
        assertEquals(1, graph.getNeighbours(0).size());

        graph.deleteEdge(0, 1);
        assertTrue(graph.getNeighbours(0).isEmpty());
    }

    /**
     * Тест удаления вершины и последующего сжатия матрицы смежности.
     * При удалении вершины строки и столбцы смещаются, индексы оставшихся вершин уменьшаются.
     */
    @Test
    void testDeleteVertexAndMatrixShifting() {
        Graph graph = new AdjacencyMatrixGraph(3); // Вершины: 0, 1, 2
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);

        // Удаляем среднюю вершину (индекс 1)
        graph.deleteVertex(1);

        // Вершин должно остаться 2 (бывшие 0 и 2)
        assertEquals(2, graph.getVertexCount());

        // Бывшая вершина 2 теперь имеет индекс 1.
        // Ребро (0 -> 2) должно превратиться в ребро (0 -> 1)
        List<Integer> neighboursOfZero = graph.getNeighbours(0);
        assertEquals(1, neighboursOfZero.size());
        assertTrue(neighboursOfZero.contains(1));
    }
}
