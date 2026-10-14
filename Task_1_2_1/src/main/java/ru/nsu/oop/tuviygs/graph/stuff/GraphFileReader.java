package ru.nsu.oop.tuviygs.graph.stuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.oop.tuviygs.graph.exceptions.GraphException;
import ru.nsu.oop.tuviygs.graph.representation.text.TextGraph;

/**
 * класс для считывания графа из файла.
 */
public class GraphFileReader {

    /**
     * чтение графа из файла.
     *
     * @param fileName - имя файла.
     */
    public static TextGraph readGraphFromFile(String fileName) {

        List<Edge> edges = new ArrayList<Edge>();
        int vertexCount;

        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(GraphFileReader.class.getClassLoader()
                .getResourceAsStream(fileName)))) {

            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new GraphException("Файл пуст или некорректен");
            }

            vertexCount = Integer.parseInt(line.trim());
            if (vertexCount < 0) {
                throw new GraphException("Количество вершин"
                        + "не может быть отрицательным.");
            }

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                List<String> tokens = List.of(line.split("\\s+"));
                if (tokens.size() != 2) {
                    throw new GraphException("Неверный формат ребра в строке:"
                            + " должно быть два числа.");
                }

                try {
                    int from = Integer.parseInt(tokens.getFirst());
                    int to = Integer.parseInt(tokens.getLast());
                    edges.add(new Edge(from, to));
                } catch (NumberFormatException e) {
                    throw new GraphException("Ошибка парсинга чисел");
                }
            }
        } catch (IOException exception) {
            throw new GraphException("Ошибка чтения графа из файла");
        }

        return new TextGraph(vertexCount, edges);
    }

}
