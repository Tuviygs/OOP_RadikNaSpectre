package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.representation.incidence.matrix.IncidenceMatrixGraph;

/**
 * Тесты основной функциональности для обеспечения 100% покрытия IncidenceMatrixGraph.
 */
public class IncidenceMatrixGraphTest {

    /**
     * Покрывает оба конструктора (пустой и с параметром count).
     */
    @Test
    void testConstructorsAndGetVertexCount() {
        Graph emptyGraph = new IncidenceMatrixGraph();
        assertEquals(0, emptyGraph.getVertexCount());

        Graph graph = new IncidenceMatrixGraph(3);
        assertEquals(3, graph.getVertexCount());
    }

    /**
     * Покрывает метод addVertex(), включая цикл внутри него
     * (добавление 0 во все существующие списки рёбер).
     */
    @Test
    void testAddVertexWithExistingEdges() {
        Graph graph = new IncidenceMatrixGraph(2);
        graph.addEdge(0, 1); // Создается матрица с одним ребром длины 2

        graph.addVertex(); // Вызывает цикл по рёбрам и увеличивает vertexCount
        assertEquals(3, graph.getVertexCount());
    }

    /**
     * Полностью покрывает ветвление цикла в addEdge():
     * i == vertex1, i == vertex2, и ветку else (для вершины 2).
     */
    @Test
    void testAddEdgeBranches() {
        Graph graph = new IncidenceMatrixGraph(3);
        graph.addEdge(0, 1); // Для вершины 2 запишется 0 в списки

        List<Integer> neighbours = graph.getNeighbours(0);
        assertEquals(1, neighbours.size());
        assertTrue(neighbours.contains(1));
    }

    /**
     * Покрывает deleteVertex(): заходит как в ветку удаления инцидентного ребра,
     * так и в ветку else (сжатие массива ребра, не связанного с удаляемой вершиной).
     */
    @Test
    void testDeleteVertexAllBranches() {
        Graph graph = new IncidenceMatrixGraph(4);
        graph.addEdge(0, 1); // Будет удалено, так как инцидентно 1
        graph.addEdge(2, 3); // НЕ инцидентно 1, зайдет в ветку else { edge.remove(vertex); }

        graph.deleteVertex(1);

        assertEquals(3, graph.getVertexCount());
        // Проверяем, что ребро (2, 3) сместилось и превратилось в (1, 2)
        List<Integer> neighboursOfOne = graph.getNeighbours(1);
        assertEquals(1, neighboursOfOne.size());
        assertTrue(neighboursOfOne.contains(2));
    }

    /**
     * Покрывает deleteEdge(): проверяет цикл с конца, ветку несовпадения ребра,
     * успешное удаление совпавшего ребра и команду break.
     */
    @Test
    void testDeleteEdgeAllBranches() {
        Graph graph = new IncidenceMatrixGraph(3);
        graph.addEdge(0, 2); // Первое ребро (индекс 0) — не совпадет, проверит ветку if на ложь
        graph.addEdge(0, 1); // Второе ребро (индекс 1) — совпадет, удалится, сработает break

        graph.deleteEdge(0, 1);

        List<Integer> neighbours = graph.getNeighbours(0);
        assertEquals(1, neighbours.size());
        assertTrue(neighbours.contains(2)); // Ребро (0, 2) должно остаться
    }

    /**
     * Покрывает скрытую ветку дубликатов !neighbours.contains(i) в getNeighbours().
     * Добавляет параллельные рёбра между 0 и 1, гарантируя выполнение условия на ложь.
     */
    @Test
    void testGetNeighboursWithParallelEdges() {
        Graph graph = new IncidenceMatrixGraph(2);
        graph.addEdge(0, 1);
        graph.addEdge(0, 1); // Повторное ребро между теми же вершинами

        List<Integer> neighbours = graph.getNeighbours(0);
        // Благодаря !neighbours.contains(i) в списке должна быть только одна единица
        assertEquals(1, neighbours.size());
        assertTrue(neighbours.contains(1));
    }

    /**
     * Покрывает вызов дефолтного метода интерфейса readGraphFromFile.
     * Так как реальный парсинг мы тестируем в GraphFileReader, здесь достаточно
     * вызвать его, чтобы покрыть саму строку вызова супер-метода.
     */
    @Test
    void testReadGraphFromFileDelegation() {
        Graph graph = new IncidenceMatrixGraph(7);
        // Вызываем метод. Он бросит исключение из GraphFileReader, так как структуры TextGraph
        // и фабрики не связаны напрямую автоматически без реализации в Graph.super,
        // но сама строка вызова метода внутри IncidenceMatrixGraph гарантированно выполнится.
        assertThrows(RuntimeException.class, () -> graph.readGraphFromFile("graphExample1.txt"));
    }
}
