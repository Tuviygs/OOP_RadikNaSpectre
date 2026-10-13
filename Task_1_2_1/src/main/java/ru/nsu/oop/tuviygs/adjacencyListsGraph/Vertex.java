package ru.nsu.oop.tuviygs.adjacencyListsGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * вершина.
 */
public class Vertex {

    /**
     * номер вершины.
     */
    private int number;

    /**
     * соседи.
     */
    private final List<Integer> neighbours;

    /**
     * получение соседей.
     */
    public List<Integer> getNeighbours() {
        return Collections.unmodifiableList(this.neighbours);
    }

    /**
     * получение номера вершины
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * уменьшение номера.
     */
    public void decreaseNumber() {
        this.number--;
    }

    /**
     * создание вершины.
     *
     * @param number - номер вершины.
     */
    public Vertex(int number) {
        this.number = number;
        this.neighbours = new ArrayList<Integer>();
    }

    /**
     * добавление соседа.
     *
     * @param number - номер соседа
     */
    public void addNeighbour(int number) {
        if (!this.neighbours.contains(number)) {
            this.neighbours.add(number);
        }
    }

    /**
     * удаление вершины (какой-то в графе).
     *
     * @param vertex - номер соседа
     */
    public void delVertex(int vertex) {
        if (this.neighbours.contains(vertex)) {
            this.neighbours.remove(Integer.valueOf(vertex));
        }
        if (this.number == vertex) {
            return;
        }
        for (int i = 0; i < this.neighbours.size(); i++) {
            if (this.neighbours.get(i) > vertex) {
                this.neighbours.set(i,
                        this.neighbours.get(i) - 1);
            }
        }
    }

    /**
     * удаление соседа.
     *
     * @param vertex - вершина
     */
    public void delNeighbour(int vertex) {
        if (this.neighbours.contains(vertex)) {
            this.neighbours.remove(Integer.valueOf(vertex));
        }
    }

}
