package ru.nsu.oop.tuviygs.graph.representation.incidence.Matrix;

import java.util.List;

import ru.nsu.oop.tuviygs.graph.Graph;


/**
 * представление графа на матрице инцидентвности.
 */
public class IncidenceMatrixGraph implements Graph {


    /**
     * количество вершин.
     */
    private int vertexCount;

    /**
     * получение количества вершин в графе.
     */
    @Override
    public int getVertexCount() {
        return this.vertexCount;
    }

    /**
     * добавление вершины.
     */
    @Override
    public void addVertex() {

    }

    /**
     * удаление вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public void deleteVertex(int vertex) {

    }

    /**
     * добавление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    @Override
    public void addEdge(int vertex1, int vertex2) {

    }

    /**
     * удаление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    @Override
    public void deleteEdge(int vertex1, int vertex2) {

    }

    /**
     * получение соседей вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public List<Integer> getNeighbours(int vertex) {
        return List.of();
    }

    /**
     * чтение графа из файла.
     *
     * @param filePath - относительный путь до файла
     */
    @Override
    public void readGraphFromFile(String filePath) {
        Graph.super.readGraphFromFile(filePath);
    }
}
