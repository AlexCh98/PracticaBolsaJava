package poo;

import poo.bolsa.BolsaDeValores;
import poo.bolsa.Empresa;
import poo.general.InterfazDeUsuario;

public class Main {

    public static void main(String[] args) {
        InterfazDeUsuario interfaz = new InterfazDeUsuario();
        Empresa repsol = new Empresa("Repsol",1.02);
        BolsaDeValores bolsa = new BolsaDeValores("IBEX");
        bolsa.añadirEmpresa(repsol);
        bolsa.imprimirEmpresas();
        bolsa.actualizarValoresAcciones();
        bolsa.imprimirEmpresas();
    }
}
