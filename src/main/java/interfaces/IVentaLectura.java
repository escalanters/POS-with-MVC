package interfaces;
import model.Producto;
import java.util.List;

/**
 * The interface Venta lectura.
 */
public interface IVentaLectura {
    /**
     * Gets lista productos.
     *
     * @return the lista productos
     */
    List<Producto> getListaProductos();

    /**
     * Gets total.
     *
     * @return the total
     */
    double getTotal();

    /**
     * Gets cambio.
     *
     * @return the cambio
     */
    double getCambio();
}
