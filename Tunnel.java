package practicoEspecial;

public class Tunnel<T> extends Arco<T>{

    public Tunnel(int verticeOrigen, int verticeDestino, T etiqueta) {
        super(verticeOrigen, verticeDestino, etiqueta);
    }
    public Tunnel(Arco<T> tunnel) {
        super(tunnel.verticeOrigen, tunnel.verticeDestino, tunnel.etiqueta);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime * (verticeOrigen + verticeDestino);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Tunnel<?> other)) {
            return false;
        }

        return (this.verticeOrigen == other.verticeOrigen && this.verticeDestino == other.verticeDestino)
                || (this.verticeOrigen == other.verticeDestino && this.verticeDestino == other.verticeOrigen);
    }

}
