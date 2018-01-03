package poo.banco;

import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.mensajes.MensajeActualizacion;
import poo.mensajes.MensajeRespuestaActualizacion;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorInversiones  extends Persona{
    public GestorInversiones(String nombre, String dni) {
        super(nombre, dni);
    }

    public String SolicitarRecomendacion(int identificador, AgenteDeInversiones agenteDeInversiones) {
        MensajeActualizacion mensajeActualizacion = new MensajeActualizacion(identificador);
        MensajeRespuestaActualizacion mensajeRespuestaActualizacion = agenteDeInversiones.elaborarMensajeRespuestaActualizacion(
                agenteDeInversiones.ejecutarSolicitudActualizacion(mensajeActualizacion));
        ArrayList<String> nombresEmpresas = mensajeRespuestaActualizacion.getNombresEmpresas();
        ArrayList<Double> valoresEmpresas = mensajeRespuestaActualizacion.getValoresEmpresas();
        ArrayList<Double> valoresPreviosEmpresas = mensajeRespuestaActualizacion.getValoresPreviosEmpresas();
        Iterator<String> itrNombresEmpresas = nombresEmpresas.iterator();
        Iterator<Double> itrValoresEmpresas =  valoresEmpresas.iterator();
        Iterator<Double> itrValoresPreviosEmpresas =  valoresPreviosEmpresas.iterator();
        String EmpresaSalida = "";
        Double diferenciaMayor = 0.0;
        while(itrNombresEmpresas.hasNext() && itrValoresEmpresas.hasNext() && itrValoresPreviosEmpresas.hasNext()){
            String nombreEmpresa = itrNombresEmpresas.next();
            Double valorActual = itrValoresEmpresas.next();
            Double valorPrevio = itrValoresPreviosEmpresas.next();
            Double diferencia = Math.abs(valorActual - valorPrevio);
            if(diferencia > diferenciaMayor){
                diferenciaMayor = diferencia;
                EmpresaSalida = nombreEmpresa;
            }
        }
        return EmpresaSalida;
    }
}
