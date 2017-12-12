package poo.general;

import org.omg.PortableInterceptor.INACTIVE;
import poo.Excepciones.FormatoNoValidoExcepcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

public class Utilidades {


    public static double numeroAleatorio(){
        return Math.random();
    }

    public static Object[] deserializar (String mensaje, int longitud,String tipo) throws FormatoNoValidoExcepcion {

        String[] fields = mensaje.split("\\|");


        Object[] camposMensaje = new Object[longitud];
        if (fields.length<longitud) throw new FormatoNoValidoExcepcion();
        int i=longitud;
        int d=0;
        try{
            camposMensaje[0] = Integer.parseInt(fields[0]);
            camposMensaje[1] = fields[1];
            camposMensaje[2] = fields[2];
            if (longitud==4){
                if (tipo=="Compra"){
                    camposMensaje[3]=Double.parseDouble(fields[3]);
                }else {
                    camposMensaje[3]=Integer.parseInt(fields[3]);
                }
            }
        } catch (NumberFormatException e){
            throw new FormatoNoValidoExcepcion();
        }
        return camposMensaje;
    }
    public static String toString (ArrayList<Object> array) {
        StringJoiner sj = new StringJoiner("|");
        Iterator<Object> itr = array.iterator();
        while (itr.hasNext()) {
            Object object = itr.next();
            if(object instanceof String){
                sj.add((String)object);
            }else if (object instanceof Integer){
                sj.add(Integer.toString((Integer)object));
            }else if (object instanceof Boolean){
                sj.add(Boolean.toString((Boolean)object));
            }else{
                sj.add(Double.toString((Double)object));
            }
        }
        return sj.toString();
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
