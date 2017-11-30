package poo.mensajes;

public class MensajeVenta extends Mensaje{
    protected int dinero;
    protected String nombreEmpresa;

    public MensajeVenta(int identificador, String nombreCliente, int dinero, String nombreEmpresa) {
        super(identificador, nombreCliente);
        this.dinero = dinero;
        this.nombreEmpresa = nombreEmpresa;
    }
}
