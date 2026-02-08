package main;
import controller.VentaControlador;
import model.VentaModelo;
import view.VentaVista;

/**
 * The type Main.
 */
public class Main {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){
        VentaModelo modelo = new VentaModelo();
        VentaControlador controlador = new VentaControlador(modelo);
        VentaVista vista = new VentaVista(controlador);
        modelo.agregarObservador(vista);
        vista.setVisible(true);
    }
}
