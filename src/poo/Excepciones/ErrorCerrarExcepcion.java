package poo.Excepciones;

public class ErrorCerrarExcepcion extends Exception {
    public ErrorCerrarExcepcion (){
        super("Ha surgido un error al cerrar el fichero");
    }
}
