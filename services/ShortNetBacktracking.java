package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Tunnel;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;
import java.util.*;

public class ShortNetBacktracking {

    protected Grafo<?> stations;
    protected ShortNetGreedy greedy;
    protected Integer minimunDistance;
    protected List<Arco<?>> minimumCoverTree;
    protected int iterations;

    public ShortNetBacktracking(CSVReader csvReader) {
        this.stations = csvReader.getStations();
        this.minimumCoverTree = new ArrayList<>();
        this.greedy = new ShortNetGreedy(csvReader);
    }

    private Integer aproximateDistance() {
        greedy.findMinimumCoverTree();
        this.iterations = this.greedy.getIterations();
        return this.greedy.getMinimunDistance() != -1 ? this.greedy.getMinimunDistance() : Integer.MAX_VALUE;
    }

    public String findMinimumCoverTree() {
        this.minimumCoverTree.clear();
        this.minimunDistance = this.aproximateDistance();
        HashSet<Tunnel<?>> currentPath = new HashSet<>();
        HashMap<Tunnel<?>,Integer> visitedEdges = new HashMap<>();

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(currentPath, station, 0, visitedEdges);
        }
        ArrayList<Integer> stations = new ArrayList<>();
        for (Arco<?> a: this.minimumCoverTree) {
            stations.add(a.getVerticeOrigen());
            stations.add(a.getVerticeDestino());
        }
        if(stations.stream().distinct().toList().size() < this.stations.getVertices().size()) {
            this.minimunDistance = -1;
            return "no hay solucion";
        }
        return this.minimumCoverTree.toString();
    }

    private void findMinimumCoverTree(HashSet<Tunnel<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      HashMap<Tunnel<?>,Integer> visitedEdges)
    {
        if (currentLength > this.minimunDistance) return;
        this.iterations++;
        if (allStationsVisited(currentPath)) {
            this.minimunDistance = currentLength;
            this.minimumCoverTree.clear();
            this.minimumCoverTree.addAll(currentPath);
            return;
        }

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos(actualStation);

        while (it.hasNext()) {
            Tunnel<?> actualTunnel = new Tunnel<>(it.next());
            Integer count = visitedEdges.getOrDefault(actualTunnel, 0);
            if (count < 2) {
                visitedEdges.put(actualTunnel, count + 1);
                if (currentPath.add(actualTunnel)) {
                    currentLength += (Integer) actualTunnel.getEtiqueta();
                }
                if (currentLength < minimunDistance) {
                    findMinimumCoverTree(currentPath, actualTunnel.getVerticeDestino(), currentLength, visitedEdges);
                }
                visitedEdges.put(actualTunnel, count);
                if (count == 0) {
                    visitedEdges.remove(actualTunnel);
                    currentPath.remove(actualTunnel);
                    currentLength -= (Integer) actualTunnel.getEtiqueta();
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

    public void print() {
        String sb = "";
        sb += "Backtracking: \n";
        sb += this.findMinimumCoverTree() + "\n";
        sb += this.minimunDistance > 0 ? "Distancia minima: " + this.minimunDistance + " km\n" : "";
        sb +=  "Iteraciones: " + this.iterations;
        System.out.println(sb);
    }

    public String getMinimumDistance(){
        return this.minimunDistance + " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}


