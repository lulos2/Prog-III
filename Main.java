package practicoEspecialP2;

import practicoEspecialP2.services.ShortNetBacktracking;
import practicoEspecialP2.services.ShortNetGreedy;
import practicoEspecialP2.services.ShortNetKruskal;

public class Main {

    public static void main(String[] args) {

        String path = "src/practicoEspecialP2/datasets/dataset1.txt";
        String dataset2 = "src/practicoEspecialP2/datasets/dataset2.txt";
        CSVReader reader = new CSVReader(path);
        CSVReader readerDataset2 = new CSVReader(dataset2);

        reader.read();
        readerDataset2.read();

        ShortNetGreedy greedy = new ShortNetGreedy(reader);
        ShortNetKruskal Kruskal = new ShortNetKruskal(reader);
        ShortNetBacktracking backtracking = new ShortNetBacktracking(reader);
        ShortNetBacktracking backtracking2 = new ShortNetBacktracking(readerDataset2);


        backtracking.print();
        System.out.println("dataset2");
        backtracking2.print();
        /*System.out.println("----------------------------------------------------------------------------------------------------------------");
        long startTimeBacktracking = System.nanoTime();
        backtracking.print();
        long endTimeBacktracking = System.nanoTime();
        long totalTimeBacktracking = endTimeBacktracking - startTimeBacktracking;
        System.out.println("Tiempo de ejecución backtracking : " + totalTimeBacktracking + " nanosegundos");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        long startTimeGreedy = System.nanoTime();
        greedy.print();
        long endTimeGreedy = System.nanoTime();
        long totalTimeGreedy = endTimeGreedy - startTimeGreedy;
        System.out.println("Tiempo de ejecución greedy : " + totalTimeGreedy + " nanosegundos");

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        long startTimeKruskal = System.nanoTime();
        System.out.println(Kruskal.findMinimumCoverTree());
        System.out.println(Kruskal.getMinimumDistance());
        System.out.println(Kruskal.getIterations() + " iteraciones");
        long endTimeKruskal = System.nanoTime();
        long totalTimeKruskal = endTimeKruskal - startTimeKruskal;
        System.out.println("Tiempo de ejecución Kruskal : " + totalTimeKruskal + " nanosegundos");
   */
    }

}
