package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;

import java.util.*;

public class ShortNetGreedy {

    protected Grafo<?> stations;
    protected HashSet<Arco<?>> minimumCoverTree;
    protected Integer minimunDistanceForConectEveryStations;
    private int iterations;

    public ShortNetGreedy(CSVReader reader) {
        this.stations = reader.getStations();
        this.minimumCoverTree = new HashSet<>();
        minimunDistanceForConectEveryStations = 0;
    }

    public String findMinimumCoverTree() {
        minimumCoverTree.clear();
        minimunDistanceForConectEveryStations = 0;
        HashSet<Integer> visitedStations = new HashSet<>();
        Iterator<Integer> iterator = this.stations.obtenerVertices();

        while (this.stations.getVertices().size() > visitedStations.size()) {
            this.iterations++;
            int pos = 0;
            Integer current = iterator.next();
            visitedStations.add(current);
            if(this.stations.getVertices().size() == visitedStations.size())break;
            Arco<?> bestTunnel = select(current, pos);

            while (this.minimumCoverTree.contains(bestTunnel)||visitedStations.contains(bestTunnel.getVerticeDestino())) {
                pos++;
                bestTunnel = select(current, pos);
            }
            this.minimumCoverTree.add(bestTunnel);
            this.minimunDistanceForConectEveryStations += (Integer) bestTunnel.getEtiqueta();
        }
        return ("Greedy:\n" + this.minimumCoverTree.toString());
    }

    public Arco<?> select (int v, int pos) {
       return this.stations.getOrderEdges(v).get(pos);
    }
    public String getMinimumDistanceForConnectEveryStations(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.minimunDistanceForConectEveryStations);
        sb.append("km");
        return sb.toString();
    }
    public int getIterations(){
        return this.iterations;
    }
}
