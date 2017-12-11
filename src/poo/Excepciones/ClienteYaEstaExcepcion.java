package poo.Excepciones;

public class ClienteYaEstaExcepcion extends Exception{
    public ClienteYaEstaExcepcion() {
        super("El cliente ya esta añadido este banco");
    }
}
