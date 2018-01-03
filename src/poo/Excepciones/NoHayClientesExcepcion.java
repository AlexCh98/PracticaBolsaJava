package poo.Excepciones;

public class NoHayClientesExcepcion extends  Exception {
    public NoHayClientesExcepcion() {
        super("No hay nigun cliente en el banco");
    }
}
