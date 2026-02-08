package interfaces;

/**
 * The interface Venta modelo.
 */
public interface IVentaModelo {

    /**
     * Agregar producto.
     *
     * @param nombre   the nombre
     * @param cantidad the cantidad
     * @param precio   the precio
     * @throws Exception the exception
     */
    void agregarProducto(String nombre, int cantidad, double precio) throws Exception;

    /**
     * Finalizar venta.
     *
     * @param pagoCliente the pago cliente
     * @throws Exception the exception
     */
    void finalizarVenta(double pagoCliente) throws Exception;

    /**
     * Quitar producto.
     *
     * @param indice the indice
     */
    void quitarProducto(int indice);
}
