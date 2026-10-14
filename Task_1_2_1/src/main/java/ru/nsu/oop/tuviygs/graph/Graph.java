package ru.nsu.oop.tuviygs.graph;

import java.util.List;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.text.TextGraph;
import ru.nsu.oop.tuviygs.graph.stuff.Edge;
import ru.nsu.oop.tuviygs.graph.stuff.GraphFileReader;


/**
 * интерфейс графа.
 */
public interface Graph {

    /**
     * получение количества вершин в графе.
     */
    int getVertexCount();

    /**
     * добавление вершины.
     */
    void addVertex();

    /**
     * удаление вершины.
     *
     * @param vertex - номер вершины
     */
    void deleteVertex(int vertex);

    /**
     * добавление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    void addEdge(int vertex1, int vertex2);

    /**
     * удаление ребра.
     *
     * @param vertex1 - начальная вершина
     * @param vertex2 - конечная вершина
     */
    void deleteEdge(int vertex1, int vertex2);

    /**
     * получение соседей вершины.
     *
     * @param vertex - номер вершины
     */
    List<Integer> getNeighbours(int vertex);

    /**
     * чтение графа из файла.
     *
     * @param fileName - имя файла
     */
    default void readGraphFromFile(String fileName) {
        if (this.getVertexCount() > 0) {
            throw new GraphException("Граф уже задан.");
        }

        TextGraph graphFromFile
                = GraphFileReader.readGraphFromFile(fileName);

        int vertexCount = graphFromFile.vertexCount();
        List<Edge> edges = graphFromFile.edges();
        for (int i = 0; i < vertexCount; i++) {
            this.addVertex();
        }
        for (Edge edge : edges) {
            this.addEdge(edge.from(), edge.to());
        }
    }


}
