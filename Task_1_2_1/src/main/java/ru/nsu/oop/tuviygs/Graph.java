package ru.nsu.oop.tuviygs;

import java.util.List;

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
    void delVertex(int vertex);

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
    void delEdge(int vertex1, int vertex2);

    /**
     * получение соседей вершины.
     *
     * @param vertex - номер вершины
     */
    List<Integer> getNeighbours(int vertex);

    /**
     * чтение графа из файла.
     *
     * @param filePath - имя файла
     */
    default void readGraphFromFile(String filePath) {
        if (this.getVertexCount() > 0) {
            throw new GraphException("Граф уже задан.");
        }

        GraphFileReader.GrafRepresentation graphFromFile
                = GraphFileReader.readGraphFromFile(filePath);

        int vertexCount = graphFromFile.vertexCount();
        List<GraphFileReader.Edge> edges = graphFromFile.edges();
        for (int i = 0; i < vertexCount; i++) {
            this.addVertex();
        }
        for (GraphFileReader.Edge edge : edges) {
            this.addEdge(edge.from(), edge.to());
        }
    }


}
