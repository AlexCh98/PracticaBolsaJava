package poo.general;

import poo.Excepciones.NoEsEnteroExcepcion;

import java.io.IOException;
import java.util.Scanner;

public class Escaner {
    private Scanner sc;



    public Escaner (){
        this.sc = new Scanner(System.in);
    }

    public int leerEntero () throws NoEsEnteroExcepcion{ String cadena = null;
        int entero = 0;
        try {
            cadena = sc.nextLine();
            entero = Integer.parseInt(cadena);
        } catch (NumberFormatException e) {
            throw new NoEsEnteroExcepcion("No es un entero");
        }
        return entero;
    }


}
