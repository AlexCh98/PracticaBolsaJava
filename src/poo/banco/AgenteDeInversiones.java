package poo.banco;

import poo.Excepciones.*;
import poo.bolsa.BolsaDeValores;
import poo.general.Utilidades;
import poo.mensajes.*;

import java.util.ArrayList;
import java.util.Collection;

public class AgenteDeInversiones {
    private BolsaDeValores bolsa;
    private ArrayList<Mensaje> listaPeticiones;

    public AgenteDeInversiones(BolsaDeValores bolsa) {
        this.bolsa=bolsa;
        this.listaPeticiones = new ArrayList<>();
    }

    public void almacenarMensaje(Mensaje mensaje){
        this.listaPeticiones.add(mensaje);
    }


    public void empiezaTrabajar(Banco banco) throws EmpresaNoEncontradaExcepcion, NoSePuedeComprarAccionesExcepcion, ClienteNoEncontradoExcepcion, VentaNoRealizadaExcepcion, CompraNoRealizadaExcepcion {
        ArrayList<Mensaje> peticiones = this.listaPeticiones;

        for (Mensaje peticion: peticiones){
            if (peticion.getTipo().equals("actualizacion")){
                banco.actualizarCliente(elaborarMensajeRespuestaActualizacion(ejecutarSolicitudActualizacion(peticion)));

            }else{
                banco.actualizarCliente(elaborarMensajeRespuestaCompraVenta(ejecutarSolicitud(peticion)));
            }
        }
        this.listaPeticiones.clear();
        System.out.println("" +
                "HE TERMINADO DE TRABAJAR!");
    }


    public String ejecutarSolicitud(Mensaje peticion){
        return this.bolsa.realizarOperacion(peticion.toString(),peticion.getTipo());
    }

    public String ejecutarSolicitudActualizacion(Mensaje peticion) {
            return this.bolsa.realizarOperacionActualizacion(peticion.toString());
    }

    public MensajeRespuestaActualizacion elaborarMensajeRespuestaActualizacion(String mensaje){
        String[] fields = mensaje.split("\\|");
        int identificador = Integer.parseInt(fields[0]);
        String[] cadenaNombresEmpresa = fields[1].split(",");
        String[] cadenaValorPrevioEmpresa = fields[2].split(",");
        String[] cadenaValorActualEmpresa = fields[3].split(",");

        ArrayList<String> arrayNombres = Utilidades.pasarStringArray(cadenaNombresEmpresa);
        ArrayList<Double> arrayValorPrevio =  Utilidades.pasarStringArrayDouble(cadenaValorPrevioEmpresa);
        ArrayList<Double> arrayValorActual =  Utilidades.pasarStringArrayDouble(cadenaValorActualEmpresa);
        return new MensajeRespuestaActualizacion(identificador, arrayNombres,arrayValorPrevio,arrayValorActual);
    }

    public Mensaje elaborarMensajeRespuestaCompraVenta(String mensaje) {

        String[] fields = mensaje.split("\\|");
        Boolean realizadaoperacion = false;
        Double dineroAccion = 0.0;
        Double dineroSobranteDevuelto = 0.0;
        int identificador = Integer.parseInt(fields[0]);
        String nombreCliente = fields[1];
        String nombreEmpresa = fields[2];
        if (fields.length == 8) {//MensajeRespuestaCompra
            Double dineroTotal = Double.parseDouble(fields[3]);
            realizadaoperacion = Boolean.parseBoolean(fields[5]);
            int numAccionesCompradas = Integer.parseInt(fields[4]);
            dineroAccion = Double.parseDouble(fields[6]);
            dineroSobranteDevuelto = Double.parseDouble(fields[7]);
            Mensaje mensajeRespuesta = new MensajeRespuestaCompra(identificador, nombreCliente, nombreEmpresa, dineroTotal, realizadaoperacion, numAccionesCompradas, dineroAccion, dineroSobranteDevuelto);
            return mensajeRespuesta;
        } else {
            int numAcciones = Integer.parseInt(fields[3]);
            realizadaoperacion = Boolean.parseBoolean(fields[4]);
            dineroSobranteDevuelto = Double.parseDouble(fields[5]);
            dineroAccion = Double.parseDouble(fields[6]);
            Mensaje mensajeRespuesta = new MensajeRespuestaVenta(identificador, nombreCliente, nombreEmpresa, numAcciones, realizadaoperacion, dineroSobranteDevuelto, dineroAccion);
            return mensajeRespuesta;
        }
    }

    public void imprimirPeticionesPendientes(){
        for(Mensaje m : this.listaPeticiones){
            System.out.println(m.toString());
        }
    }

}
