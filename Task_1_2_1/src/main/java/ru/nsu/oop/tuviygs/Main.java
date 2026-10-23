package ru.nsu.oop.tuviygs;

import java.util.List;
import ru.nsu.oop.tuviygs.graph.Graph;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.lists.AdjacencyListsGraph;
import ru.nsu.oop.tuviygs.graph.representation.adjacency.matrix.AdjacencyMatrixGraph;
import ru.nsu.oop.tuviygs.graph.representation.incidence.matrix.IncidenceMatrixGraph;
import ru.nsu.oop.tuviygs.graph.stuff.GraphTopasort;
import ru.nsu.oop.tuviygs.io.InputController;
import ru.nsu.oop.tuviygs.io.OutputController;
import ru.nsu.oop.tuviygs.stuff.Stuff;


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
        OutputController.printLetChooseRepresentation();
        String answer = inputController.getInput();

        Graph graph;

        try {
            graph = Stuff.setMatrixRepresentation(answer);
        } catch (GraphException exception) {
            OutputController.printException(exception);
            return;
        }

        OutputController.printAscFileName();
        String fileName = inputController.getInput();
        try {
            graph.readGraphFromFile(fileName);
            List<Integer> sortedGraph = GraphTopasort.topasort(graph);

            OutputController.printIntegerList(sortedGraph);
        } catch (GraphException exception) {
            OutputController.printException(exception);
        }



    }
}