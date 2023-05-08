package practicoEspecial;


import practico3.Vertice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS<T> {

    private Grafo<T> grafo;

    public ServicioDFS(Grafo<T> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> dfsForest() {
        int verticeOrigen = this.grafo.obtenerVertice((int)(Math.random()*this.grafo.cantidadVertices()));
        List<Integer> resultado = new ArrayList<>();
        return dfsForest(verticeOrigen, resultado);
    }
    private List<Integer> dfsForest(int verticeOrigen, List<Integer> resultado) {
        if(!this.grafo.obtenerAdyacentes(verticeOrigen).hasNext()){
            return resultado;
        }
        resultado.add(verticeOrigen);
        dfsForest(this.grafo.obtenerAdyacentes(verticeOrigen).next(),resultado);
        return resultado;
    }

}
/*DFS Forest: dado un grafo, realiza un recorrido en profundidad y retorna un orden posible de
descubrimiento para los vértices durante ese recorrido.
*/