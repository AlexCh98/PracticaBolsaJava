package poo.Excepciones;

public class NoSuficientesAccionesExcecion extends Exception {
    public NoSuficientesAccionesExcecion() {
        super("El cliente no tiene suficientes acciones");
    }

}

