package ru.nsu.oop.tuviygs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * граф на матрице смежности.
 */
public class AdjacencyMatrixGraph implements Graph {

    /**
     * матрица смежности графа.
     * элемент - список
     * элементы этого списка показывают,
     * что вершина является началом ребра,
     * входящего в вершину с номером, равным индексу
     */
    private List<List<Integer>> matrix;

    /**
     * количество вершин в графе.
     */
    private int vertexCount;

    /**
     * создание пустого графа.
     */
    public AdjacencyMatrixGraph() {
        this.matrix = new ArrayList<>();
        this.vertexCount = 0;
    }

    /**
     * создание графа с заданным количеством вершин.
     *
     * @param count - количество вершин
     */
    public AdjacencyMatrixGraph(int count) {
        this.matrix = new ArrayList<>(count);
        this.vertexCount = 0;
        for (int i = 0; i < count; i++) {
            this.addVertex();
        }
    }

    /**
     * получение матрицы извне без возможности её изменения.
     */
    public List<List<Integer>> getMatrix() {
        List<List<Integer>> protectedMatrix = new ArrayList<>();
        for (List<Integer> row : this.matrix) {
            protectedMatrix.add(Collections.unmodifiableList(row));
        }
        return Collections.unmodifiableList(protectedMatrix);
    }

    /**
     * получение количества вершин в графе.
     */
    public int getVertexCount() {
        return this.vertexCount;
    }

    /**
     * добавление вершины.
     */
    @Override
    public void addVertex() {
        this.matrix.forEach(list -> list.add(0));
        int newSize = this.matrix.size() + 1;
        List<Integer> newVertex = new ArrayList<Integer>(newSize);
        for (int i = 0; i < newSize; i++) {
            newVertex.add(0);
        }
        this.matrix.add(newVertex);
        this.vertexCount++;
    }

    /**
     * удаление вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public void delVertex(int vertex) {
        if (vertex < 0 || vertex >= this.matrix.size()) {
            throw new GraphException("Вершины с номером "
                + Integer.toString(vertex) + " не существует.");
        }
        this.matrix.forEach(list -> list.remove(vertex));
        this.matrix.remove(vertex);
        this.vertexCount--;
    }

    /**
     * добавление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    @Override
    public void addEdge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex1 >= this.matrix.size()  ) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex1) + " не существует.");
        }
        if (vertex2 < 0 || vertex2 >= this.matrix.size()) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex2) + " не существует.");
        }
        this.matrix.get(vertex1).set(vertex2, 1);
    }

    /**
     * удаление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    @Override
    public void delEdge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex1 >= this.matrix.size()) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex1) + " не существует.");
        }
        if (vertex2 < 0 || vertex2 >= this.matrix.size()) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex2) + " не существует.");
        }
        this.matrix.get(vertex1).set(vertex2, 0);

    }

    /**
     * получение соседей вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public List<Integer> getNeighbours(int vertex) {
        if (vertex < 0 || vertex >= this.matrix.size()) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex) + " не существует.");
        }
        List<Integer> neighbours = new ArrayList<Integer>();
        List<Integer> thisVertex = this.matrix.get(vertex);
        for (int i = 0; i < thisVertex.size(); i++) {
            if (thisVertex.get(i) == 1) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    /**
     * чтение графа из файла.
     *
     * @param fileName - имя файла
     */
    @Override
    public void readGraphFromFile(String fileName) {

    }
}
