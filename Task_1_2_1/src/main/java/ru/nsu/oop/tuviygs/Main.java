package ru.nsu.oop.tuviygs;

import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.stuff.GraphTopasort;
import ru.nsu.oop.tuviygs.io.InputController;
import ru.nsu.oop.tuviygs.io.OutputController;

import java.util.List;

/**
 * это мейн.
 */
public class Main {

    /**
     * запуск программы.
     *
     * @param args - аргументы
     */
    public static void main(String[] args) {

        InputController inputController = new InputController();
        OutputController.printAscFilePath();

        String filePath = inputController.getInput();
        Graph graph = new AdjacencyMatrixGraph();
        graph.readGraphFromFile(filePath);

        List<Integer> sortedGraph = GraphTopasort.topasort(graph);

        OutputController.printIntegerList(sortedGraph);


    }
}