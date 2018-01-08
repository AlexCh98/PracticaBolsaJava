package poo.banco;

import poo.Excepciones.ClienteNoEncontradoExcepcion;
import poo.Excepciones.CompraNoRealizadaExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;
import poo.Excepciones.VentaNoRealizadaExcepcion;
import poo.bolsa.BolsaDeValores;
import poo.general.Utilidades;
import poo.mensajes.Mensaje;
import poo.mensajes.MensajeRespuestaActualizacion;
import poo.mensajes.MensajeRespuestaCompra;
import poo.mensajes.MensajeRespuestaVenta;

import java.util.ArrayList;


public class AgenteDeInversiones {
    private BolsaDeValores bolsa;
    private ArrayList<Mensaje> listaPeticiones;

    public AgenteDeInversiones(BolsaDeValores bolsa) {
        this.bolsa=bolsa;
        this.listaPeticiones = new ArrayList<>();
    }


    void almacenarMensaje(Mensaje mensaje){
        this.listaPeticiones.add(mensaje);
    }


    public void empiezaTrabajar(Banco banco) throws NoSePuedeComprarAccionesExcepcion, ClienteNoEncontradoExcepcion, VentaNoRealizadaExcepcion, CompraNoRealizadaExcepcion {
        ArrayList<Mensaje> peticiones = this.listaPeticiones;

        for (Mensaje peticion: peticiones){
            if (peticion.getTipo().equals("actualizacion")){
                banco.actualizarCliente(elaborarMensajeRespuestaActualizacion(ejecutarSolicitudActualizacion(peticion)));
            }else{
                try{
                    banco.actualizarCliente(elaborarMensajeRespuestaCompraVenta(ejecutarSolicitud(peticion)));
                }catch (VentaNoRealizadaExcepcion | CompraNoRealizadaExcepcion e){
                    this.listaPeticiones.remove(peticion);
                    System.out.println("Error Al realizar la operación con identificador: "+ peticion.getIdentificador()+", del tipo: "+peticion.getTipo());
                }
            }
        }
        this.listaPeticiones.clear();
        System.out.println("" +
                "HE TERMINADO DE TRABAJAR!");
    }


    private String ejecutarSolicitud(Mensaje peticion){
        return this.bolsa.realizarOperacion(peticion.toString(),peticion.getTipo());
    }

    String ejecutarSolicitudActualizacion(Mensaje peticion) {
            return this.bolsa.realizarOperacionActualizacion(peticion.toString());
    }

    MensajeRespuestaActualizacion elaborarMensajeRespuestaActualizacion(String mensaje){
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

    private Mensaje elaborarMensajeRespuestaCompraVenta(String mensaje) {

        String[] fields = mensaje.split("\\|");
        Boolean realizadaoperacion;
        Double dineroAccion;
        Double dineroSobranteDevuelto;
        int identificador = Integer.parseInt(fields[0]);
        String nombreCliente = fields[1];
        String nombreEmpresa = fields[2];
        if (fields.length == 8) {//MensajeRespuestaCompra
            Double dineroTotal = Double.parseDouble(fields[3]);
            realizadaoperacion = Boolean.parseBoolean(fields[5]);
            int numAccionesCompradas = Integer.parseInt(fields[4]);
            dineroAccion = Double.parseDouble(fields[6]);
            dineroSobranteDevuelto = Double.parseDouble(fields[7]);
            return new MensajeRespuestaCompra(identificador, nombreCliente, nombreEmpresa, dineroTotal, realizadaoperacion, numAccionesCompradas, dineroAccion, dineroSobranteDevuelto);
        } else {
            int numAcciones = Integer.parseInt(fields[3]);
            realizadaoperacion = Boolean.parseBoolean(fields[4]);
            dineroSobranteDevuelto = Double.parseDouble(fields[5]);
            dineroAccion = Double.parseDouble(fields[6]);

            return new MensajeRespuestaVenta(identificador, nombreCliente, nombreEmpresa, numAcciones, realizadaoperacion, dineroSobranteDevuelto, dineroAccion);
        }
    }

    public void imprimirPeticionesPendientes(){
        if (this.listaPeticiones.size()==0) System.out.println("No hay peticiones Pendientes");
        for(Mensaje m : this.listaPeticiones){
            System.out.println(m.toString());
        }

    }

}
