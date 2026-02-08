package interfaces;

import model.Producto;

import java.util.List;

public interface IVentaLectura {
    List<Producto> getListaProductos();
    double getTotal();
    double getCambio();
}
