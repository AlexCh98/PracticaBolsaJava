package poo.Excepciones;

public class ArchivoNoEncontradoExcepcion extends Exception {
    public ArchivoNoEncontradoExcepcion(){
        super("No se ha encontrado el archivo");
    }
}
