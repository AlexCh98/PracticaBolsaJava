package poo.mensajes;

import java.util.ArrayList;
import java.util.StringJoiner;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private ArrayList<String> nombresEmpresas;
    private ArrayList<Double> valoresEmpresas;
    private ArrayList<Double> valoresPreviosEmpresas;


    public MensajeRespuestaActualizacion(int identificador, ArrayList<String> nombresEmpresas, ArrayList<Double> valoresEmpresas,ArrayList<Double> valoresPreviosEmpresas){
        super(identificador);
        this.nombresEmpresas = nombresEmpresas;
        this.valoresEmpresas = valoresEmpresas;
        this.valoresPreviosEmpresas = valoresPreviosEmpresas;
    }

    public String toString(){

        StringBuilder sbNombresEmpresas = new StringBuilder("");
        for(String s : this.nombresEmpresas){
            sbNombresEmpresas.append(s);
            sbNombresEmpresas.append(",");
        }
        sbNombresEmpresas.deleteCharAt(sbNombresEmpresas.lastIndexOf(","));
        StringBuilder sbValoresEmpresas = new StringBuilder("");
        for(Double d : this.valoresEmpresas){
            sbValoresEmpresas.append(d);
            sbValoresEmpresas.append(",");
        }
        sbValoresEmpresas.deleteCharAt(sbValoresEmpresas.lastIndexOf(","));
        StringBuilder sbValoresPreviosEmpresas = new StringBuilder("");
        for(Double d : this.valoresPreviosEmpresas){
            sbValoresPreviosEmpresas.append(d);
            sbValoresPreviosEmpresas.append(",");
        }
        sbValoresPreviosEmpresas.deleteCharAt(sbValoresPreviosEmpresas.lastIndexOf(","));
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(sbNombresEmpresas.toString());
        sj.add(sbValoresPreviosEmpresas.toString());
        sj.add(sbValoresEmpresas.toString());
        return sj.toString();
    }


    public ArrayList<String> getNombresEmpresas() {
        return this.nombresEmpresas;
    }

    public ArrayList<Double> getValoresEmpresas() {
        return this.valoresEmpresas;
    }
    public ArrayList<Double> getValoresPreviosEmpresas() {
        return this.valoresPreviosEmpresas;
    }
}