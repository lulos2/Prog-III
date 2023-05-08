package practicoEspecial;


import practico3.Arco;
import practico3.Grafo;

import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {

    protected LinkedList<Integer> vertices;
    protected LinkedList<Arco<T>> arcos;

    public GrafoDirigido() {
        this.vertices = new LinkedList<Integer>();
        this.arcos = new LinkedList<Arco<T>>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        this.vertices.add(verticeId);
    }

    @Override
    public void borrarVertice(int verticeId) {
        vertices.remove(verticeId);
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        this.arcos.add(new Arco<>(verticeId1,verticeId2,etiqueta));
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        this.arcos.remove(this.obtenerArco(verticeId1,verticeId2));
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.contains(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return this.arcos.contains(obtenerArco(verticeId1, verticeId2));
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        for(Arco a: arcos) {
            if(a.getVerticeOrigen() == verticeId1) {
                if (a.getVerticeDestino() == verticeId2) {
                    return a;
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
        return arcos.size();
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        LinkedList<Integer> adyacentes = new LinkedList<>();
        for (Arco<T> a : arcos) {
            if (a.getVerticeOrigen() == verticeId) {
                adyacentes.add(a.getVerticeDestino());
            }
        }
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> arcosResult = new LinkedList<>(arcos);
        return arcosResult.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        LinkedList<Arco<T>> arcosVertice = new LinkedList<Arco<T>>();
        for (Arco<T> a : arcos) {
            if (a.getVerticeOrigen() == verticeId) {
                arcosVertice.add(a);
            }
        }
        return arcosVertice.iterator();
    }

    public int obtenerVertice(int pos) {
        return this.vertices.get(pos);
    }
}