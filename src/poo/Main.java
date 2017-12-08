package poo;

import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.Excepciones.EmpresaRepetidaExcepcion;
import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;

import poo.banco.AgenteDeInversionesCopia;
import poo.bolsa.BolsaDeValoresCopia;
import poo.bolsa.Empresa;
import poo.general.InterfazDeUsuario;
import poo.mensajes.MensajeCompra;
import poo.mensajes.MensajeVenta;

public class Main {

    public static void main(String[] args) throws EmpresaNoEncontradaExcepcion, FormatoNoValidoExcepcion, NoSePuedeComprarAccionesExcepcion, EmpresaRepetidaExcepcion {
        InterfazDeUsuario interfaz = new InterfazDeUsuario();
        Empresa repsol = new Empresa("Repsol",1.02);
        Empresa cepsa = new Empresa("Cepsa",3.541);
        BolsaDeValoresCopia bolsa = new BolsaDeValoresCopia("IBEX",repsol);
        bolsa.añadirEmpresa(cepsa);
        bolsa.imprimirEmpresas();
      /*bolsa.actualizarValoresAcciones();
        bolsa.imprimirEmpresas();*/

        /*try {
            bolsa.realizarOperacionCompra("5052|John Nash|Tesla|0003000");
        }catch(EmpresaNoEncontradaExcepcion e){e.printStackTrace();}
*/
        AgenteDeInversionesCopia broker= new AgenteDeInversionesCopia(bolsa);
        MensajeVenta mensaje4 = new MensajeVenta(1000,"Alex","Repsol",15);
        MensajeCompra mensaje = new MensajeCompra(5052,"Alex","Repsol",1000);
        MensajeCompra mensaje3 = new MensajeCompra(2500,"Alex","Cepsa",1250);
        MensajeCompra mensaje1 = new MensajeCompra(5020,"Marcos","Tesla",1052);
        MensajeCompra mensaje2= new MensajeCompra(6000,"Dani","Hirdrola",1012);

        broker.almacenarMensaje(mensaje);
        broker.almacenarMensaje(mensaje4);
        broker.almacenarMensaje(mensaje3);
        broker.almacenarMensaje(mensaje2);
        broker.empiezaTrabajar();
    }

}
