package ru.nsu.oop.tuviygs.stuff;

import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.lists.AdjacencyListsGraph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.representation.incidence.matrix.IncidenceMatrixGraph;

/**
 * всякие штуки.
 */
public class Stuff {

    /**
     * создание графа на выбранной реализации.
     *
     * @param answer выбранная реализация
     */
    public static Graph setMatrixRepresentation(String answer) {
        int number;
        try {
            number = Integer.parseInt(answer.trim());
        } catch (NumberFormatException e) {
            throw new GraphException("Ошибка: не удалось распознать число в строке '" + answer + "'");

        }

        Graph graph;
        switch (number) {
            case 1 -> graph = new AdjacencyMatrixGraph();
            case 2 -> graph = new AdjacencyListsGraph();
            case 3 -> graph = new IncidenceMatrixGraph();
            default -> throw new GraphException("Такого графа нет...");
        };

        return graph;
    }
}
