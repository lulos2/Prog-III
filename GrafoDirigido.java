package practicoEspecial;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    protected HashMap<Integer, LinkedList<Arco<? extends T>>> vertices;

    public GrafoDirigido() {
        this.vertices = new HashMap<>();
    }

    @Override
    public Set<Integer> getVertices(){
        return this.vertices.keySet();
    }
    @Override//agregarVertice(int verticeId): la complejidad temporal es O(1), ya que solo se agrega un nuevo elemento al mapa vertices.
    public void agregarVertice(int verticeId) {
        this.vertices.put(verticeId, new LinkedList<>());
    }

    @Override//borrarVertice(int verticeId): la complejidad temporal es O(N^2), porque en el peor de los casos donde el grafo es pesado y cada uno de los N vertices tiene N aristas
    public void borrarVertice(int verticeId) {
        for (Map.Entry<Integer, LinkedList<Arco<? extends T>>> entry : vertices.entrySet()) {
            for (Arco<? extends T> adyasente : entry.getValue())
                if (existeArco(adyasente.getVerticeOrigen(), verticeId)) {
                    this.borrarArco(adyasente.getVerticeOrigen(), verticeId);
                }
        }
        vertices.remove(verticeId);
    }

    @Override//agregarArco(int verticeId1, int verticeId2, T etiqueta): la complejidad temporal es O(1), ya que solo se agrega un nuevo elemento a la lista de adyacencia correspondiente en el mapa vertices.
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        vertices.get(verticeId1).add((new Arco<>(verticeId1, verticeId2, etiqueta)));
    }

    @Override//borrarArco(int verticeId1, int verticeId2): la complejidad temporal es O(N), donde N es el número de arcos en el grafo. Esto se debe a que se debe buscar el arco en la lista de adyacencia correspondiente en el mapa vertices.
    public void borrarArco(int verticeId1, int verticeId2) {
        vertices.get(verticeId1).remove(obtenerArco(verticeId1, verticeId2));
    }

    @Override//contieneVertice(int verticeId): la complejidad temporal es O(1), ya que se usa el método containsKey() en el mapa vertices.
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    @Override//existeArco(int verticeId1, int verticeId2): la complejidad temporal es O(N). Esto se debe a que utiliza el obtenerArco(int verticeId1, int verticeId2)
    public boolean existeArco(int verticeId1, int verticeId2) {
        return obtenerArco(verticeId1, verticeId2) != null;
    }

    @Override//obtenerArco(int verticeId1, int verticeId2): la complejidad temporal es O(N), donde N es el número de arcos en la lista de adyacencia correspondiente en el mapa vertices. Esto se debe a que se debe buscar el arco en la lista de adyacencia correspondiente en el mapa vertices.
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        LinkedList<Arco<? extends T>> arcosDeV1 = vertices.get(verticeId1);
        if (arcosDeV1 != null) {
            for (Arco<? extends T> i : arcosDeV1) {
                if (i.getVerticeDestino() == verticeId2) {
                    return (Arco<T>) i;
                }
            }
        }
        return null;
    }

    @Override//cantidadVertices(): la complejidad temporal es O(1), ya que se usa el método size() en el mapa vertices.
    public int cantidadVertices() {
        return vertices.size();
    }


    @Override//cantidadArcos(): la complejidad temporal es O(N), donde N es el número de arcos en el grafo. Esto se debe a que se debe contar cada arco en cada lista de adyacencia en el mapa vertices.
    public int cantidadArcos() {
        int cantidad = 0;
        for (Map.Entry<Integer, LinkedList<Arco<? extends T>>> entry : vertices.entrySet()) {
            cantidad += entry.getValue().size();
        }
        return cantidad;
    }

    @Override//obtenerVertices(): la complejidad temporal es O(N), donde N es el número de vértices en el grafo. Esto se debe a que se debe iterar por cada entrada en el mapa vertices y devolver la clave correspondiente.
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override//obtenerAdyacentes(int verticeId):O(m), donde m es el número de arcos que salen del vértice dado.
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        List<Arco<? extends T>> arcos = vertices.get(verticeId);
        List<Integer> adyacentes = new ArrayList<>();
        for (Arco<? extends T> arco : arcos) {
            adyacentes.add(arco.getVerticeDestino());
        }
        return adyacentes.iterator();
    }

    @Override//obtenerArcos(): O(m), donde m es el número total de arcos en el grafo.
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<? extends T>> arcosResult = new LinkedList<>();
        for (Map.Entry<Integer, LinkedList<Arco<? extends T>>> entry : vertices.entrySet()) {
            arcosResult.addAll(entry.getValue());
        }
        return (Iterator<Arco<T>>) new ArrayList<T>((Collection<? extends T>) arcosResult).iterator();
    }

    @Override//obtenerArcos(int verticeId): O(m), donde m es el número de arcos que salen del vértice dado.
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        LinkedList<Arco<? extends T>> arcosVertice = new LinkedList<>(vertices.get(verticeId));
//        return arcosVertice.iterator();
        return (Iterator<Arco<T>>) new ArrayList<T>((Collection<? extends T>) arcosVertice).iterator();
    }
}
