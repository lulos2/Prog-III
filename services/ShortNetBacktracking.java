package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;


import java.util.*;

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
        ArrayList<Arco<?>> visitedEdges = new ArrayList<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedEdges);
        }
        return ("Backtracking: \n" + this.minimumCoverTree.toString());
    }

    private void findMinimumCoverTree(List<Arco<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      ArrayList<Arco<?>> visitedEdges) {
        this.iterations++;
        if(currentLength > this.minimunDistanceForConectEveryStations){
            return;
        }

        if (allStationsVisited(visitedEdges)) {
            visitedEdges.forEach(System.out::print);
            System.out.print("\n");
            if (currentLength < minimunDistanceForConectEveryStations) {
                this.minimunDistanceForConectEveryStations = currentLength;
                this.minimumCoverTree.clear();
                this.minimumCoverTree.addAll(currentPath);
            }
            return;
        }

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()){
            Arco<?> actualTunnel =  it.next();

            if (!visitedEdges.contains(actualTunnel)) {
                visitedEdges.add(actualTunnel);
                if (!currentPath.contains(actualTunnel)) {
                    currentLength = currentLength + (Integer) actualTunnel.getEtiqueta();
                    currentPath.add(actualTunnel);
                }
                findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedEdges);
                currentPath.remove(actualTunnel);
                currentLength = (currentLength - (Integer) actualTunnel.getEtiqueta());
                visitedEdges.remove(actualTunnel);
            }
        }
    }

    private boolean allStationsVisited(ArrayList<Arco<?>> visitedEdges){
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (Integer station: this.stations.getVertices()){
            visited.put(station, false);
        }
        for (Arco<?> a: visitedEdges){
            visited.put(a.getVerticeOrigen(), true);
            visited.put(a.getVerticeDestino(), true);
        }
        return visited.values().stream().allMatch(x -> x);
    }



    public String getMinimumDistanceForConnectEveryStations(){
        return this.minimunDistanceForConectEveryStations +
                " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}


