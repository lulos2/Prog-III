package practicoEspecial;

public class Arco<T> {

    protected int verticeOrigen;
    protected int verticeDestino;
    protected T etiqueta;

    public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return "E" + verticeOrigen + "-" + "E" + verticeDestino;
    }



    public Arco<T> reverse() {
        return new Arco<>(this.verticeDestino, this.verticeOrigen, this.etiqueta);
    }


}

