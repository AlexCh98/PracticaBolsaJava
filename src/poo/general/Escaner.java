package poo.general;

import poo.Excepciones.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

class Escaner {
    private Scanner sc;



    Escaner(){
        this.sc = new Scanner(System.in);
        sc.useDelimiter("\\R");
    }

    int leerEntero() throws NoEsEnteroExcepcion{
        int entero;
        try {
            entero = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.next();
            throw new NoEsEnteroExcepcion();
        }
        return entero;
    }
    double leerDouble() throws NoEsDoubleExcepcion {
        double doble;
        try {
            doble = sc.nextDouble();
        } catch (InputMismatchException e) {
            sc.next();
            throw new NoEsDoubleExcepcion();
        }
        return doble;
    }

    String leerCadenaSinNumeros() throws NoEsCadenaSinNumeros {
        String cadena;
        cadena = sc.nextLine();
        try{
            if(!Pattern.matches("[a-zA-Z]+", cadena))throw new NoEsCadenaSinNumeros();
        }catch (NoEsCadenaSinNumeros e){
            sc.next();
            throw new NoEsCadenaSinNumeros();
        }
        return cadena;
    }

    String leerDni() throws NoEsDniExcepcion{
        String cadena;
        cadena = sc.nextLine();
        try{
            if(!Pattern.matches("[0-9]{8}+[A-Z]", cadena))throw new NoEsDniExcepcion();
        }catch (NoEsDniExcepcion e){
            sc.next();
            throw new NoEsDniExcepcion();
        }
        return cadena;
    }


    String leerCadena() throws CadenaVaciaExcepcion {
        String cadena = sc.nextLine();
        if (cadena.isEmpty()) throw new CadenaVaciaExcepcion();
        return cadena;
    }

    void leerParaContinuar() {
        sc.nextLine();
    }
}
