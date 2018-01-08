package poo.general;


import poo.bolsa.Empresa;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Utilidades {


    public static double numeroAleatorio(){
        return redondearDecimales(Math.random()*10000 , 0);
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
    public static ArrayList<String> pasarStringArray(String[] cadena){
        int contadorNombres = cadena.length;
        int i =0;
        ArrayList<String> arrayNombres = new ArrayList<>();
        while (i<contadorNombres){
            arrayNombres.add(cadena[i]);
            i++;
        }
        return arrayNombres;
    }
    public static ArrayList<Double> pasarStringArrayDouble(String[] cadena){
        int contadorValores = cadena.length;
        int i =0;
        ArrayList<Double> arrayValores = new ArrayList<>();
        while (i<contadorValores){
            arrayValores.add(Double.parseDouble(cadena[i]));
            i++;
        }
        return arrayValores;
    }
}
