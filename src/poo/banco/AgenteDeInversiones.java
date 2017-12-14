package poo.banco;

import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;
import poo.bolsa.BolsaDeValores;
import poo.general.Utilidades;
import poo.mensajes.*;

import java.util.ArrayList;
import java.util.Collection;

public class AgenteDeInversiones {
    private Banco banco;
    private BolsaDeValores bolsa;
    private ArrayList<Mensaje> listaPeticiones;

    public AgenteDeInversiones(Banco banco,BolsaDeValores bolsa) {
        this.banco=banco;
        this.bolsa=bolsa;
        this.listaPeticiones = new ArrayList<>();
    }
    public AgenteDeInversiones(BolsaDeValores bolsa) {
        this.bolsa=bolsa;
        this.listaPeticiones = new ArrayList<>();
    }
    public void almacenarMensaje(Mensaje mensaje){
        this.listaPeticiones.add(mensaje);
    }


    public void empiezaTrabajar()throws FormatoNoValidoExcepcion,EmpresaNoEncontradaExcepcion,NoSePuedeComprarAccionesExcepcion{
        ArrayList<Mensaje> peticiones = this.listaPeticiones;

        for (Mensaje peticion: peticiones){
            if (peticion.getTipo().equals("actualizacion")){
                System.out.println(elaborarMensajeRespuestaActualizacion(ejecutarSolicitudActualizacion(peticion)));

            }else{
                System.out.println(elaborarMensajeRespuestaCompraVenta(ejecutarSolicitud(peticion)));
            }
        }

        System.out.println("HE TERMINADO DE TRABAJAR!");
    }


    public String ejecutarSolicitud(Mensaje peticion){
        return this.bolsa.realizarOperacion(peticion.toString(),peticion.getTipo());
    }

    public String ejecutarSolicitudActualizacion(Mensaje peticion) throws FormatoNoValidoExcepcion, EmpresaNoEncontradaExcepcion {
            return this.bolsa.realizarOperacionActualizacion(peticion.toString());
    }
    public MensajeRespuestaActualizacion elaborarMensajeRespuestaActualizacion(String mensaje){
        String[] fields = mensaje.split("\\|");
        int identificador = Integer.parseInt(fields[0]);
        String[] cadenaNombresEmpresa = fields[1].split(",");
        String[] cadenaValorPrevioEmpresa = fields[2].split(",");
        String[] cadenaValorActualEmpresa = fields[3].split(",");

        ArrayList<String> arrayNombres = Utilidades.pasarStringArray(cadenaNombresEmpresa);
        ArrayList<Double> arrayValorPrevio = Utilidades.pasarStringArray(cadenaValorPrevioEmpresa);
        ArrayList<Double> arrayValorActual = Utilidades.pasarStringArray(cadenaValorActualEmpresa);
        return new MensajeRespuestaActualizacion(identificador, arrayNombres,arrayValorPrevio,arrayValorActual);
    }

    public Mensaje elaborarMensajeRespuestaCompraVenta(String mensaje) {

        String[] fields = mensaje.split("\\|");
        Boolean realizadaoperacion = false;
        double dineroAccion = 0;
        double dineroSobranteDevuelto = 0;
        int identificador = Integer.parseInt(fields[0]);
        String nombreCliente = fields[1];
        String nombreEmpresa = fields[2];
        if (fields.length == 8) {//MensajeRespuestaCompra
            double dineroTotal = Double.parseDouble(fields[3]);
            realizadaoperacion = Boolean.parseBoolean(fields[4]);
            int numAccionesCompradas = Integer.parseInt(fields[5]);
            dineroAccion = Double.parseDouble(fields[6]);
            dineroSobranteDevuelto = Double.parseDouble(fields[7]);
            MensajeRespuestaCompra mensajeRespuesta = new MensajeRespuestaCompra(identificador, nombreCliente, nombreEmpresa, dineroTotal, realizadaoperacion, numAccionesCompradas, dineroAccion, dineroSobranteDevuelto);
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

}
