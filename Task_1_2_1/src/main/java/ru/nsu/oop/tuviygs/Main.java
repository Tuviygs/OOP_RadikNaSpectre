package ru.nsu.oop.tuviygs;

import java.util.List;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.stuff.GraphTopasort;
import ru.nsu.oop.tuviygs.io.InputController;
import ru.nsu.oop.tuviygs.io.OutputController;


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
        OutputController.printAscFileName();

        String fileName = inputController.getInput();
        Graph graph = new AdjacencyMatrixGraph();

        try {
            graph.readGraphFromFile(fileName);
            List<Integer> sortedGraph = GraphTopasort.topasort(graph);

            OutputController.printIntegerList(sortedGraph);
        } catch (GraphException exception) {
            System.out.println(exception.getMessage());
        }



    }
}