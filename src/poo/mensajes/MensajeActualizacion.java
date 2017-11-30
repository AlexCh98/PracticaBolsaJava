package poo.mensajes;

public class MensajeActualizacion extends Mensaje{
    protected String nombreEmpresa;

    public MensajeActualizacion(int identificador, String nombreCliente, String nombreEmpresa) {
        super(identificador, nombreCliente);
        this.nombreEmpresa = nombreEmpresa;
    }
}
