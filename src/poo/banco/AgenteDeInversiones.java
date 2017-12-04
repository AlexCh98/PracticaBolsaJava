package poo.banco;

import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;
import poo.bolsa.BolsaDeValores;
import poo.mensajes.*;

import java.util.ArrayList;

public class AgenteDeInversiones {
    private ArrayList<Mensaje> listaPeticiones;

    public AgenteDeInversiones(ArrayList<Mensaje> listaPeticiones) {
        this.listaPeticiones = listaPeticiones;
    }

    public void almacenarMensaje(Mensaje mensaje){
        this.listaPeticiones.add(mensaje);
    }


    public void empiezaTrabajar(BolsaDeValores bolsaValores)throws FormatoNoValidoExcepcion,EmpresaNoEncontradaExcepcion,NoSePuedeComprarAccionesExcepcion{
        ArrayList<Mensaje> peticiones = this.listaPeticiones;

        for (Mensaje peticion: peticiones){
           System.out.println(elaborarMensajeRespuesta(ejecutarSolicitud(peticion,bolsaValores)));
        }


    }


    public String ejecutarSolicitud(Mensaje peticion,BolsaDeValores bolsaValores)throws FormatoNoValidoExcepcion,EmpresaNoEncontradaExcepcion,NoSePuedeComprarAccionesExcepcion{
        if(peticion instanceof MensajeCompra) {
            MensajeCompra mensaje = (MensajeCompra) peticion;
            return bolsaValores.realizarOperacionCompra(mensaje.toString());

        }
        else if(peticion instanceof MensajeVenta) {
            MensajeVenta mensaje = (MensajeVenta) peticion;
            return bolsaValores.realizarOperacionVenta(mensaje.toString());
        }
        else {
            MensajeActualizacion mensaje = (MensajeActualizacion) peticion;
            return bolsaValores.realizarOperacionActualizacion(mensaje.toString());
        }


    }

    public MensajeRespuestaCompra elaborarMensajeRespuesta(String mensaje){
        String[] fields = mensaje.split("\\|");
        int identificador = Integer.parseInt(fields[0]);
        String nombreCliente = fields[1];
        boolean operacionRealizada = Boolean.parseBoolean(fields[2]);
        int numTitulosComprados = Integer.parseInt(fields[3]);
        double precioAccion=Double.parseDouble(fields[4]);
        double dineroSobrante=Double.parseDouble(fields[5]);
        MensajeRespuestaCompra mensajeRespuesta = null;
        mensajeRespuesta.elaborarMensajeRespuestaCompra(identificador,nombreCliente,operacionRealizada,numTitulosComprados,precioAccion,dineroSobrante);
        return mensajeRespuesta;
    }



}
