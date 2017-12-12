package poo.mensajes;

import java.util.StringJoiner;

public class MensajeVenta extends Mensaje{
    protected int accionesVenta;
    protected String nombreEmpresa;
    final  private String tipo = "venta";

    public MensajeVenta(int identificador, String nombreCliente,  String nombreEmpresa,int numAcciones) {
        super(identificador, nombreCliente);
        this.accionesVenta = numAcciones;
        this.nombreEmpresa = nombreEmpresa;
    }

    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(this.nombreEmpresa);
        sj.add(Integer.toString(this.accionesVenta));
        return sj.toString();
    }
    public final String getTipo() {
        return tipo;
    }
}
