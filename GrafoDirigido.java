package practicoEspecial;


import practico3.Arco;
import practico3.Grafo;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    protected HashMap<Integer,LinkedList<Arco<T>>> vertices;

    public GrafoDirigido() {
        this.vertices = new HashMap<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        this.vertices.put(verticeId, new LinkedList<>());
    }

    @Override
    public void borrarVertice(int verticeId) {
        for(Map.Entry<Integer, LinkedList<Arco<T>>> entry : vertices.entrySet()){
            for(Arco<T> adyasente : entry.getValue())
                if(existeArco(adyasente.getVerticeOrigen(),verticeId)){
                    this.borrarArco(adyasente.getVerticeOrigen(),verticeId);
            }
        }
        vertices.remove(verticeId);
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        vertices.get(verticeId1).add((new Arco<>(verticeId1,verticeId2,etiqueta)));
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        vertices.get(verticeId1).remove(obtenerArco(verticeId1, verticeId2));
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return obtenerArco(verticeId1,verticeId2) != null;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        LinkedList<Arco<T>> arcosDeV1 = vertices.get(verticeId1);
        if(arcosDeV1!=null) {
            for (Arco<T> i : arcosDeV1) {
                if (i.getVerticeDestino() == verticeId2) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int cantidad = 0;
        for (Map.Entry<Integer, LinkedList<Arco<T>>> entry : vertices.entrySet()) {
            cantidad += entry.getValue().size();
        }
        return cantidad;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        List<Arco<T>> arcos = vertices.get(verticeId);
        List<Integer> adyacentes = new ArrayList<>();
        for (Arco<T> arco : arcos) {
            adyacentes.add(arco.getVerticeDestino());
        }
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> arcosResult = new LinkedList<>();
        for (Map.Entry<Integer, LinkedList<Arco<T>>> entry : vertices.entrySet()) {
            arcosResult.addAll(entry.getValue());
        }
        return arcosResult.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        LinkedList<Arco<T>> arcosVertice = new LinkedList<>(vertices.get(verticeId));
        return arcosVertice.iterator();
    }

    public Integer obtenerVerticeRandom() {
        Integer[] keys = vertices.keySet().stream().mapToInt(Integer::intValue).boxed().toArray(Integer[]::new);
        return keys[(int) (Math.random() * keys.length)];
    }

}