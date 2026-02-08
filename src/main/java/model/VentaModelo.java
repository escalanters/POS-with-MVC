package model;

import interfaces.IObservador;
import interfaces.ISujeto;
import interfaces.IVentaLectura;

import java.util.ArrayList;
import java.util.List;

public class VentaModelo implements IVentaLectura,ISujeto {
    private List<Producto> productos;
    private double ultimoCambio;

    private List<IObservador> observadores;

    public VentaModelo() {
        productos = new ArrayList<>();
        observadores = new ArrayList<>();
        this.ultimoCambio = 0.0;
    }

    // LOGICA DE NEGOCIO QUE LA VISTA NO PUEDE ACCEDER, ESTO GRACIAS A EL ISP

    public void agregarProducto(String nombre, int cantidad, double precio) throws Exception {
        if(cantidad<=0){
            throw new Exception("Tiene que agregar por lo menos 1 producto");
        }
        if(precio<=0){
            throw new Exception("El precio del producto no puede ser 0");
        }
        Producto producto = new Producto(nombre,cantidad,precio);
        productos.add(producto);
        notificarObservadores();
    }

    public void quitarProducto(int indice){
        if(indice >= 0 && indice < productos.size()){
            productos.remove(indice);
            notificarObservadores();
        }
    }

    public void finalizarVenta(double pagoCliente) throws Exception{
        if(productos.isEmpty()){throw new Exception("El carrito esta vacio, favor de agregar productos");}
        double total = getTotal();

        if(pagoCliente < total){
            throw new Exception("Pago insuficiente. Te faltan $" +  (total - pagoCliente));
        }

        this.ultimoCambio = pagoCliente - total;
        this.productos.clear();
        notificarObservadores();
    }

    // AQUI ESTA LA IMPLEMENTACION DEL ISP

    @Override
    public List<Producto> getListaProductos() {
        return new ArrayList<>(productos);
    }

    @Override
    public double getTotal() {
        double total = 0;
        for(Producto producto: productos){
            total += producto.getSubtotal();
        }
        return total;
    }

    @Override
    public double getCambio() {
        return ultimoCambio;
    }

    // OBSERVER

    @Override
    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void notificarObservadores() {
        for(IObservador observador: observadores){
            observador.actualizar();
        }
    }
}
