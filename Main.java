package practicoEspecialP2;

import practicoEspecialP2.services.ShortNetBacktracking;
import practicoEspecialP2.services.ShortNetGreedy;
import practicoEspecialP2.services.ShortNetKruskal;

public class Main {

    public static void main(String[] args) {

        String path = "C:\\Users\\lulo\\Documents\\tudai\\2do\\PROG-III\\prog3\\src\\practicoEspecialP2\\datasets\\dataset2.txt";
        CSVReader reader = new CSVReader(path);
        reader.read();

        ShortNetGreedy greedy = new ShortNetGreedy(reader);
        ShortNetKruskal Kruskal = new ShortNetKruskal(reader);
        ShortNetBacktracking backtracking = new ShortNetBacktracking(reader);
        /*System.out.println(backtracking.findMinimumCoverTree());
        System.out.println(backtracking.getMinimumDistanceForConnectEveryStations());
        System.out.println(backtracking.getIterations());*/
        System.out.println(Kruskal.findMinimumCoverTree());
        System.out.println(Kruskal.getMinimumDistanceForConnectEveryStations());
        System.out.println(Kruskal.getIterations());
        System.out.println(greedy.findMinimumCoverTree());
        System.out.println(greedy.getMinimumDistanceForConnectEveryStations());
        System.out.println(greedy.getIterations());
    }

}
