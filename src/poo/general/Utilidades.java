package poo.general;

import poo.Excepciones.FormatoNoValidoExcepcion;

public class Utilidades {


    public static double numeroAleatorio(){
        return Math.random();
    }

    public static Object[] deserializar (String mensaje, int longitud) throws FormatoNoValidoExcepcion {

        String[] fields = mensaje.split("\\|");


        Object[] camposMensaje = new Object[longitud];
        if (fields.length<longitud) throw new FormatoNoValidoExcepcion();
        int i=longitud;
        int d=0;
        try{
            while (i!=0){
                camposMensaje[d] = fields[d];
                i--;
                d++;
            }
        } catch (NumberFormatException e){
            throw new FormatoNoValidoExcepcion();
        }
        return camposMensaje;
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
}
