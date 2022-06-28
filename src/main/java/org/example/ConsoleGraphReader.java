package org.example;

import java.util.Scanner;

public class ConsoleGraphReader implements GraphReader{ //Класс, создающий граф на основе данных из консоли

    private final Scanner console = new Scanner(System.in);

    @Override
    public Graph readGraphFromAdjList() {
        int vertNum = console.nextInt();
        int edgesNum = console.nextInt();
        Graph graph = new Graph(vertNum);
        for (int i = 0; i < edgesNum; ++i) {
            int from, to, cost;
            from = console.nextInt()-1;
            to = console.nextInt()-1;
            cost = console.nextInt();
            graph.addEdge(from, to, cost);
        }
        return graph;
    }

    @Override
    public Graph readGraphFromMatrix() {
        int vertNum = console.nextInt();
        Graph graph = new Graph(vertNum);
        for (int i = 0; i < vertNum; ++i) {
            for (int j = 0; j < vertNum; ++j) {
                int from, to;
                double cost;
                from = i;
                to = j;
                cost = console.nextDouble();
                graph.addEdge(from, to, cost);
            }
        }
        return graph;
    }
}
