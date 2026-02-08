package main;

import controller.VentaControlador;
import model.VentaModelo;
import view.VentaVista;

public class Main {
    public static void main(String[] args){
        VentaModelo modelo = new VentaModelo();
        VentaControlador controlador = new VentaControlador(modelo);
        VentaVista vista = new VentaVista(modelo,controlador);
        modelo.agregarObservador(vista);
        vista.setVisible(true);

    }
}
