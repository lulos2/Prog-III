package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;
import practicoEspecialP2.Tunnel;

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
        List<Arco<?>> currentPath = new ArrayList<>();
        HashSet<Arco<?>> visitedEdges = new HashSet<>();
        HashSet<Integer> visitedStations = new HashSet<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedStations, visitedEdges);
        }
        return ("Backtracking: \n" + this.minimumCoverTree.toString());
    }

    private void findMinimumCoverTree(List<Arco<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      HashSet<Integer> visitedStations,
                                      HashSet<Arco<?>> visitedEdges) {
        this.iterations++;
        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()){
            Arco<?> actualTunnel =  it.next();
            visitedStations.add(actualStation);
            if (!visitedEdges.contains(actualTunnel)) {
                visitedEdges.add(actualTunnel);
                if (!currentPath.contains(actualTunnel)) {
                    currentLength = currentLength + (Integer) actualTunnel.getEtiqueta();
                    currentPath.add(actualTunnel);
                }
                if (visitedStations.containsAll(this.stations.getVertices())) {
                    if (currentLength < minimunDistanceForConectEveryStations) {
                        this.minimunDistanceForConectEveryStations = currentLength;
                        this.minimumCoverTree.clear();
                        this.minimumCoverTree.addAll(currentPath);
                    }
                }
                findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedStations, visitedEdges);
                currentPath.remove(actualTunnel);
                currentLength = (currentLength - (Integer) actualTunnel.getEtiqueta());
                visitedEdges.remove(actualTunnel);
                visitedStations.remove(actualStation);
                /*if(currentLength > this.minimunDistanceForConectEveryStations)return;*/
            }
        }
    }




    public String getMinimumCoverTree(){
        return this.minimumCoverTree.toString();
    }
    public String getMinimunDistanceForConectEveryStations(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.minimunDistanceForConectEveryStations);
        sb.append("km");
        return sb.toString();
    }
    public int getIterations(){
        return this.iterations;
    }
}
