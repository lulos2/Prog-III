package practicoEspecial.parte1;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    protected HashMap<Integer, LinkedList<practicoEspecial.parte1.Arco<? extends T>>> vertices;
    protected Integer cantArcos;

    public GrafoDirigido() {
        this.vertices = new HashMap<>();
        cantArcos = 0;
    }

    @Override
    public Set<Integer> getVertices(){
        return this.vertices.keySet();
    }
    @Override//agregarVertice(int verticeId): la complejidad temporal es O(1), ya que solo se agrega un nuevo elemento al mapa vertices.
    public void agregarVertice(int verticeId) {
        if (!this.vertices.containsKey(verticeId)) {
            this.vertices.put(verticeId, new LinkedList<>());
        }
    }

    @Override//borrarVertice(int verticeId): la complejidad temporal es O(N^2), porque en el peor de los casos donde el grafo es pesado y cada uno de los N vertices tiene N aristas
    public void borrarVertice(int verticeId) {
        for (Map.Entry<Integer, LinkedList<practicoEspecial.parte1.Arco<? extends T>>> entry : vertices.entrySet()) {
            if (entry.getValue().removeIf(x -> x.getVerticeDestino() == verticeId)) {
                cantArcos --;
            }
        }
        if(vertices.containsKey(verticeId)) {
            cantArcos -= vertices.get(verticeId).size();
        }
        vertices.remove(verticeId);
    }

    @Override//agregarArco(int verticeId1, int verticeId2, T etiqueta): la complejidad temporal es O(1), ya que solo se agrega un nuevo elemento a la lista de adyacencia correspondiente en el mapa vertices.
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(this.vertices.containsKey(verticeId1)&&this.vertices.containsKey(verticeId2)) {
            if(!this.existeArco(verticeId1,verticeId2)) {
                vertices.get(verticeId1).add((new practicoEspecial.parte1.Arco<>(verticeId1, verticeId2, etiqueta)));
                cantArcos++;
            }
        }
    }

    @Override//borrarArco(int verticeId1, int verticeId2): la complejidad temporal es O(N), donde N es el número de arcos en el grafo. Esto se debe a que se debe buscar el arco en la lista de adyacencia correspondiente en el mapa vertices.
    public void borrarArco(int verticeId1, int verticeId2) {
        if(this.vertices.containsKey(verticeId1)) {
           if (vertices.get(verticeId1).removeIf(x -> x.getVerticeDestino() == verticeId2)) {
               cantArcos--;
           }
        }
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
    public practicoEspecial.parte1.Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        LinkedList<practicoEspecial.parte1.Arco<? extends T>> arcosDeV1 = vertices.get(verticeId1);
        if (arcosDeV1 != null) {
            for (practicoEspecial.parte1.Arco<? extends T> i : arcosDeV1) {
                if (i.getVerticeDestino() == verticeId2) {
                    return (practicoEspecial.parte1.Arco<T>) i;
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
        return cantArcos;
    }

    @Override//obtenerVertices(): la complejidad temporal es O(N), donde N es el número de vértices en el grafo. Esto se debe a que se debe iterar por cada entrada en el mapa vertices y devolver la clave correspondiente.
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override//obtenerAdyacentes(int verticeId):O(m), donde m es el número de arcos que salen del vértice dado.
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        List<practicoEspecial.parte1.Arco<? extends T>> arcos = vertices.get(verticeId);
        List<Integer> adyacentes = new ArrayList<>();
        for (practicoEspecial.parte1.Arco<? extends T> arco : arcos) {
            adyacentes.add(arco.getVerticeDestino());
        }
        return adyacentes.iterator();
    }

    @Override//obtenerArcos(): O(m), donde m es el número total de arcos en el grafo.
    public Iterator<practicoEspecial.parte1.Arco<T>> obtenerArcos() {
        LinkedList<practicoEspecial.parte1.Arco<? extends T>> arcosResult = new LinkedList<>();
        for (Map.Entry<Integer, LinkedList<practicoEspecial.parte1.Arco<? extends T>>> entry : vertices.entrySet()) {
            arcosResult.addAll(entry.getValue());
        }
        return (Iterator<practicoEspecial.parte1.Arco<T>>) new ArrayList<T>((Collection<? extends T>) arcosResult).iterator();
    }

    @Override//obtenerArcos(int verticeId): O(m), donde m es el número de arcos que salen del vértice dado.
    public Iterator<practicoEspecial.parte1.Arco<T>> obtenerArcos(int verticeId) {
        LinkedList<practicoEspecial.parte1.Arco<? extends T>> arcosVertice = new LinkedList<>(vertices.get(verticeId));
        return (Iterator<practicoEspecial.parte1.Arco<T>>) new ArrayList<T>((Collection<? extends T>) arcosVertice).iterator();
    }

    @Override//metodo utilizado para testeos de eficiencia temporal
    public void hacerPesado() {
        for (Integer i : vertices.keySet()){
            for (Integer j : vertices.keySet()){
                if (!existeArco(j,i)){
                    this.agregarArco(j,i,(T)"pepe");
                }
            }
        }
    }

    @Override
    public ArrayList<practicoEspecial.parte1.Arco<T>> getOrderEdges(Integer vertice) {
        Iterator<practicoEspecial.parte1.Arco<T>> edges = obtenerArcos(vertice);
        ArrayList<practicoEspecial.parte1.Arco<T>> result = new ArrayList<>();
        while (edges.hasNext()) {
            practicoEspecial.parte1.Arco<T> actualEdge = edges.next();
            result.add(actualEdge);
        }
        Collections.sort(result,comparador);
        return result;
    }
    Comparator<practicoEspecial.parte1.Arco<?>> comparador = new Comparator<practicoEspecial.parte1.Arco<?>>() {
        @Override
        public int compare(practicoEspecial.parte1.Arco<?> arco1, practicoEspecial.parte1.Arco<?> arco2) {
            Integer etiqueta1 = (Integer) arco1.getEtiqueta();
            Integer etiqueta2 = (Integer) arco2.getEtiqueta();
            return etiqueta1.compareTo(etiqueta2);
        }
    };

    public ArrayList<practicoEspecial.parte1.Arco<T>> getAllEdges(){
        ArrayList<practicoEspecial.parte1.Arco<T>> arcos = new ArrayList<>();
        for (Iterator<? extends Arco<T>> it = obtenerArcos(); it.hasNext(); ) {
            arcos.add(it.next());
        }
        return arcos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Integer i : this.vertices.keySet()){
            sb.append(i);
            sb.append("-");
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
/*TODO funcionamiento:
-DONE     Al borrar un vertice que tiene arcos entrantes tira excepción.
 DONE   - El agregarArco no está chequeando que el arco no exista previamente, por lo que se permite crear arcos duplicados.
 DONE   - El borrarArco primero busca el arco, y despues lo borra (doble recorrido). Se podría usar el metodo ""removeIf"" de List para evitar el doble recorrido."
  TODO implementacion:
"-DONE    La cantidad de arcos la calculan cada vez que se las piden. Se podría haber llevado pre-calculada en una variable (tal y como hicieron con el size en la Lista vinculada que implementaron en el TP1.
*/