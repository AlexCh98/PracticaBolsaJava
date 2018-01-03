package poo.Excepciones;

public class ErrorSeguridadExcepcion extends Exception {
    public ErrorSeguridadExcepcion(){
        super("No tienes permisos");
    }
}
