package poo.mensajes;

import java.util.StringJoiner;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private boolean operacion;
    private double valorAccion;

    public MensajeRespuestaActualizacion(int identificador, String nombreCliente, String nombreEmpresa,boolean operacion, double valorAccion) {
        super(identificador, nombreCliente, nombreEmpresa);
        this.operacion = operacion;
        this.valorAccion = valorAccion;
    }
    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(this.nombreEmpresa);
        sj.add(Boolean.toString(this.operacion));
        sj.add(Double.toString(this.valorAccion));
        return sj.toString();
    }
}
