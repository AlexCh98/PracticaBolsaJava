package poo;

import poo.Excepciones.*;

import poo.banco.AgenteDeInversionesCopia;
import poo.banco.Banco;
import poo.banco.Cliente;
import poo.bolsa.BolsaDeValoresCopia;
import poo.bolsa.Empresa;
import poo.general.InterfazDeUsuario;
import poo.mensajes.MensajeCompra;
import poo.mensajes.MensajeVenta;

public class Main {

    public static void main(String[] args) throws EmpresaNoEncontradaExcepcion, FormatoNoValidoExcepcion, NoSePuedeComprarAccionesExcepcion, EmpresaRepetidaExcepcion, ClienteYaEstaExcepcion {
        InterfazDeUsuario interfaz = new InterfazDeUsuario();
        Empresa repsol = new Empresa("Repsol",1.02);
        Cliente pepe = new Cliente("Pepe","12345678",5035.8);
       // Banco Santander = new Banco("Santander",pepe);
       // Santander.anadirCliente(pepe);
        Empresa cepsa = new Empresa("Cepsa",3.541,3.054);
        Empresa Iberdrola = new Empresa("Iberdrola",3.541);
        Empresa Caixa = new Empresa("Caixa",3.541);
        Empresa Santander = new Empresa("Santander",3.541);
        Empresa Grifol = new Empresa("Grifol",3.541);
        BolsaDeValoresCopia bolsa = new BolsaDeValoresCopia("IBEX",repsol);
       bolsa.añadirEmpresa(cepsa);
        bolsa.añadirEmpresa(Iberdrola);
        bolsa.añadirEmpresa(Caixa);
        bolsa.añadirEmpresa(Santander);
        bolsa.añadirEmpresa(Grifol);

        bolsa.imprimirEmpresas();

        bolsa.realizarCopiaSeguridad();
       // bolsa.restaurarCopiaSeguridad();
      /*bolsa.actualizarValoresAcciones();*/
        bolsa.imprimirEmpresas();

        /*try {
            bolsa.realizarOperacionCompra("5052|John Nash|Tesla|0003000");
        }catch(EmpresaNoEncontradaExcepcion e){e.printStackTrace();}*/

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
