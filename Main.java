package practicoEspecialP2;

import practicoEspecialP2.services.ShortNetBacktracking;
import practicoEspecialP2.services.ShortNetGreedy;
import practicoEspecialP2.services.ShortNetKruskal;

public class Main {

    public static void main(String[] args) {

        String dataset1 = "src/practicoEspecialP2/datasets/dataset2.txt";
        String dataset2 = "src/practicoEspecialP2/datasets/dataset2.txt";
        String dataset3 = "src/practicoEspecialP2/datasets/dataset3.txt";
        CSVReader readerDataset1 = new CSVReader(dataset1);
        CSVReader readerDataset2 = new CSVReader(dataset2);
        CSVReader readerDataset3 = new CSVReader(dataset3);

        readerDataset1.read();
        readerDataset2.read();
        readerDataset3.read();

        ShortNetGreedy greedy = new ShortNetGreedy(readerDataset1);
        ShortNetBacktracking backtracking = new ShortNetBacktracking(readerDataset1);


        long startTimeGreedy = System.nanoTime();
        greedy.print();
        long endTimeGreedy = System.nanoTime();
        long totalTimeGreedy = endTimeGreedy - startTimeGreedy;
        System.out.println("Tiempo de ejecución greedy : " + totalTimeGreedy + " nanosegundos");

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        long startTimeBacktracking = System.nanoTime();
        backtracking.print();
        long endTimeBacktracking = System.nanoTime();
        long totalTimeBacktracking = endTimeBacktracking - startTimeBacktracking;
        System.out.println("Tiempo de ejecución backtracking : " + totalTimeBacktracking + " nanosegundos");
    }

}
