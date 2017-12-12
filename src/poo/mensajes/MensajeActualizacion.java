package poo.mensajes;

import java.util.StringJoiner;

public class MensajeActualizacion extends Mensaje{
    protected String nombreEmpresa;
    final  private String tipo = "actualizacion";

    public MensajeActualizacion(int identificador, String nombreCliente, String nombreEmpresa) {
        super(identificador, nombreCliente);
        this.nombreEmpresa = nombreEmpresa;
    }
    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(this.nombreEmpresa);
        return sj.toString();
    }

    public final String getTipo() {
        return tipo;
    }
}
