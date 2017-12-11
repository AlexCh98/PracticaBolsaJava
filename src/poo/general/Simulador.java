package poo.general;

import poo.banco.Banco;
import poo.banco.Cliente;
import poo.bolsa.BolsaDeValores;
import poo.bolsa.Empresa;

public class Simulador {
    public Simulador() {
        InterfazDeUsuario interfazDeUsuario = new InterfazDeUsuario();
        Cliente primerCliente = new Cliente("Jose","53956431H", 3000);
        Empresa primeraEmpresa = new Empresa("Repsol");
        Banco banco = new Banco("Mi banco", primerCliente);
        BolsaDeValores bolsa = new BolsaDeValores("Mi bolsa", primeraEmpresa);
        switch (interfazDeUsuario.getOpcion()){
            case 1:
                //Imprimir estado de los clientes
                banco.imprimirClientes();
                break;
            case 2:
                //Imprimir estado de los clientes
                bolsa.imprimirEmpresas();
                break;
            case 3:
                //Añadir cliente
                //boolean sinError = false;
                //do{
                //      System.out.println("Introduzca datos del cliente");
                //      Escaner escaner = new Escaner();
                //      try{
                //          Cliente cliente = escaner.leerCliente();
                //          banco.añandirCliente(cliente);
                //          sinError = true;
                //      }catch(NoEsClienteExcepcion | ClienteYaEstaExcepcion e){
                //          System.out.println(e.getMessage());
                //      }
                // }while(sinError);
                break;
            case 4:
                //Eliminar cliente
                //boolean sinError = false;
                //do{
                //      System.out.println("Introduzca datos del cliente");
                //      Escaner escaner = new Escaner();
                //      try{
                //          Cliente cliente = escaner.leerCliente();
                //          banco.eliminar(cliente);
                //          sinError = true;
                //      }catch(NoEsClienteExcepcion | ClienteNoEncontradoExcepcion e){
                //          System.out.println(e.getMessage());
                //      }
                // }while(sinError);
                break;
            case 5:
                //Realizar copia de seguridad
                //boolean sinError = false;
                //do{
                //No se como hacer esto
                //while(sinError);
                break;
            case 6:
                //Restaurar copia de seguridad
                //boolean sinError = false;
                //do{
                //No se como hacer esto
                //while(sinError);
                break;
            case 7:
                //Mejorar cliente a premium
                //boolean sinError = false;
                //do{
                //      System.out.println("Introduzca datos del cliente");
                //      Escaner escaner = new Escaner();
                //      try{
                //          Cliente cliente = escaner.leerCliente();
                //          banco.subirClienteAPremium(cliente);
                //          sinErrror = true;
                //      }catch(NoEsClienteExcepcion | ClienteNoEncontradoExcepcion | ClienteYaEsPremiumExcepcion e){
                //          System.out.println(e.getMessage());
                //      }
                // }while(sinError);
                break;
            case 8:
                //Solicitar recomendacion de inversion
                ////do{
                //      System.out.println("Introduzca datos del cliente");
                //      Escaner escaner = new Escaner();
                //      try{
                //          Cliente cliente = escaner.leerCliente();
                //          banco.subirClienteAPremium(cliente);
                //          String nombreEmpresa = banco.solicitarRecomendacionInversion();
                //          System.out.println("Lo mas recomendable es que inviertas en " + nombreEmpresa);
                //          sinErrror = true;
                //      }catch(NoEsClienteExcepcion | ClienteNoEncontradoExcepcion | ClienteNoPremiumExcepcion e){
                //          System.out.println(e.getMessage());
                //      }
                // }while(sinError);
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            case 18:
                break;
            case 0:
                break;
        }
    }
}
