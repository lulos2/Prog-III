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
        HashSet<Arco<?>> currentPath = new HashSet<>();
        ArrayList<Arco<?>> visitedEdges = new ArrayList<>();
        HashSet<Integer> visitedStations = new HashSet<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedEdges, visitedStations);
        }
        return ("Backtracking: \n" + this.minimumCoverTree.toString());
    }

    private void findMinimumCoverTree(HashSet<Arco<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      ArrayList<Arco<?>> visitedEdges,
                                      HashSet<Integer> visitedStations)
    {
        this.iterations++;
        if (currentLength >= this.minimunDistanceForConectEveryStations) return;
        visitedStations.add(actualStation);
        if (allStationsVisited(currentPath) && currentPath.size() >= this.stations.getVertices().size()-1) {
            this.minimunDistanceForConectEveryStations = currentLength;
            this.minimumCoverTree.clear();
            this.minimumCoverTree.addAll(currentPath);
            return;
        }

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()) {
            Arco<?> actualTunnel = it.next();

            if (!visitedEdges.contains(actualTunnel)) {
                visitedEdges.add(actualTunnel);
                if (!currentPath.contains(actualTunnel)) {
                    currentPath.add(actualTunnel);
                    currentLength += (Integer) actualTunnel.getEtiqueta();
                    if (currentLength < this.minimunDistanceForConectEveryStations) {
                        findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedEdges, visitedStations);
                    }
                    currentPath.remove(actualTunnel);
                    currentLength -= (Integer) actualTunnel.getEtiqueta();
                }
                findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedEdges, visitedStations);
                visitedEdges.remove(actualTunnel);
            }
        }
        visitedStations.remove(actualStation);
    }


    private boolean allStationsVisited(HashSet<Arco<?>> visitedEdges) {
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
        return this.minimunDistanceForConectEveryStations + " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}


