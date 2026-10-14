package ru.nsu.oop.tuviygs.graph.representation.incidence.matrix;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;


/**
 * представление графа на матрице инцидентности.
 */
public class IncidenceMatrixGraph implements Graph {


    /**
     * количество вершин.
     */
    private int vertexCount;

    /**
     * матрица инцидентности.
     * Один список - одно ребро
     */
    private List<List<Integer>> matrix;


    /**
     * создание пустого графа.
     */
    public IncidenceMatrixGraph() {
        this.matrix = new ArrayList<>();
        this.vertexCount = 0;
    }

    /**
     * создание графа с заданным количеством вершин.
     *
     * @param count количество вершин
     */
    public IncidenceMatrixGraph(int count) {
        this.matrix = new ArrayList<>(count);
        this.vertexCount = 0;
        for (int i = 0; i < count; i++) {
            this.addVertex();
        }
    }


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
        this.vertexCount++;
        for (List<Integer> edge : this.matrix) {
            edge.add(0);
        }
    }

    /**
     * удаление вершины.
     *
     * @param vertex номер вершины
     */
    @Override
    public void deleteVertex(int vertex) {
        if (vertex < 0 || vertex >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                + Integer.toString(vertex) + " не существует.");
        }
        for (int i = this.matrix.size() - 1; i >= 0; i--) {
            if (this.matrix.get(i).get(vertex) != 0) {
                this.matrix.remove(i);
            } else {
                this.matrix.get(i).remove(vertex);
            }
        }
        vertexCount--;
    }

    /**
     * добавление ребра.
     *
     * @param vertex1 начальная вершина
     * @param vertex2 конечная вершина
     */
    @Override
    public void addEdge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex1 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                + Integer.toString(vertex1) + " не существует.");
        }
        if (vertex1 == vertex2) {
            throw new GraphException("Петли в данной реализации запрещены");
        }
        if (vertex2 < 0 || vertex2 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                + Integer.toString(vertex2) + " не существует.");
        }
        List<Integer> edge = new ArrayList<Integer>();
        for (int i = 0; i < this.vertexCount; i++) {
            if (i == vertex1) {
                edge.add(1);
            } else if (i == vertex2) {
                edge.add(-1);
            } else {
                edge.add(0);
            }
        }
        this.matrix.add(edge);
    }

    /**
     * удаление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    @Override
    public void deleteEdge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex1 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                + Integer.toString(vertex1) + " не существует.");
        }
        if (vertex2 < 0 || vertex2 >= this.vertexCount) {
            throw new GraphException("Вершины с номером "
                + Integer.toString(vertex2) + " не существует.");
        }
        for (int i = this.matrix.size() - 1; i >= 0; i--) {
            List<Integer> edge = this.matrix.get(i);
            if (edge.get(vertex1) == 1
                && edge.get(vertex2) == -1) {
                this.matrix.remove(i);
                break;
            }
        }
    }

    /**
     * получение соседей вершины.
     *
     * @param vertex - номер вершины
     */
    @Override
    public List<Integer> getNeighbours(int vertex) {
        List<Integer> neighbours = new ArrayList<Integer>();
        for (List<Integer> edge : this.matrix) {
            if (edge.get(vertex) == 1) {
                for (int i = 0; i < this.vertexCount; i++) {
                    if (edge.get(i) == -1
                        && !neighbours.contains(i)) {
                        neighbours.add(i);
                        break;
                    }
                }
            }
        }
        return neighbours;
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
