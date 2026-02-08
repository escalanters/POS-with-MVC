package controller;

import interfaces.IVentaModelo;
import model.VentaModelo;

import javax.swing.*;

public class VentaControlador {
    private final IVentaModelo ventaModelo;
    public VentaControlador(IVentaModelo ventaModelo) {
        this.ventaModelo = ventaModelo;
    }

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

    public void quitarProducto(int indiceSeleccionado) throws Exception {
        if(indiceSeleccionado < 0) {
            throw new Exception("Seleccione un producto de la tabla para quitarlo");
        }
        ventaModelo.quitarProducto(indiceSeleccionado);
    }

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
