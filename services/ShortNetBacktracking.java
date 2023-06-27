package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecial.Tunnel;
import practicoEspecialP2.CSVReader;


import java.util.*;

public class ShortNetBacktracking {

    protected Grafo<?> stations;
    protected Integer minimunDistance;

    protected List<Arco<?>> minimumCoverTree;
    protected Integer iterations;

    public ShortNetBacktracking(CSVReader csvReader) {
        this.stations = csvReader.getStations();
        this.minimumCoverTree = new ArrayList<>();
    }

    public String findMinimumCoverTree() {
        this.minimumCoverTree.clear();
        this.iterations = 0;
        this.minimunDistance = Integer.MAX_VALUE;
        HashSet<Tunnel<?>> currentPath = new HashSet<>();
        ArrayList<Arco<?>> visitedEdges = new ArrayList<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedEdges);
        }
        return ("Backtracking: \n" + this.minimumCoverTree.toString());
    }

    private void findMinimumCoverTree(HashSet<Tunnel<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      ArrayList<Arco<?>> visitedEdges)
    {
        this.iterations++;
        if (currentLength >= this.minimunDistance) return;

        if (allStationsVisited(currentPath) && currentPath.size() >= this.stations.getVertices().size()-1) {
            this.minimunDistance = currentLength;
            this.minimumCoverTree.clear();
            this.minimumCoverTree.addAll(currentPath);
            return;
        }

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()) {
            Arco<?> actualEdge = it.next();
            if(!visitedEdges.contains(actualEdge)) {
                visitedEdges.add(actualEdge);
                Tunnel<?> actualTunnel = new Tunnel<>(actualEdge);
                if(currentPath.add(actualTunnel)) {
                    currentLength += (Integer) actualEdge.getEtiqueta();
                }
                if(currentLength < minimunDistance) {
                    findMinimumCoverTree(currentPath, actualEdge.getVerticeDestino(), currentLength, visitedEdges);
                }
                visitedEdges.remove(actualEdge);
                if(!visitedEdges.contains(actualEdge) && currentPath.contains(actualTunnel)) {
                    currentPath.remove(actualTunnel);
                    currentLength -= (Integer) actualEdge.getEtiqueta();
                }
            }
        }
    }


    private boolean allStationsVisited(HashSet<Tunnel<?>> visitedEdges) {
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
        return this.minimunDistance + " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}


