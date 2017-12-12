package poo.Excepciones;

public class NoEsDniExcepcion extends Exception {
    public NoEsDniExcepcion(){
        super("DNI con formato erroneo");
    }
}
