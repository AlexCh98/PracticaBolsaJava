package poo.mensajes;

public class MensajeRespuestaVenta extends MensajeVenta{
    private boolean operacion;
    private double precioAccion;
    private double dineroSobrante;

    public MensajeRespuestaVenta(int identificador, String nombreCliente, int numAcciones, String nombreEmpresa, boolean operacion, double precioAccion, double dineroSobrante) {
        super(identificador, nombreCliente, nombreEmpresa,numAcciones);
        this.operacion = operacion;
        this.precioAccion = precioAccion;
        this.dineroSobrante = dineroSobrante;
    }
}
