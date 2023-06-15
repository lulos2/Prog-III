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
        List<Arco<?>> visitedEdges = new ArrayList<>();
        HashSet<Arco<?>> currentPath = new HashSet<>();
        HashSet<Integer> visitedStations = new HashSet<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedStations, visitedEdges);
        }
        return ("Backtracking: \n" + this.minimumCoverTree.toString());
    }

    private void findMinimumCoverTree(HashSet<Arco<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      HashSet<Integer> visitedStations,
                                      List<Arco<?>> visitedEdges) {
        if (currentLength >= minimunDistanceForConectEveryStations) return;
        if (visitedStations.size() == this.stations.getVertices().size()) {
            this.minimunDistanceForConectEveryStations = currentLength;
            this.minimumCoverTree.clear();
            this.minimumCoverTree.addAll(currentPath);
            return;
        }
        visitedStations.add(actualStation);
        this.iterations++;
        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()) {
            Arco<?> actualTunnel = it.next();
            int source = actualTunnel.getVerticeOrigen();
            int destination = actualTunnel.getVerticeDestino();
            int distance = (int) actualTunnel.getEtiqueta();

            if (visitedStations.contains(source) && visitedStations.contains(destination)) continue;

            if (!visitedStations.contains(source)) {
                visitedStations.add(source);
                currentPath.add(actualTunnel);
                currentLength += distance;
                findMinimumCoverTree(currentPath, actualTunnel.getVerticeOrigen(), currentLength, visitedStations, visitedEdges);
                currentLength -= distance;
                currentPath.remove(actualTunnel);
                visitedStations.remove(destination);
                visitedStations.remove(source);
            } else if (!visitedStations.contains(destination)) {
                visitedStations.add(destination);
                currentPath.add(actualTunnel);
                currentLength += distance;
                findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedStations, visitedEdges);
                currentLength -= distance;
                visitedStations.remove(destination);
                visitedStations.remove(source);
                currentPath.remove(actualTunnel);
            }
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


