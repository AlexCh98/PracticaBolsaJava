package poo.mensajes;

public class MensajeCompra extends Mensaje {
    protected int dinero;
    protected String nombreEmpresa;

    public MensajeCompra(int identificador, String nombreCliente, String nombreEmpresa, int dinero) {
        super(identificador, nombreCliente);
        this.nombreEmpresa = nombreEmpresa;
        this.dinero = dinero;
    }
}
