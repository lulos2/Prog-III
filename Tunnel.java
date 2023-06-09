package practicoEspecialP2;
import practicoEspecial.*;
public class Tunnel<T> extends Arco<T>{


    public Tunnel(int verticeOrigen, int verticeDestino, T etiqueta) {
        super(verticeOrigen, verticeDestino, etiqueta);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Tunnel)) {
            return false;
        }

        Tunnel<?> other = (Tunnel<?>) obj;

        return (this.verticeOrigen == other.verticeOrigen && this.verticeDestino == other.verticeDestino)
                || (this.verticeOrigen == other.verticeDestino && this.verticeDestino == other.verticeOrigen);
    }

}
