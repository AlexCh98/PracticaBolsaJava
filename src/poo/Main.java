package poo;

import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;
import poo.bolsa.BolsaDeValores;
import poo.bolsa.Empresa;
import poo.general.InterfazDeUsuario;
import poo.general.Utilidades;

public class Main {

    public static void main(String[] args) throws EmpresaNoEncontradaExcepcion,FormatoNoValidoExcepcion,NoSePuedeComprarAccionesExcepcion {
        /*InterfazDeUsuario interfaz = new InterfazDeUsuario();
        Empresa repsol = new Empresa("Repsol",1.02);
        BolsaDeValores bolsa = new BolsaDeValores("IBEX",repsol);

        bolsa.imprimirEmpresas();
      *//*bolsa.actualizarValoresAcciones();
        bolsa.imprimirEmpresas();*//*

        try {
            bolsa.realizarOperacionCompra("5052|John Nash|Tesla|0003000");
        }catch(EmpresaNoEncontradaExcepcion e){e.printStackTrace();}*/
        System.out.println(Utilidades.redondearDecimales(78.123456,2));
    }

}
