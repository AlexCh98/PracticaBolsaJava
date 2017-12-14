package poo.general;

import poo.Excepciones.NoEsCadenaSinNumeros;
import poo.Excepciones.NoEsDniExcepcion;
import poo.Excepciones.NoEsDoubleExcepcion;
import poo.Excepciones.NoEsEnteroExcepcion;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
            throw new NoEsEnteroExcepcion();
        }
        return entero;
    }
    public double leerDouble () throws NoEsDoubleExcepcion {
        double doble = 0;
        try {
            doble = sc.nextDouble();
        } catch (InputMismatchException e) {
            sc.next();
            throw new NoEsDoubleExcepcion();
        }
        return doble;
    }

    public String leerCadenaSinNumeros() throws NoEsCadenaSinNumeros {
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

    public String leerDni() throws NoEsDniExcepcion{
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


    public String leerCadena() {
        return sc.nextLine();
    }


}
