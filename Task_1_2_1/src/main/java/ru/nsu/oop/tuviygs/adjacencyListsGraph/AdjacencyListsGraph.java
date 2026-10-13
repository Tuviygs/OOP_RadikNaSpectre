package ru.nsu.oop.tuviygs.adjacencyListsGraph;

import ru.nsu.oop.tuviygs.graphExceptions.GraphException;

import java.util.ArrayList;
import java.util.List;

/**
 * класс для представления графа
 * как списков смежности
 */
public class AdjacencyListsGraph implements ru.nsu.oop.tuviygs.Graph {


    /**
     * списки смежности графа.
     * элемент - список
     * элементы этого списка показывают,
     * что вершина является началом ребра,
     * входящего в вершину с номером, равным элементу
     */
    private List<Vertex> lists;

    /**
     * количество вершин в графе.
     */
    private int vertexCount;

    /**
     * вывод количества вершин в графе.
     */
    @Override
    public int getVertexCount() {
        return this.vertexCount;
    }

    /**
     * создание пустого графа.
     */
    public AdjacencyListsGraph() {
        this.lists = new ArrayList<Vertex>();
        this.vertexCount = 0;
    }

    /**
     * создание графа с заданным количеством вершин.
     *
     * @param count - количество вершин
     */
    public AdjacencyListsGraph(int count) {
        this.lists = new ArrayList<Vertex>(count);
        this.vertexCount = 0;
        for (int i = 0; i < count; i++) {
            this.addVertex();
        }
    }

    /**
     * добавление вершины.
     */
    @Override
    public void addVertex() {
        this.lists.add(new Vertex(this.vertexCount));
        this.vertexCount++;
    }

    /**
     * удаление вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public void delVertex(int vertex) {
        if (vertex < 0 || vertex >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex) + " не существует.");
        }
        for (int i = 0; i < vertexCount; i++) {
            this.lists.get(i).delVertex(vertex);
            if (i > vertex) {
                this.lists.get(i).decreaseNumber();
            }
        }
        this.lists.remove(vertex);
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
        if (vertex1 < 0 || vertex1 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex1) + " не существует.");
        }
        if (vertex2 < 0 || vertex2 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex2) + " не существует.");
        }

        this.lists.get(vertex1).addNeighbour(vertex2);

    }

    /**
     * удаление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    @Override
    public void delEdge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex1 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex1) + " не существует.");
        }
        if (vertex2 < 0 || vertex2 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex2) + " не существует.");
        }
        this.lists.get(vertex1).delNeighbour(vertex2);
    }

    /**
     * получение соседей вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public List<Integer> getNeighbours(int vertex) {
        if (vertex < 0 || vertex >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                    + Integer.toString(vertex) + " не существует.");
        }
        return this.lists.get(vertex).getNeighbours();
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
