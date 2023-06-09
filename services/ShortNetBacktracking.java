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

    public ShortNetBacktracking(CSVReader csvReader) {
        this.stations = csvReader.getStations();
        this.minimumCoverTree = new ArrayList<>();
    }

    public String findMinimumCoverTree() {
        this.minimumCoverTree.clear();
        List<Arco<?>> currentPath = new ArrayList<>();
        HashSet<Arco<?>> visitedEdges = new HashSet<>();
        HashSet<Integer> visitedStations = new HashSet<>();
        this.minimunDistanceForConectEveryStations = Integer.MAX_VALUE;

        for (Integer station: this.stations.getVertices()){
            findMinimumCoverTree(minimumCoverTree, currentPath, station, 0, visitedStations, visitedEdges);
        }
        return this.minimumCoverTree.toString();
    }

    private void findMinimumCoverTree(List<Arco<?>> minimumCoverTree,
                                      List<Arco<?>> currentPath,
                                      Integer actualStation,
                                      Integer currentLength,
                                      HashSet<Integer> visitedStations,
                                      HashSet<Arco<?>> visitedEdges) {
        if(!visitedStations.contains(actualStation)) {
            visitedStations.add(actualStation);
        }

        Iterator<? extends Arco<?>> it = this.stations.obtenerArcos();

        while (it.hasNext()){
            Arco<?> actualEdge = it.next();

            if (!visitedEdges.contains(actualEdge)) {
                visitedEdges.add(actualEdge);
                if (!currentPath.contains(actualEdge)) {
                    currentLength = currentLength + (Integer) actualEdge.getEtiqueta();
                    currentPath.add(actualEdge);
                }
                if (visitedStations.size() == this.stations.cantidadVertices()) {
                    if (currentLength < minimunDistanceForConectEveryStations) {
                        this.minimunDistanceForConectEveryStations = currentLength;
                        this.minimumCoverTree.clear();
                        this.minimumCoverTree.addAll(currentPath);
                    }
                }
                findMinimumCoverTree(minimumCoverTree, currentPath, actualEdge.getVerticeDestino(), currentLength, visitedStations, visitedEdges);
                visitedStations.remove(actualEdge.getVerticeDestino());
                visitedEdges.remove(actualEdge);
                currentPath.remove(actualEdge);
                currentLength = currentLength - (Integer) actualEdge.getEtiqueta();
            }
        }
    }

    /*Tunnel<?> actualTunnel = (Tunnel<?>) it.next();*/



    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Arco<?> arco : minimumCoverTree) {
            sb.append("E");
            sb.append(arco.getVerticeOrigen());
            sb.append("-");
            sb.append(arco.getVerticeDestino());
            sb.append(", ");
        }
        if (!minimumCoverTree.isEmpty()) {
            sb.setLength(sb.length() - 2); // Eliminar la coma y el espacio extra al final
        }
        sb.append("]");
        return sb.toString();
    }

    public String getMinimumCoverTree(){
        return this.minimumCoverTree.toString();
    }
    public Integer getMinimunDistanceForConectEveryStations(){
        return this.minimunDistanceForConectEveryStations;
    }
}
