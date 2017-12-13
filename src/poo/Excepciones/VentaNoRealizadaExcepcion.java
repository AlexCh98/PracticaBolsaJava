package poo.Excepciones;

public class VentaNoRealizadaExcepcion extends Exception {
    public VentaNoRealizadaExcepcion() {
        super("La venta no se ha podido realizar");
    }
}
