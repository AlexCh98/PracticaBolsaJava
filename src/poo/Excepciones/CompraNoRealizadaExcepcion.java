package poo.Excepciones;

public class CompraNoRealizadaExcepcion extends Exception {
    public CompraNoRealizadaExcepcion() {
        super("La compra no se ha podido realizar");
    }
}
