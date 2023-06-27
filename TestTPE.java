package practicoEspecial;
import java.util.concurrent.Executors;
import practico4.ServicioCaminoMasLargo;
import practicoEspecial.*;

public class TestTPE {
    public static void main(String[] args) {

        Grafo<String> miGrafoParaCaminos = new GrafoDirigido<String>();
        Grafo<Character> miGrafoParaBFS = new GrafoDirigido<Character>();
        Grafo<Character> miGrafin = new GrafoDirigido<Character>();


        miGrafoParaCaminos.agregarVertice(1);
        miGrafoParaCaminos.agregarVertice(3);
        miGrafoParaCaminos.agregarVertice(2);
        miGrafoParaCaminos.agregarVertice(4);
        miGrafoParaCaminos.agregarVertice(5);
        miGrafoParaCaminos.agregarVertice(6);


        miGrafoParaCaminos.agregarArco(1,2,"sapo");
        miGrafoParaCaminos.agregarArco(2,1,"sapo");
        miGrafoParaCaminos.agregarArco(2,3,"sapo");
        miGrafoParaCaminos.agregarArco(1,4,"sapo");
        miGrafoParaCaminos.agregarArco(4,5,"sapo");
        miGrafoParaCaminos.agregarArco(5,6,"sapo");
        miGrafoParaCaminos.agregarArco(6,3,"sapo");
        miGrafoParaCaminos.agregarArco(1,3,"sapo");

        miGrafoParaCaminos.borrarVertice(2);

        miGrafoParaCaminos.borrarArco(1,100);
        System.out.println(miGrafoParaCaminos.cantidadArcos());
        miGrafoParaCaminos.obtenerArcos().forEachRemaining(System.out::println);


      /*  System.out.println("servicio DFS");
        ServicioDFS servicioDFS = new ServicioDFS(miGrafoParaCaminos);
        System.out.println(servicioDFS.dfsForest());

        System.out.println("servicio BFS");
        ServicioBFS servicioBFS = new ServicioBFS(miGrafoParaCaminos);
        System.out.println(servicioBFS.bfsForest());
        System.out.println("servicio caminos ");
        ServicioCaminos servicioCaminos = new ServicioCaminos(miGrafoParaCaminos,1, 3,3);
        System.out.println(servicioCaminos.caminos());

        miGrafoParaCaminos.hacerPesado();
        System.out.println("servicio caminos ejercicio 1");
        ServicioCaminoMasLargo servicioCaminoMasLargo = new ServicioCaminoMasLargo(miGrafoParaCaminos,1, 3);
        System.out.println(servicioCaminoMasLargo.lowestCostPath());
*/
    }
}
