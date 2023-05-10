package practicoEspecial;


import practico3.Vertice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS<T> {

    private GrafoDirigido<T> grafo;

    public ServicioDFS(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> dfsForest() {
        int verticeOrigen = this.grafo.obtenerVerticeRandom();
        List<Integer> resultado = new ArrayList<>();
        List<Integer> visitados = new ArrayList<>();
        dfsForest(verticeOrigen, resultado, visitados);
        return resultado;
    }
    private void dfsForest(int verticeActual, List<Integer> arbol, List<Integer> visitados) {
        if(visitados.contains(verticeActual)) {
            return;
        }
        visitados.add(verticeActual);
        arbol.add(verticeActual);
        Iterator<Integer> adyasentes = this.grafo.obtenerAdyacentes(verticeActual);
        while(adyasentes.hasNext()) {
            dfsForest(adyasentes.next(), arbol, visitados);
        }
    }

}/*
DFS Forest: dado un grafo, realiza un recorrido en profundidad y retorna un orden posible de
descubrimiento para los vértices durante ese recorrido.
*/