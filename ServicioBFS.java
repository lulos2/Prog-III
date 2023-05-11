package practicoEspecial;

import practico3.Arco;

import java.util.*;

public class ServicioBFS<T> {

    final private GrafoDirigido<T> grafo;

    public ServicioBFS(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
    }

    /*public List<Integer> bfsForest() {
        Integer verticeOrigen = 1;
        List<Integer> resultado = new ArrayList<>();
        LinkedList <Integer> cola = new LinkedList<>();
        HashMap<Integer,Integer> visitados = new HashMap<>();
        Iterator<Integer> adyasentes = this.grafo.obtenerAdyacentes(verticeOrigen);
        cola.add(verticeOrigen);
        while (!cola.isEmpty()){
            cola.add(adyasentes.next());
        }
        return resultado;
    }*/

    public List<Integer> bfsForest() {
        HashMap<Integer, Boolean> visitados = new HashMap<>();
        List<Integer> resultado = new ArrayList<>();
        LinkedList<Integer> cola = new LinkedList<>();

        for (Map.Entry<Integer, LinkedList<Arco<T>>> entry : this.grafo.vertices.entrySet()){
            if(!visitados.containsKey(entry.getKey())){
                List<Integer> arbol = new ArrayList<>();
                bfsForest(arbol, cola, visitados, entry.getKey());
                resultado.addAll(arbol);
            }
        }

        return resultado;
    }
    private void bfsForest(List<Integer> arbol, LinkedList<Integer> cola, HashMap<Integer, Boolean> visitados, Integer verticeActual){
        if(visitados.containsKey(verticeActual)) {
            return;
        }
        visitados.put(verticeActual, true);
        arbol.add(verticeActual);
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(verticeActual);
        while (adyacentes.hasNext()) {
            cola.addFirst(adyacentes.next());
        }


    }
}