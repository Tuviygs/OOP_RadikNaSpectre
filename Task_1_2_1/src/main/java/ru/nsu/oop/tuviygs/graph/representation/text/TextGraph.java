package ru.nsu.oop.tuviygs.graph.representation.text;

import java.util.List;
import ru.nsu.oop.tuviygs.graph.stuff.Edge;

/**
 * представление графа.
 *
 * @param vertexCount - количество вершин
 * @param edges - список рёбер
 */
public record TextGraph(int vertexCount, List<Edge> edges) {

}
