package poo.mensajes;
import java.util.StringJoiner;

public class MensajeActualizacion extends Mensaje{

    public MensajeActualizacion(int identificador) {
        super(identificador);
    }
    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        return sj.toString();
    }

    public final String getTipo() {
        return "actualizacion";
    }
}