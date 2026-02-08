package interfaces;

public interface ISujeto {
    void agregarObservador(IObservador observador);
    void notificarObservadores();
}
