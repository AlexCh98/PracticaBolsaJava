package poo.general;


import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.bolsa.Empresa;

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
        for (Object object : array) {
            if (object instanceof String) {
                sj.add((String) object);
            } else if (object instanceof Integer) {
                sj.add(Integer.toString((Integer) object));
            } else if (object instanceof Boolean) {
                sj.add(Boolean.toString((Boolean) object));
            } else if (object instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) object;
                StringBuilder sb = new StringBuilder("");
                if (arrayList.get(0) instanceof String) {
                    for (Object o : arrayList) {
                        sb.append((String)o);
                        sb.append(",");
                    }
                    sb.deleteCharAt(sb.lastIndexOf(","));
                } else if (arrayList.get(0) instanceof Double) {
                    for (Object o : arrayList) {
                        sb.append(Double.toString((Double) o));
                        sb.append(",");
                    }
                    sb.deleteCharAt(sb.lastIndexOf(","));
                }
                sj.add(sb.toString());
            } else {
                sj.add(Double.toString((Double) object));
            }
        }
        return sj.toString();
    }
    public static ArrayList<Object> obtenerDatosEmpresas(ArrayList<Empresa> array){
       ArrayList<String> arrayNombre = new ArrayList<>();
       ArrayList<Double> arrayValorActual = new ArrayList<>();
       ArrayList<Double> arrayValorPrevio = new ArrayList<>();
       for (Empresa e: array) {
           arrayNombre.add(e.getNombreEmpresa());
           arrayValorPrevio.add(e.getValorPrevio());
           arrayValorActual.add(e.getValorActual());
       }

       ArrayList<Object> arrayList = new ArrayList<>();
       arrayList.add(arrayNombre);
       arrayList.add(arrayValorPrevio);
       arrayList.add(arrayValorActual);
       return arrayList;

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
    public static ArrayList pasarStringArray(String[] cadena){
        int contadorNombres = cadena.length;
        int i =0;
        ArrayList<Object> arrayNombres = new ArrayList<>();
        while (i<contadorNombres){
            arrayNombres.add(cadena[i]);
            i++;
        }
        return arrayNombres;
    }
}
