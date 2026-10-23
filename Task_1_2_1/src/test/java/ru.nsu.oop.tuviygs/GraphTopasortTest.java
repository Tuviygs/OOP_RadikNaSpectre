package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.stuff.GraphTopasort;

/**
 * Тесты корректной работы топологической сортировки.
 * Проверяет правильность порядка вершин для различных ациклических графов (DAG).
 */
public class GraphTopasortTest {

    /**
     * Тест топологической сортировки на примере твоего graphExample1.txt.
     * Структура:
     * 7 вершин, рёбра: 0->1, 0->2, 1->3, 2->3, 2->4, 3->5, 4->5, 5->6.
     * Из-за vertexToProcess.sort(Comparator.naturalOrder()) порядок обработки строго детерминирован.
     */
    @Test
    void testTopasortWithGraphExample1() {
        Graph graph = new AdjacencyMatrixGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        List<Integer> result = GraphTopasort.topasort(graph);

        // Проверяем, что все 7 вершин отсортированы
        assertEquals(7, result.size());

        // Проверяем каноничные правила топосорта (индекс источника должен быть раньше стока)
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(0) < result.indexOf(2));
        assertTrue(result.indexOf(1) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(4));
        assertTrue(result.indexOf(3) < result.indexOf(5));
        assertTrue(result.indexOf(4) < result.indexOf(5));
        assertTrue(result.indexOf(5) < result.indexOf(6));

        // С учётом сортировки Comparator.naturalOrder() в очереди vertexToProcess,
        // точный ожидаемый результат выполнения алгоритма Кана: [0, 1, 2, 3, 4, 5, 6]
        List<Integer> expected = List.of(0, 1, 2, 3, 4, 5, 6);
        assertEquals(expected, result);
    }

    /**
     * Тест топологической сортировки пустого графа без вершин.
     */
    @Test
    void testTopasortEmptyGraph() {
        Graph graph = new AdjacencyMatrixGraph(0);
        List<Integer> result = GraphTopasort.topasort(graph);
        assertTrue(result.isEmpty());
    }

    /**
     * Тест топологической сортировки графа, состоящего только из изолированных вершин.
     * Поскольку у всех степень захода 0, а очередь сортируется по naturalOrder,
     * вершины должны выйти строго по порядку своих индексов.
     */
    @Test
    void testTopasortIsolatedVertices() {
        Graph graph = new AdjacencyMatrixGraph(4); // Вершины 0, 1, 2, 3 без рёбер

        List<Integer> result = GraphTopasort.topasort(graph);

        assertEquals(4, result.size());
        assertEquals(List.of(0, 1, 2, 3), result);
    }

    /**
     * Тест топологической сортировки линейного графа (пути).
     */
    @Test
    void testTopasortLinearGraph() {
        Graph graph = new AdjacencyMatrixGraph(4);
        graph.addEdge(3, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 0);

        List<Integer> result = GraphTopasort.topasort(graph);

        assertEquals(4, result.size());
        assertEquals(List.of(3, 2, 1, 0), result);
    }
}
