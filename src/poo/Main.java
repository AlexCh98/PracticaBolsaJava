package poo;

import poo.Excepciones.ClienteYaEstaExcepcion;
import poo.Excepciones.EmpresaRepetidaExcepcion;
import poo.banco.*;
import poo.bolsa.BolsaDeValores;
import poo.bolsa.Empresa;
import poo.general.*;
import poo.mensajes.MensajeActualizacion;
import poo.mensajes.MensajeRespuestaActualizacion;
import poo.mensajes.MensajeRespuestaCompra;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args)  {
        /*ArrayList<String> arrayListS = new ArrayList<>();
        arrayListS.add("A");
        arrayListS.add("B");
        arrayListS.add("C");
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(8.5);
        arrayList.add(5.5);
        arrayList.add(86.5);
        ArrayList<Double> arrayList1 = new ArrayList<>();
        arrayList1.add(18.5);
        arrayList1.add(15.5);
        arrayList1.add(6.5);
        MensajeRespuestaActualizacion mensajeRespuestaActualizacion = new MensajeRespuestaActualizacion(1,arrayListS,arrayList,arrayList1);
        System.out.println(mensajeRespuestaActualizacion.toString());
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
        System.out.println(EmpresaSalida);

        Empresa empresa1 = new Empresa("Repsol", 2000.0, 3000.0);
        Empresa empresa2 = new Empresa("Cepsa", 4000.0, 3000.0);
        Empresa empresa3 = new Empresa("Telefonica", 8000.0, 6000.0);
        Empresa empresa4 = new Empresa("BBVA", 2500.0, 1500.0);
        Empresa empresa5 = new Empresa("Santander", 1020.0, 2020.0);
        BolsaDeValores bolsa = new BolsaDeValores("Bolsa de Madrid", empresa1);
        try {
            bolsa.añadirEmpresa(empresa2);
            bolsa.añadirEmpresa(empresa3);
            bolsa.añadirEmpresa(empresa4);
            bolsa.añadirEmpresa(empresa5);
        } catch (EmpresaRepetidaExcepcion e) {
            //En teoria nunga deberia llegar aqui
            System.out.println("Error en la declaracion de datos de ejemplo");
        }
        AgenteDeInversiones broker = new AgenteDeInversiones(bolsa);
        GestorInversiones gestorInversiones = new GestorInversiones("Alex", "34568798K");
        PaqueteDeAcciones paqueteDeAcciones = new PaqueteDeAcciones("Repsol",4,2000.3);
        Cliente cliente1 = new ClientePremium("Jose", "53956431H", 200000.24, gestorInversiones);
        Cliente cliente2 = new Cliente("Ivan", "64967532D", 300000.0);
        Cliente cliente3 = new Cliente("Alex", "47472532A", 250000.0);
        cliente3.anadirPaqueteDeAciones(paqueteDeAcciones);
        Cliente cliente4 = new Cliente("Lucas", "17802852H", 18000.53);
        Banco banco = new Banco("Banco de España",cliente1,broker);
        try {
            banco.anadirCliente(cliente2);
            banco.anadirCliente(cliente3);
            banco.anadirCliente(cliente4);
        } catch (ClienteYaEstaExcepcion e) {
            //En teoria nunca deberia llegar aqui
            System.out.println("Error en la declaracion de datos de ejemplo");
        }
        MensajeActualizacion mensajeActualizacion = new MensajeActualizacion(1);
        System.out.println(broker.elaborarMensajeRespuestaActualizacion(broker.ejecutarSolicitudActualizacion(mensajeActualizacion)));*/






        Simulador simulador = new Simulador();
        System.out.println("Fin de la ejecucion del programa");
    }

}
