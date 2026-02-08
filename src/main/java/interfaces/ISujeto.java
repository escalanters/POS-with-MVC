package interfaces;

/**
 * The interface Sujeto.
 */
public interface ISujeto {
    /**
     * Agregar observador.
     *
     * @param observador the observador
     */
    void agregarObservador(IObservador observador);

    /**
     * Notificar observadores.
     */
    void notificarObservadores();
}
