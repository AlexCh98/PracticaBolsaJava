package poo.mensajes;

import java.util.StringJoiner;

public class MensajeCompra extends Mensaje {
    protected double dinero;
    protected String nombreEmpresa;

    public MensajeCompra(int identificador, String nombreCliente, String nombreEmpresa, int dinero) {
        super(identificador, nombreCliente);
        this.nombreEmpresa = nombreEmpresa;
        this.dinero = dinero;
    }
    public MensajeCompra(int identificador,String nombreCliente){
        super(identificador,nombreCliente);
    }

    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(this.nombreEmpresa);
        sj.add(Double.toString(this.dinero));
        return sj.toString();
    }

}
