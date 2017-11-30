package poo.mensajes;

public class MensajeRespuestaCompra extends MensajeCompra {
    private boolean operacion;
    private double precioAccion;
    private double dineroSobrante;

    public MensajeRespuestaCompra(int identificador, String nombreCliente, String nombreEmpresa, int dinero, boolean operacion, double precioAccion, double dineroSobrante) {
        super(identificador, nombreCliente, nombreEmpresa, dinero);
        this.operacion = operacion;
        this.precioAccion = precioAccion;
        this.dineroSobrante = dineroSobrante;
    }

    /*public MensajeRespuestaCompra(int identificador, String nombreCliente, boolean operacion, double precioAccion, double dineroSobrante) {
        MensajeRespuestaCompra mensajeRespuestaCompra= (MensajeRespuestaCompra) new Mensaje(identificador, nombreCliente);
        this.operacion = operacion;
        this.precioAccion = precioAccion;
        this.dineroSobrante = dineroSobrante;
    }*/
}
