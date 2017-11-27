package poo.general;

import poo.Excepciones.NoEsEnteroExcepcion;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Escaner {
    private Scanner sc;



    public Escaner (){
        this.sc = new Scanner(System.in);
        sc.useDelimiter("\\R");
    }

    public int leerEntero () throws NoEsEnteroExcepcion{
        int entero = 0;
        try {
            entero = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.next();
            throw new NoEsEnteroExcepcion("No es un entero");
        }
        return entero;
    }


}
