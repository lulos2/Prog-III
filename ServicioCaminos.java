package practicoEspecial;

import java.util.*;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;

    // Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
    }

    public List<List<Integer>> caminos() {
        List<Integer> caminoValido = new LinkedList<>();
        List<List<Integer>> caminos = new ArrayList<>();
        HashSet<Arco<?>> arcosVisitados = new HashSet<>();
        if(this.grafo.contieneVertice(origen) && this.grafo.contieneVertice(destino)) {
            encontrarCaminos(this.origen, this.lim, caminoValido, caminos, arcosVisitados);
        }
        return caminos;
    }

    private void encontrarCaminos(Integer  verticeActual,
                                  Integer limite,
                                  List<Integer> caminoValido,
                                  List<List<Integer>> caminos,
                                  HashSet<Arco<?>> arcosVisitados) {
        if(limite.equals(0)) return;
        caminoValido.add(verticeActual);
        if(this.destino == verticeActual && limite > 0) {
            caminos.add(new ArrayList<>(caminoValido));
        }
        Iterator<? extends Arco<?>> arcos = this.grafo.obtenerArcos(verticeActual);
        while (arcos.hasNext()) {
            Arco arcoActual = arcos.next();
            if(!arcosVisitados.contains(arcoActual)) {
                arcosVisitados.add(arcoActual);
                encontrarCaminos(arcoActual.getVerticeDestino(), limite-1, caminoValido, caminos, arcosVisitados);
                if(!caminoValido.isEmpty()) {
                    caminoValido.remove(caminoValido.size() - 1);
                }
                limite++;
                arcosVisitados.remove(arcoActual);
            }
        }
    }
}

/*Caminos: dado un origen, un destino y un límite “lim” retorna todos los caminos que, partiendo del
vértice origen, llega al vértice de destino sin pasar por más de “lim” arcos. Aclaración importante: en
un camino no se puede pasar 2 veces por el mismo arco*/