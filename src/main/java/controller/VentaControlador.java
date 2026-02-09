package controller;
import interfaces.IVentaModelo;

/**
 * The type Venta controlador.
 */
public class VentaControlador {
    private final IVentaModelo ventaModelo;

    /**
     * Instantiates a new Venta controlador.
     *
     * @param ventaModelo the venta modelo
     */
    public VentaControlador(IVentaModelo ventaModelo) {
        this.ventaModelo = ventaModelo;
    }

    /**
     * Agregar producto boolean.
     *
     * @param nombre      the nombre
     * @param cantidadStr the cantidad str
     * @param precioStr   the precio str
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean agregarProducto(String nombre, String cantidadStr, String precioStr) throws Exception {
        if(nombre.trim().isEmpty()) throw new Exception("El nombre es obligatorio");

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            double precio = Double.parseDouble(precioStr);
            ventaModelo.agregarProducto(nombre, cantidad, precio);
            return true;
        } catch (NumberFormatException e) {
            throw new Exception("La cantidad y el precio deben ser numeros validos");
        }
    }

    /**
     * Quitar producto.
     *
     * @param indiceSeleccionado the indice seleccionado
     * @throws Exception the exception
     */
    public void quitarProducto(int indiceSeleccionado) throws Exception {
        if(indiceSeleccionado < 0) {
            throw new Exception("Seleccione un producto de la tabla para quitarlo");
        }
        ventaModelo.quitarProducto(indiceSeleccionado);
    }
    
    public void editarProducto(int indiceSeleccionado, String nombre, String cantidadStr, String precioStr) throws Exception{
        if(nombre.trim().isEmpty()) throw new Exception("El nombre es obligatorio");
        if(cantidadStr.trim().isEmpty()) throw new Exception("La cantidad es obligatoria");
        if(precioStr.trim().isEmpty()) throw new Exception("El precio unitario es obligatorio");
        try{
            int cantidad= Integer.parseInt(cantidadStr);
            double precio= Double.parseDouble(precioStr);
            ventaModelo.editarProducto(indiceSeleccionado, nombre, cantidad, precio);
        } catch (NumberFormatException e) {
            throw new Exception("La cantidad y el precio deben ser números validos");
        }
    }

    /**
     * Finalizar venta.
     *
     * @param pagoString the pago string
     * @throws Exception the exception
     */
    public void finalizarVenta(String pagoString) throws Exception {
        try {
            double pago = Double.parseDouble(pagoString);
            ventaModelo.finalizarVenta(pago);
        } catch (NumberFormatException e) {
            throw new Exception("El monto de pago debe ser un número válido");
        }
    }

//    public void mostrarRegistroVentas(){
//        JDialog registroVentas = new JDialog();
//        registroVentas.setModal(true);
//    }
}
