package poo.Excepciones;

public class ClienteNoEncontradoExcepcion extends Exception {
    public ClienteNoEncontradoExcepcion() {
        super("No se encuentra el cliente");
    }
}
