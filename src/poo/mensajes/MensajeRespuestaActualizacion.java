package poo.mensajes;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private double valorAccion;

    public MensajeRespuestaActualizacion(int identificador, String nombreCliente, String nombreEmpresa, double valorAccion) {
        super(identificador, nombreCliente, nombreEmpresa);
        this.valorAccion = valorAccion;
    }
}
