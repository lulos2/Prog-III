package practicoEspecial;

import practico3.Arco;

import java.util.*;

public class ServicioBFS<T> {

    final private GrafoDirigido<T> grafo;

    public ServicioBFS(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
    }


    //bfsForest(): tiene una eficiencia de O(n^2) donde en el peor caso hay n vertices conectados con n aristas y en cada vertice tengo que preguntar por cada arista
    public List<Integer> bfsForest() {
        HashMap<Integer, Boolean> visitados = new HashMap<>();
        List<Integer> resultado = new ArrayList<>();
        LinkedList<Integer> cola = new LinkedList<>();

        for (Map.Entry<Integer, LinkedList<Arco<T>>> entry : this.grafo.vertices.entrySet()) {
            if(!visitados.containsKey(entry.getKey())) {
                List<Integer> arbol = new ArrayList<>();
                bfsForest(arbol, cola, visitados, entry.getKey());
                resultado.addAll(arbol);
            }
        }
        return resultado;
    }
    private void bfsForest(List<Integer> arbol, LinkedList<Integer> cola, HashMap<Integer, Boolean> visitados, Integer verticeActual) {
        if(visitados.containsKey(verticeActual)) {
            return;
        }
        visitados.put(verticeActual, true);
        arbol.add(verticeActual);
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(verticeActual);
        while (adyacentes.hasNext()) {
            cola.addFirst(adyacentes.next());
        }
        if(!cola.isEmpty()) {
            bfsForest(arbol, cola, visitados, cola.removeLast());
        }
    }
}