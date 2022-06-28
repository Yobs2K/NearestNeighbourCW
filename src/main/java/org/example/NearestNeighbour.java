package org.example;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbour {

    private List<Integer> route;
    private int vertNum;
    private boolean[] used;
    private Graph graph;
    private boolean isCalculated;
    private double routeLength;
    private String errorText;

    public NearestNeighbour(Graph graph) {
        vertNum = graph.getVertNum();
        this.graph = graph;
        isCalculated = false;
        routeLength = 0;
        errorText = null;
    }

    public NearestNeighbour() {

    }

    public boolean findOptimalRoute() {
        if (isCalculated)
            return true;

        route = new ArrayList<>();
        used = new boolean[vertNum];
        dfs(0);
        if (!checkUsed()) {
            errorText = "Не удалось пройти все вершины.";
            return false;
        }

        double lastCost = graph.getMatrix()[route.get(vertNum - 1)][0];
        if (lastCost == 0) {
            errorText = "Не удалось вернуться в начальную вершину.";
            return false;
        }

        route.add(0);
        routeLength += lastCost;
        isCalculated = true;
        return true;
    }

    public List<Integer> getOptimalRoute() {
        if (isCalculated)
            return route;
        return null;
    }

    public double getRouteLength() {
        if (isCalculated)
            return routeLength;
        return -1;
    }

    private void dfs(int vertIndex) {
        route.add(vertIndex);
        used[vertIndex] = true;
        if (checkUsed())
            return;
        double minCost = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < vertNum; ++i) {
            double nextVertCost = graph.getMatrix()[vertIndex][i];
            if (used[i] || nextVertCost == 0) {
                continue;
            }
            if (nextVertCost < minCost) {
                minCost = nextVertCost;
                minIndex = i;
            }
        }
        routeLength += minCost;
        if (used[minIndex])
            return;
        dfs(minIndex);
    }

    private boolean checkUsed() {
        for (int i = 0; i < vertNum; ++i) {
            if (!used[i])
                return false;
        }
        return true;
    }

    public void setGraph(Graph graph) {
        vertNum = graph.getVertNum();
        route = new ArrayList<>();
        used = new boolean[vertNum];
        this.graph = graph;
        isCalculated = false;
        routeLength = 0;
        errorText = null;
    }

    public String getErrorText() {
        return errorText;
    }
}
