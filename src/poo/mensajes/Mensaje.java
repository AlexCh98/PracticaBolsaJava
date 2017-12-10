package poo.mensajes;

public class Mensaje {
    protected int identificador;
    protected String nombreCliente;

    //Antes de METER LOS PARAMETROS AL MENSAJE HAY QUE COMPROBARLOS EN BANCO

    public Mensaje(int identificador, String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.identificador = identificador;
    }
}
