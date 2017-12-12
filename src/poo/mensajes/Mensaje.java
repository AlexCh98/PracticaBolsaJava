package poo.mensajes;

abstract public class Mensaje {
    protected int identificador;
    protected String nombreCliente;



    public Mensaje(int identificador, String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.identificador = identificador;
    }
    abstract public String toString();
    abstract public String getTipo();


}
