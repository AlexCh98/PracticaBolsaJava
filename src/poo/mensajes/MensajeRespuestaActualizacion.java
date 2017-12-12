package poo.mensajes;

import java.util.ArrayList;
import java.util.StringJoiner;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private ArrayList<String> nombresEmpresas;
    private ArrayList<Double> valoresEmpresas;

    public MensajeRespuestaActualizacion(int identificador, ArrayList<String> nombresEmpresas, ArrayList<Double> valoresEmpresas){
        super(identificador);
        this.nombresEmpresas = nombresEmpresas;
        this.valoresEmpresas = valoresEmpresas;
    }

    public String toString(){

        StringBuilder sbNombresEmpresas = new StringBuilder("");
        for(String s : nombresEmpresas){
            sbNombresEmpresas.append(s);
            sbNombresEmpresas.append(",");
        }
        sbNombresEmpresas.deleteCharAt(sbNombresEmpresas.lastIndexOf(","));
        StringBuilder sbValoresEmpresas = new StringBuilder("");
        for(Double d : valoresEmpresas){
            sbValoresEmpresas.append(d);
            sbValoresEmpresas.append(",");
        }
        sbValoresEmpresas.deleteCharAt(sbValoresEmpresas.lastIndexOf(","));
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(sbNombresEmpresas.toString());
        sj.add(sbValoresEmpresas.toString());
        return sj.toString();
    }
}