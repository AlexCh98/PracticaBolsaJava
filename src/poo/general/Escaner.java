package poo.general;

import poo.Excepciones.NoEsEnteroExcepcion;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Escaner {
    private InputStreamReader isr;
    private BufferedReader br;



    public Escaner (){
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
    }

    public int leerEntero () throws NoEsEnteroExcepcion, IOException{
        String cadena = null;
        int entero = 0;
        try {
            cadena = br.readLine();
            entero = Integer.parseInt(cadena);
        } catch (NumberFormatException e) {
            throw new NoEsEnteroExcepcion("No es un entero");
        } catch (IOException e) {
            throw  new IOException("Ha habido un error con la lectra de la entrada");
        } finally{
            br.mark(0);
            br.reset();
        }
        return entero;
    }


}
