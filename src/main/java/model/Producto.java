package model;

public class Producto {
    private final String nombre;
    private final int cantidad;
    private final double precioUnitario;

    public Producto(String nombre, int cantidad, double precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    public String getNombre() {return nombre;}
    public int getCantidad() {return cantidad;}
    public double getPrecioUnitario() {return precioUnitario;}

    public double getSubtotal(){
        return cantidad*precioUnitario;
    }
    @Override
    public String toString() {
        return nombre;
    }

}
