package practicoEspecialP2;

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

    public static class Tunnel<T> extends Arco<T>{


        public Tunnel(int verticeOrigen, int verticeDestino, T etiqueta) {
            super(verticeOrigen, verticeDestino, etiqueta);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Arco.Tunnel)) {
                return false;
            }

            Tunnel<?> other = (Tunnel<?>) obj;

            return (this.verticeOrigen == other.verticeOrigen && this.verticeDestino == other.verticeDestino)
                    || (this.verticeOrigen == other.verticeDestino && this.verticeDestino == other.verticeOrigen);
        }

    }
}

