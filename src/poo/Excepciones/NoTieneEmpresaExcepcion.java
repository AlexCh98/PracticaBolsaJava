package poo.Excepciones;

public class NoTieneEmpresaExcepcion extends Exception {
    public NoTieneEmpresaExcepcion() {
        super("El cliente no tiene acciones de esta empresa");
    }
}
