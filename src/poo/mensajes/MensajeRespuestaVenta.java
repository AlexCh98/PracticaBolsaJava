package poo.mensajes;

import java.util.StringJoiner;

public class MensajeRespuestaVenta extends MensajeVenta{
    private boolean operacion;
    private double precioAccion;
    private double dineroDevuelto;

    public MensajeRespuestaVenta(int identificador, String nombreCliente,  String nombreEmpresa,int numAcciones, boolean operacion,double dineroDevuelto , double precioAccion) {
        super(identificador, nombreCliente,nombreEmpresa,numAcciones);
        this.operacion = operacion;
        this.dineroDevuelto = dineroDevuelto;
        this.precioAccion = precioAccion;

    }

    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(Boolean.toString(this.operacion));
        sj.add(Double.toString(this.dineroDevuelto));
        sj.add(Double.toString(this.precioAccion));
        return sj.toString();
    }

    public boolean isOperacion() {
        return operacion;
    }

    public double getPrecioAccion() {
        return precioAccion;
    }

    public double getDineroDevuelto() {
        return dineroDevuelto;
    }
}