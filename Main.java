package practicoEspecialP2;

import practicoEspecialP2.services.ShortNetBacktracking;

public class Main {

    public static void main(String[] args) {

        String path = "C:\\Users\\lulo\\Documents\\tudai\\2do\\PROG-III\\prog3\\src\\practicoEspecialP2\\datasets\\dataset1.txt";
        CSVReader reader = new CSVReader(path);
        reader.read();

        ShortNetBacktracking backtracking = new ShortNetBacktracking(reader);
        System.out.println(reader.getStations());
        System.out.println(backtracking.findMinimumCoverTree());
        System.out.println(backtracking.getMinimunDistanceForConectEveryStations());
    }

}
