package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;

import java.util.*;

public class ShortNetKruskal {

    protected Grafo<?> stations;
    protected HashSet<Arco<?>> minimumCoverTree;
    protected Integer minimunDistanceForConectEveryStations;
    private int iterations;
    private boolean conexo;

    public ShortNetKruskal(CSVReader reader) {
        this.stations = reader.getStations();
        this.minimumCoverTree = new HashSet<>();
        minimunDistanceForConectEveryStations = Integer.MAX_VALUE;
        conexo = true;
    }

    public String findMinimumCoverTree() {
        minimumCoverTree.clear();
        minimunDistanceForConectEveryStations = 0;

        List<Arco<?>> allEdges = new ArrayList<>();
        for (Integer station : stations.getVertices()) {
            Iterator<? extends Arco<?>> it = stations.obtenerArcos(station);
            while (it.hasNext()) {
                Arco<?> edge = it.next();
                allEdges.add(edge);
            }
        }
        Collections.sort(allEdges, Comparator.comparingInt(edge -> (Integer) edge.getEtiqueta()));
        DisjointSet disjointSet = new DisjointSet(stations.getVertices().size());
        for (Arco<?> edge : allEdges) {
            int source = edge.getVerticeOrigen();
            int destination = edge.getVerticeDestino();
            this.iterations++;
            if (disjointSet.find(source) != disjointSet.find(destination)) {
                disjointSet.union(source, destination);
                minimumCoverTree.add(edge);
                minimunDistanceForConectEveryStations += (Integer) edge.getEtiqueta();
            }
        }

        if (minimumCoverTree.size() != stations.getVertices().size() - 1) {
            conexo = false;
        }

        if (conexo) {
            return ("Kruskal:\n" + minimumCoverTree.toString());
        } else {
            return ("El grafo no es conexo.");
        }
    }
    private static class DisjointSet {
        private int[] parent;
        private int[] rank;

        public DisjointSet(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];

            for (int i = 0; i <= size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }
    }

    public String getMinimumDistance(){
        return this.minimunDistanceForConectEveryStations + " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}
