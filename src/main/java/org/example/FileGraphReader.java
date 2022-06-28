package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileGraphReader implements GraphReader{ //Класс, создающий граф на основе данных из файла

    private final Scanner fileScanner;

    public FileGraphReader (String filePath) throws FileNotFoundException {
        fileScanner = new Scanner(new File(filePath));
    }

    @Override
    public Graph readGraphFromAdjList() {
        int vertNum = fileScanner.nextInt();
        int edgesNum = fileScanner.nextInt();
        Graph graph = new Graph(vertNum);
        for (int i = 0; i < edgesNum; ++i) {
            int from, to, cost;
            from = fileScanner.nextInt()-1;
            to = fileScanner.nextInt()-1;
            cost = fileScanner.nextInt();
            graph.addEdge(from, to, cost);
        }
        return graph;
    }

    @Override
    public Graph readGraphFromMatrix() {
        int vertNum = fileScanner.nextInt();
        Graph graph = new Graph(vertNum);
        for (int i = 0; i < vertNum; ++i) {
            for (int j = 0; j < vertNum; ++j) {
                int from, to;
                double cost;
                from = i;
                to = j;
                cost = fileScanner.nextDouble();
                graph.addEdge(from, to, cost);
            }
        }
        return graph;
    }
}
