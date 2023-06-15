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
        visitedStations.add(actualStation);
        if (visitedStations.size() == this.stations.getVertices().size()) {
            if (currentLength < minimunDistanceForConectEveryStations) {
                this.minimunDistanceForConectEveryStations = currentLength;
                this.minimumCoverTree.clear();
                this.minimumCoverTree.addAll(currentPath);
            }
            return;
        }

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()) {
            Arco<?> actualTunnel =  it.next();
            visitedStations.add(actualStation);
            if (!visitedEdges.contains(actualTunnel)) {
                visitedEdges.add(actualTunnel);
                if (!currentPath.contains(actualTunnel)) {
                    currentLength += (Integer) actualTunnel.getEtiqueta();
                    currentPath.add(actualTunnel);
                }
                findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedStations, visitedEdges);
                currentPath.remove(actualTunnel);
                currentLength -= (Integer) actualTunnel.getEtiqueta();
                visitedEdges.remove(actualTunnel);
                visitedStations.remove(actualStation);
                if(currentLength > this.minimunDistanceForConectEveryStations)return;
            }
        }
    }


    public String getMinimumDistanceForConnectEveryStations(){
        return this.minimunDistanceForConectEveryStations +
                "km";
    }
    public int getIterations(){
        return this.iterations;
    }
}
