package poo.Excepciones;

public class NoSePuedeComprarAccionesExcepcion extends Exception {
    public NoSePuedeComprarAccionesExcepcion() {
        super("No puede comprar ninguna acciones de la empresa con el dinero facilitado");
    }
}
