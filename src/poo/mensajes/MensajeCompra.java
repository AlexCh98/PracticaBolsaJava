
package poo.mensajes;

import java.util.StringJoiner;

public class MensajeCompra extends Mensaje {
    protected String nombreCliente;
    protected double dinero;
    protected String nombreEmpresa;

    public MensajeCompra(int identificador, String nombreCliente, String nombreEmpresa, double dinero) {
        super(identificador);
        this.nombreEmpresa = nombreEmpresa;
        this.dinero = dinero;
    }


    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(this.nombreEmpresa);
        sj.add(Double.toString(this.dinero));
        return sj.toString();
    }
    public final String getTipo() {
        return "compra";
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getDinero() {
        return dinero;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
}