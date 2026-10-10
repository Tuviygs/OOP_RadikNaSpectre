package ru.nsu.oop.tuviygs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * топологическая сортировка графа.
 */
public class GraphTopasort {

    /**
     * топологическая сортировка графа на матрице смежности.
     *
     * @param graph - сортируемый граф
     */
    public List<Integer> topasort(Graph graph) {
        List<Integer> sortedVertex = new ArrayList<>();

        List<Integer> inDegree = getInDegree(graph);
        List<Integer> vertexToProcess = getVertexToProcess(graph, inDegree);

        while (!vertexToProcess.isEmpty()) {
            int currentVertex = vertexToProcess.getFirst();
            vertexToProcess.removeFirst();
            List<Integer> neighbours = graph.getNeighbours(currentVertex);
            for (int neighbour : neighbours) {
                inDegree.set(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0) {
                    vertexToProcess.add(neighbour);
                }
            }
            sortedVertex.add(currentVertex);
            vertexToProcess.sort(Comparator.naturalOrder());
        }

        if (sortedVertex.size() < graph.getVertexCount()) {
            throw new GraphException("Граф не ацикличен.");
        }
        return sortedVertex;
    }

    /**
     * получение списка со степенями захода у вершин.
     *
     * @param graph - исходный граф
     */
    private List<Integer> getInDegree(Graph graph) {
        int vertexCount = graph.getVertexCount();
        List<Integer> inDegree = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            inDegree.add(0);
        }
        for (int i = 0; i < vertexCount; i++) {
            for (int neighbour : graph.getNeighbours(i)) {
                inDegree.set(neighbour, inDegree.get(neighbour) + 1);
            }
        }
        return inDegree;
    }

    /**
     * создание списка с вершинами для начала обхода.
     *
     * @param graph - исходный граф
     * @param inDegree - список степеней захода вершин
     */
    private List<Integer> getVertexToProcess(Graph graph, List<Integer> inDegree) {
        List<Integer> vertexToProcess = new ArrayList<Integer>();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (inDegree.get(i) == 0) {
                vertexToProcess.add(i);
            }
        }
        return vertexToProcess;
    }
}
