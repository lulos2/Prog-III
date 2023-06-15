package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ShortNetBacktracking {

    protected Grafo<?> stations;
    protected Integer minimunDistanceForConectEveryStations;

    protected List<Arco<?>> minimumCoverTree;
    protected Integer iterations;

    public ShortNetBacktracking(CSVReader csvReader) {
        this.stations = csvReader.getStations();
        this.minimumCoverTree = new ArrayList<>();
    }

    public String findMinimumCoverTree() {
        this.minimumCoverTree.clear();
        this.iterations = 0;
        this.minimunDistanceForConectEveryStations = Integer.MAX_VALUE;
        HashSet<Arco<?>> currentPath = new HashSet<>();
        ArrayList<Integer> visitedStations = new ArrayList<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedStations);
        }
        return ("Backtracking: \n" + this.minimumCoverTree.toString());
    }

    private void findMinimumCoverTree(HashSet<Arco<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      ArrayList<Integer> visitedStations) {
        if (currentLength >= minimunDistanceForConectEveryStations) return;
        if (visitedStations.size() == this.stations.getVertices().size()) {
            this.minimunDistanceForConectEveryStations = currentLength;
            this.minimumCoverTree.clear();
            this.minimumCoverTree.addAll(currentPath);
            return;
        }
        visitedStations.add(actualStation);

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()) {
            Arco<?> actualTunnel = it.next();
            int source = actualTunnel.getVerticeOrigen();
            int destination = actualTunnel.getVerticeDestino();
            int distance = (int) actualTunnel.getEtiqueta();

            if (visitedStations.contains(source) && visitedStations.contains(destination)) {
                continue;
            }

            visitedStations.add(source);
            currentPath.add(actualTunnel);
            currentLength += distance;
            findMinimumCoverTree(currentPath, actualTunnel.getVerticeOrigen(), currentLength, visitedStations);
            currentLength -= distance;
            currentPath.remove(actualTunnel);
            visitedStations.remove(source);
        }
    }

    public String getMinimumDistanceForConnectEveryStations(){
        return this.minimunDistanceForConectEveryStations +
                " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}


