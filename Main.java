package practicoEspecialP2;

import practico1.RecursiveResurses;
import practicoEspecialP2.services.ShortNetBacktracking;
import practicoEspecialP2.services.ShortNetGreedy;
import practicoEspecialP2.services.ShortNetKruskal;

public class Main {

    public static void main(String[] args) {

        String path = "src/practicoEspecialP2/datasets/dataset1.txt";
        CSVReader reader = new CSVReader(path);
        reader.read();

        ShortNetGreedy greedy = new ShortNetGreedy(reader);
        ShortNetKruskal Kruskal = new ShortNetKruskal(reader);
        ShortNetBacktracking backtracking = new ShortNetBacktracking(reader);

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        long startTimeBacktracking = System.nanoTime();
        System.out.println(backtracking.findMinimumCoverTree());
        System.out.println(backtracking.getMinimumDistanceForConnectEveryStations());
        System.out.println(backtracking.getIterations() + " iteraciones");
        long endTimeBacktracking = System.nanoTime();
        long totalTimeBacktracking = endTimeBacktracking - startTimeBacktracking;
        System.out.println("Tiempo de ejecución backtracking : " + totalTimeBacktracking + " nanosegundos");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        long startTimeGreedy = System.nanoTime();
        System.out.println(greedy.findMinimumCoverTree());
        System.out.println(greedy.getMinimumDistanceForConnectEveryStations());
        System.out.println(greedy.getIterations() + " iteraciones");
        long endTimeGreedy = System.nanoTime();
        long totalTimeGreedy = endTimeGreedy - startTimeGreedy;
        System.out.println("Tiempo de ejecución greedy : " + totalTimeGreedy + " nanosegundos");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        long startTimeKruskal = System.nanoTime();
        System.out.println(Kruskal.findMinimumCoverTree());
        System.out.println(Kruskal.getMinimumDistanceForConnectEveryStations());
        System.out.println(Kruskal.getIterations() + " iteraciones");
        long endTimeKruskal = System.nanoTime();
        long totalTimeKruskal = endTimeKruskal - startTimeKruskal;
        System.out.println("Tiempo de ejecución Kruskal : " + totalTimeKruskal + " nanosegundos");
    }

}
