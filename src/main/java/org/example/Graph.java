package org.example;

public class Graph {
    //Взвешенный граф, определённый матрицей смежности (матрицей весов)

    private int vertNum; //Количество вершин

    private double matrix[][]; //Матрица смежности

    public Graph (int N) {
        vertNum = N;
        matrix = new double[N][];
        for (int i = 0; i < N; ++i) {
            matrix[i] = new double[N];
        }
    }

    public Graph(double[][] matrix) {
        int height = matrix.length;
        for (int i = 0; i < height; ++i) {
            if (matrix[i].length != height)
                throw new RuntimeException("Двумерный массив, поданный на вход, не является матрицей");
        }
        this.matrix = matrix;
        vertNum = height;
    }

    public void addEdge(int from, int to, double cost) {
        matrix[from][to] = cost;
    }

    public void deleteEdge(int from, int to) {
        matrix[from][to] = 0;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getEdgesForVertex(int vert) {
        return matrix[vert];
    }

    public int getVertNum() {
        return vertNum;
    }
}
