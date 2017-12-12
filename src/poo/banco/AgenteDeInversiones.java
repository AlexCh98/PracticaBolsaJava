package poo.banco;

import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;
import poo.bolsa.BolsaDeValores;
import poo.bolsa.BolsaDeValoresCopia;
import poo.mensajes.*;

import java.util.ArrayList;

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
            //banco.actualizarCliente(elaborarMensajeRespuesta(ejecutarSolicitud(peticion)));
            if (peticion.getTipo().equals("actualizacion")){
                elaborarMensajeRespuesta(ejecutarSolicitudActualizacion(peticion));

            }else{
                System.out.println(elaborarMensajeRespuesta(ejecutarSolicitud(peticion)));
            }
        }

        System.out.println("HE TERMINADO DE TRABAJAR!");
    }


    public String ejecutarSolicitud(Mensaje peticion){
        return this.bolsa.realizarOperacion(peticion.toString(),peticion.getTipo());

        /*
        if(peticion. instanceof MensajeCompra) {
            MensajeCompra mensaje = (MensajeCompra) peticion;
            return this.bolsa.realizarOperacionCompra(mensaje.toString());

        }
        else if(peticion instanceof MensajeVenta) {
            MensajeVenta mensaje = (MensajeVenta) peticion;
            return this.bolsa.realizarOperacionVenta(mensaje.toString());
        }
        else {
            MensajeActualizacion mensaje = (MensajeActualizacion) peticion;
            return this.bolsa.realizarOperacionActualizacion(mensaje.toString());
        }
*/

    }
    public String ejecutarSolicitudActualizacion(Mensaje peticion){

    }

    public Mensaje elaborarMensajeRespuesta(String mensaje) throws FormatoNoValidoExcepcion{
        String[] fields = mensaje.split("\\|");
        int identificador = 0;
        String nombreCliente=null;
        Boolean realizadaoperacion=false;
        int numAccionesCompraVenta = 0;
        String nombreEmpresa=null;
        double dineroAccion=0;
        double dineroSobranteDevuelto=0;

        if (fields.length==6){//MensajeRespuestaCompra
            try{
                identificador = Integer.parseInt(fields[0]);
                nombreCliente = fields[1];
                realizadaoperacion = Boolean.parseBoolean(fields[2]);
                numAccionesCompraVenta =  Integer.parseInt(fields[3]);
                dineroAccion=Double.parseDouble(fields[4]);
                dineroSobranteDevuelto=Double.parseDouble(fields[5]);
            } catch (NumberFormatException e){
                throw new FormatoNoValidoExcepcion();
            }

            MensajeRespuestaCompra mensajeRespuesta = new MensajeRespuestaCompra(identificador,nombreCliente,realizadaoperacion,numAccionesCompraVenta,dineroAccion,dineroSobranteDevuelto);
            return mensajeRespuesta;
        }
        else if(fields.length==5){//MensajeRespuestaCompra
            try{
                identificador = Integer.parseInt(fields[0]);
                nombreCliente = fields[1];
                realizadaoperacion = Boolean.parseBoolean(fields[2]);
                dineroSobranteDevuelto =  Double.parseDouble(fields[3]);
                dineroAccion=Double.parseDouble(fields[4]);

            } catch (NumberFormatException e){
                throw new FormatoNoValidoExcepcion();
            }

            Mensaje mensajeRespuesta = new MensajeRespuestaVenta(identificador,nombreCliente,realizadaoperacion,dineroSobranteDevuelto,dineroAccion);
            return mensajeRespuesta;
        }
        else {
            try{
                identificador = Integer.parseInt(fields[0]);
                nombreCliente = fields[1];
                nombreEmpresa = fields[2];
                dineroAccion =  Double.parseDouble(fields[3]);
            } catch (NumberFormatException e){
                throw new FormatoNoValidoExcepcion();
            }
            Mensaje mensajeRespuesta = new MensajeRespuestaActualizacion(identificador,nombreCliente,nombreEmpresa,dineroAccion);
            return mensajeRespuesta;
        }
    }



}
