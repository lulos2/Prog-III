package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecialP2.CSVReader;

import java.util.*;


public class ShortNetKruskalBacktracking {

    protected Grafo<?> stations;
    protected HashSet<Arco<?>> minimumCoverTree;
    protected Integer minimunDistanceForConectEveryStations;
    private int iterations;

    public ShortNetKruskalBacktracking(CSVReader reader) {
        this.stations = reader.getStations();
        this.minimumCoverTree = new HashSet<>();
        minimunDistanceForConectEveryStations = Integer.MAX_VALUE;
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

        boolean[] visited = new boolean[stations.getVertices().size() + 1];
        backtrack(allEdges, visited, 0);

        return ("Kruskal (Backtracking): \n" + minimumCoverTree.toString());
    }

    private void backtrack(List<Arco<?>> allEdges, boolean[] visited, int currentIndex) {
        if (currentIndex == allEdges.size()) {
            return;
        }

        Arco<?> currentEdge = allEdges.get(currentIndex);
        int source = currentEdge.getVerticeOrigen();
        int destination = currentEdge.getVerticeDestino();
        this.iterations++;

        visited[currentIndex] = true;
        if (isConnected(visited)) {
            minimumCoverTree.add(currentEdge);
            minimunDistanceForConectEveryStations += (Integer) currentEdge.getEtiqueta();
        }

        backtrack(allEdges, visited, currentIndex + 1);

        visited[currentIndex] = false;
        backtrack(allEdges, visited, currentIndex + 1);
    }

    private boolean isConnected(boolean[] visited) {
        DisjointSet disjointSet = new DisjointSet(stations.getVertices().size());

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                Arco<?> edge = allEdges.get(i);
                int source = edge.getVerticeOrigen();
                int destination = edge.getVerticeDestino();

                if (disjointSet.find(source) != disjointSet.find(destination)) {
                    disjointSet.union(source, destination);
                }
            }
        }

        int representative = -1;
        for (int i = 1; i <= stations.getVertices().size(); i++) {
            if (visited[i]) {
                if (representative == -1) {
                    representative = disjointSet.find(i);
                } else if (representative != disjointSet.find(i)) {
                    return false;
                }
            }
        }

        return true;
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
}


    public String getMinimumDistanceForConnectEveryStations(){
        return this.minimunDistanceForConectEveryStations +
                " km";
    }
    public int getIterations(){
        return this.iterations;
    }
}
