package practicoEspecial.parte2.services;

import practicoEspecial.parte1.Arco;
import practicoEspecial.parte1.Grafo;
import practicoEspecial.parte2.CSVReader;

import java.util.*;

public class ShortNetGreedy {

    protected Grafo<?> stations;
    protected HashSet<Arco<?>> minimumCoverTree;
    protected Integer minimunDistance;
    private int iterations;

    public ShortNetGreedy(CSVReader reader) {
        this.stations = reader.getStations();
        this.minimumCoverTree = new HashSet<>();
        minimunDistance = 0;
    }

    public String findMinimumCoverTree() {
        minimumCoverTree.clear();
        minimunDistance = 0;
        iterations = 0;
        HashSet<Integer> visitedStations = new HashSet<>();
        Iterator<Integer> iterator = this.stations.obtenerVertices();

        while (this.stations.getVertices().size() > visitedStations.size()) {
            this.iterations++;
            int pos = 0;
            Integer current = iterator.next();
            visitedStations.add(current);
            if(this.stations.getVertices().size() == visitedStations.size())break;
            Arco<?> bestTunnel = select(current, pos);

            while (this.minimumCoverTree.contains(bestTunnel) || visitedStations.contains(bestTunnel.getVerticeDestino())) {
                pos++;
                bestTunnel = select(current, pos);
                if(bestTunnel == null) {
                    this.minimunDistance = -1;
                    return "no encontro solucion";
                }
            }
            this.minimumCoverTree.add(bestTunnel);
            this.minimunDistance += (Integer) bestTunnel.getEtiqueta();
        }
        if(visitedStations.size() == this.stations.getVertices().size()) {
            return this.minimumCoverTree.toString();
        }
        else return "no encontro solucion";
    }

    public Arco<?> select (int v, int pos) {
        if(pos < this.stations.getOrderEdges(v).size())
            return this.stations.getOrderEdges(v).get(pos);
        else
            return null;
    }

    public void print() {
        String sb = "";
        sb += "Greedy: \n";
        sb += this.findMinimumCoverTree() + "\n";
        sb += this.minimunDistance > 0 ? "Distancia minima: " + this.minimunDistance + " km\n" : "";
        sb +=  "Iteraciones: " + this.iterations ;
        System.out.println(sb);
    }

    public Integer getMinimunDistance() {
        return this.minimunDistance;
    }

    public int getIterations(){
        return this.iterations;
    }
}
