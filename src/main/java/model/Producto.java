package model;

/**
 * The type Producto.
 */
public class Producto {
    private final String nombre;
    private final int cantidad;
    private final double precioUnitario;

    /**
     * Instantiates a new Producto.
     *
     * @param nombre         the nombre
     * @param cantidad       the cantidad
     * @param precioUnitario the precio unitario
     */
    public Producto(String nombre, int cantidad, double precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {return nombre;}

    /**
     * Gets cantidad.
     *
     * @return the cantidad
     */
    public int getCantidad() {return cantidad;}

    /**
     * Gets precio unitario.
     *
     * @return the precio unitario
     */
    public double getPrecioUnitario() {return precioUnitario;}

    /**
     * Get subtotal double.
     *
     * @return the double
     */
    public double getSubtotal(){
        return cantidad*precioUnitario;
    }
    @Override
    public String toString() {
        return nombre;
    }
}
