package poo.Excepciones;

public class NoSuficienteSaldoExcepcion extends  Exception {
    public NoSuficienteSaldoExcepcion() {
        super("El cliente no tiene sufficiente saldo");
    }
}
