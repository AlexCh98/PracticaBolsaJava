package poo.mensajes;

public class MensajeRespuestaCompra extends MensajeCompra {
    private boolean operacion;
    private int accionesCompradas;
    private double precioAccion;
    private double dineroSobrante;

    public MensajeRespuestaCompra(int identificador, String nombreCliente, String nombreEmpresa, int dinero, boolean operacion, double precioAccion, double dineroSobrante) {
        super(identificador, nombreCliente, nombreEmpresa, dinero);
        this.operacion = operacion;
        this.precioAccion = precioAccion;
        this.dineroSobrante = dineroSobrante;
    }

   public void elaborarMensajeRespuestaCompra(int identificador, String nombreCliente, boolean operacion, int numAccionesCompradas,double precioAccion, double dineroSobrante) {
       this.identificador=identificador;
       this.nombreCliente=nombreCliente;
       this.operacion = operacion;
       this.accionesCompradas=numAccionesCompradas;
       this.precioAccion = precioAccion;
       this.dineroSobrante = dineroSobrante;
   }
}
