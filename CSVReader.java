package practicoEspecialP2;

import practicoEspecial.Grafo;
import practicoEspecial.GrafoNoDirigido;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {

    private String path;
    protected GrafoNoDirigido stations;



    public CSVReader(String path) {
        this.path = path;
        this.stations = new GrafoNoDirigido();
    }

    public void read() {
        ArrayList<String[]> lines = this.readContent();

        for (String[] line: lines) {
            Integer origen = Integer.parseInt(line[0].trim().substring(1));
            Integer destino = Integer.parseInt(line[1].trim().substring(1));
            Integer etiqueta = Integer.parseInt(line[2].trim());
            this.stations.agregarVertice(origen);
            this.stations.agregarVertice(destino);
            this.stations.agregarArco(origen, destino, etiqueta);
        }
    }

    private ArrayList<String[]> readContent() {
        ArrayList<String[]> lines = new ArrayList<>();
        File file = new File(this.path);
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        return lines;
    }

    public GrafoNoDirigido getStations() {
        return this.stations;
    }

}