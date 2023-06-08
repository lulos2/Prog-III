package practicoEspecialP2.services;

import practicoEspecial.Arco;
import practicoEspecial.Grafo;
import practicoEspecial.GrafoNoDirigido;
import practicoEspecialP2.CSVReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ShortNetBacktracking {

    protected Grafo<?> stations;
    protected Integer minimunDistanceForConectEveryStations;

    public ShortNetBacktracking(CSVReader csvReader) {
        this.stations = csvReader.getStations();
    }
    public List<List<Integer>> getShortestNet() {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> partialResult = new ArrayList<>();
        HashSet<Arco<?>> visitedArchs = new HashSet<>();
        HashSet<Integer> visitedVector = new HashSet<>();
        minimunDistanceForConectEveryStations = Integer.MAX_VALUE;
        for(Integer station: this.stations.getVertices()){
            searchShortPath(station, visitedArchs, visitedVector, 0, result, partialResult);
        }
        return result;
    }

    private void searchShortPath(Integer actualStation, HashSet<Arco<?>> visitedArch, HashSet<Integer> visitedStations, Integer actualDistance, List<List<Integer>> result, List<Integer> partialResult) {
        if(minimunDistanceForConectEveryStations < actualDistance)return;
        if(!visitedStations.contains(actualStation)) {
            visitedStations.add(actualStation);
        }

        Iterator<? extends Arco<?>> arches = this.stations.obtenerArcos(actualStation);

        while (arches.hasNext()) {
            Arco actualArch = arches.next();
            if(!visitedArch.contains(actualArch)){
                visitedArch.add(actualArch);
                partialResult.add(actualArch.getVerticeOrigen());
                partialResult.add(actualArch.getVerticeDestino());
                actualDistance = (actualDistance + (Integer)actualArch.getEtiqueta());
                if(visitedStations.size() == this.stations.cantidadVertices()){
                    if(minimunDistanceForConectEveryStations > actualDistance) {
                        minimunDistanceForConectEveryStations = actualDistance;
                        result.add(new ArrayList<>(partialResult));
                    }
                }
                searchShortPath(actualArch.getVerticeDestino(), visitedArch, visitedStations, actualDistance, result, partialResult);
                if(!partialResult.isEmpty()) {
                    partialResult.remove(partialResult.size() - 1);
                    partialResult.remove(partialResult.size() - 1);
                }
                visitedArch.remove(actualArch);
                actualDistance = (actualDistance - (Integer)actualArch.getEtiqueta());
            }
        }

    }
    public Integer getMinimunDistanceForConectEveryStations(){
        return this.minimunDistanceForConectEveryStations;
    }
}
