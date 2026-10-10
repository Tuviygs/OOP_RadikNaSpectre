package ru.nsu.oop.tuviygs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для считывания графа из файла.
 */
public class GraphFileReader {

    /**
     * ребро.
     *
     * @param from - стартовая вершина
     * @param to - конечная
     */
    public record Edge(int from, int to) {}

    /**
     * представление графа.
     *
     * @param vertexCount - количество вершин
     * @param edges - список рёбер
     */
    public record GrafRepresentation(int vertexCount, List<Edge> edges) {}

    public static GrafRepresentation readGraphFromFile(String filePath) {

        List<Edge> edges = new ArrayList<Edge>();
        int vertexCount;

        try (BufferedReader reader
                     = new BufferedReader(new FileReader(filePath))) {

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
                    throw new GraphException("Неверный формат ребра в строке:" +
                            " должно быть два числа.");
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

        return new GrafRepresentation(vertexCount, edges);
    }

}
