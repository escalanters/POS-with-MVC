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
        try{
            if(nombre.isEmpty()) throw new Exception("El nombre es obligatorio");
            int cantidad = Integer.parseInt(cantidadStr);
            int precio = Integer.parseInt(precioStr);
            if(cantidad < 0) throw new Exception("La cantidad debe ser mayor a cero");
            if(precio < 0) throw new Exception("El precio debe ser mayor a cero");

            ventaModelo.agregarProducto(nombre, cantidad, precio);
            return true;
        }catch(NumberFormatException e){
            throw new Exception("La cantidad y el precio deben ser numeros validos");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public void quitarProducto(int indiceSeleccionado){
        if(indiceSeleccionado >= 0){
            ventaModelo.quitarProducto(indiceSeleccionado);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla para quitarlo");
        }
    }

    public void finalizarVenta(String pagoString){
        try{
            double pago = Double.parseDouble(pagoString);
            ventaModelo.finalizarVenta(pago);
            JOptionPane.showMessageDialog(null, "Venta finalizada con exito");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Favor de ingresar una cantidad valida");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo finalizar la compra");
        }
    }

//    public void mostrarRegistroVentas(){
//        JDialog registroVentas = new JDialog();
//        registroVentas.setModal(true);
//    }
}
