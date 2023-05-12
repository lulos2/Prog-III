package practicoEspecial;


import practico3.Arco;
import practico3.Vertice;

import java.util.*;

public class ServicioDFS {

    private Grafo<?> grafo;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    //dfsForest: tiene una complejidad temporal O(N^2) Ya que en el peor caso, cada vértice en el grafo tiene un arco a todos los demás vértices, por lo que en cada llamada recursiva, se debe hacer una llamada a la función obtenerAdyacentes para cada uno de los  vértices
    public List<Integer> dfsForest() {
        List<Integer> resultado = new ArrayList<>();
        HashMap<Integer,Boolean> visitados = new HashMap<>();
        for (Integer entry : this.grafo.getVertices()) {
            if(!visitados.containsKey(entry)) {
                List<Integer> arbol = new ArrayList<>();
                dfsForest(entry, arbol, visitados);
                resultado.addAll(arbol);
            }
        }
        return resultado;
    }
    private void dfsForest(int verticeActual, List<Integer> arbol, HashMap<Integer,Boolean> visitados) {
        if(visitados.containsKey(verticeActual)) {
            return;
        }
        visitados.put(verticeActual,true);
        arbol.add(verticeActual);
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(verticeActual);
        while(adyacentes.hasNext()) {
            dfsForest(adyacentes.next(), arbol, visitados);
        }
    }

}/*
DFS Forest: dado un grafo, realiza un recorrido en profundidad y retorna un orden posible de
descubrimiento para los vértices durante ese recorrido.
*/