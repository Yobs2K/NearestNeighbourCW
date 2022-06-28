package org.example;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Demonstration {

    private final Scanner in = new Scanner(System.in);
    private GraphReader graphReader;
    private Graph graph;
    private final String premadeFilePath = "src/main/resources/test_cases/na128.txt";
    private final NearestNeighbour solver = new NearestNeighbour();

    public void execute() {
        System.out.println("Демонстрация метода ближайшего соседа для решения задачи Коми-вояжера");
        System.out.println("Вы можете ввести данные о графе с консоли либо файла, либо же использовать один из заготовленных файлов");
        int cycleCounter = 0;
        while(cycleCounter++ < 1000) {
            System.out.println("Введите 1, чтобы ввести данные о графе в консоли\n" +
                    "Введите 2, чтобы считать данные о графе с вашего файла\n" +
                    "Введите 3, чтобы использовать один из заготовленных файлов\n" +
                    "Введите 0, чтобы закончить выполнение программы");
            int input = in.nextInt();
            switch (input) {
                case 0:
                    System.out.println("Выполнение программы закончено");
                    return;
                case 1:
                    if(!readFromConsole())
                        continue;;
                    break;
                case 2:
                    if(!readFromUserFile())
                        continue;
                    break;
                case 3:
                    if(!readFromPreparedFile())
                        continue;
            }
            solve();
        }
    }

    private void solve() {
        System.out.println("Введите любой текст, чтобы найти оптимальный маршрут");
        in.next();
        solver.setGraph(graph);
        if(!solver.findOptimalRoute()) {
            System.out.println("Оптимальный путь не найден. Причина: " + solver.getErrorText());
            System.out.println("Введите любой текст, чтобы продолжить");
            in.next();
            return;
        }
        List<Integer> resultRoute = solver.getOptimalRoute();
        double routeLength = solver.getRouteLength();
        System.out.println("\nОптимальный маршрут, найденный методом ближайшего соседа: ");
        resultRoute.forEach(r -> System.out.printf("%s ", r+1));
        System.out.printf("\nДлина всего пути - %s\n\n", routeLength);
    }

    private boolean readFromPreparedFile() {

        System.out.println("Есть 4 заготовленных файла, выберите один из них.");

        System.out.println("1 - граф, вершины которого соответствуют 128 городам в Северной Америке, а веса рёбер равны расстояниям между ними\n" +
                "2 - симуляция распределениям городов по квадратной площади с 10 вершинами\n" +
                "2 - симуляция распределениям городов по квадратной площади с 40 вершинами\n" +
                "3 - симуляция распределениям городов по прямоугольной площади с 15 вершинами\n");
        int input = in.nextInt();
        switch (input) {
            default:
            case 1:
                try {
                    graphReader = new FileGraphReader("src/main/resources/test_cases/na128.txt");
                    graph = graphReader.readGraphFromMatrix();
                } catch (Exception e) {
                }
                break;
            case 2:
                try {
                    graphReader = new FileGraphReader("src/main/resources/test_cases/plain10.txt");
                    graph = graphReader.readGraphFromMatrix();
                } catch (Exception e) {
                }
                break;
            case 3:
                try {
                    graphReader = new FileGraphReader("src/main/resources/test_cases/plain40.txt");
                    graph = graphReader.readGraphFromMatrix();
                } catch (Exception e) {
                }
                break;
            case 4:
                try {
                    graphReader = new FileGraphReader("src/main/resources/test_cases/wide15.txt");
                    graph = graphReader.readGraphFromMatrix();
                } catch (Exception e) {
                }
                break;
        }
        System.out.println("Введите 1, чтобы просмотреть данные о графе. Чтобы продолжить без просмотра " +
                "введите любой другой текст");
        String inputStr = in.next();
        if (Objects.equals(inputStr, "1")) {
            int vertNum = graph.getVertNum();
            double[][] matrix = graph.getMatrix();
            System.out.printf("Количество вершин - %s.  Матрица смежности:\n", vertNum);
            for (int i = 0; i < vertNum; ++i) {
                for (int j = 0; j < vertNum; ++j) {
                    System.out.printf("%s ", matrix[i][j]);
                }
                System.out.println();
            }
        }
        return true;
    }


    private boolean readFromConsole() {
        System.out.println("Введите 1, чтобы ввести данные о графе, используя матрицу смежности\n" +
                "Введите 2, чтобы ввести данные, используя список смежности\n" +
                "Введите 0, чтобы вернуться на предыдущий уровень меню");
        int input;
        while  (true) {
            try {
                input = in.nextInt();
            }
            catch (Exception e) {
                System.out.println("Введёный символ не является числом");
                in.next();
                continue;
            }
            if (input < 0 || input > 2) {
                System.out.println("Опции с данным номером не существует");
                continue;
            }
            else {
                break;
            }
        }
        switch (input) {
            case 1:
                graphReader = new ConsoleGraphReader();
                System.out.println("Введите количество вершин в графе, а затем введите матрицу смежности графа");
                graph = graphReader.readGraphFromMatrix();
                System.out.println();
                return true;
            case 2:
                graphReader = new ConsoleGraphReader();
                System.out.println("Введите количество вершин в графе и количество рёбер, а затем введите все рёбра\n" +
                        "   графа в формате <вершина1> <вершина2> <длина пути>");
                graph = graphReader.readGraphFromAdjList();
                System.out.println();
                return true;
            case 0:
            default:
                return false;
        }
    }
    private boolean readFromUserFile() {
        System.out.println("Введите абсолютный путь к вашему файлу с матрицей смежности, либо введите 0, чтобы вернуться на предыдущий уровень меню");
        while (true) {
            try {
                String filePath = in.next();
                if (filePath == "0")
                    return false;
                graphReader = new FileGraphReader(filePath);
                graph = graphReader.readGraphFromMatrix();
                return true;
            }
            catch (FileNotFoundException e) {
                System.out.println("Файл не найден ");
            }
        }

    }
}
