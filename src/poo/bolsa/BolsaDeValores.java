package poo.bolsa;


import poo.Excepciones.*;
import poo.general.Utilidades;

import javax.swing.text.html.HTMLDocument;

import static poo.general.Utilidades.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;



public class BolsaDeValores {
    public String nombreBolsa;
    public ArrayList<Empresa> listaEmpresas;

    public BolsaDeValores(String nombre,Empresa empresa) {
        this.nombreBolsa = nombre;
        this.listaEmpresas = new ArrayList<>();
        this.listaEmpresas.add(empresa);
    }

    public void añadirEmpresa(Empresa empresa) throws EmpresaRepetidaExcepcion {
        if(this.listaEmpresas.contains(empresa)) throw new EmpresaRepetidaExcepcion();
        this.listaEmpresas.add(empresa);
    }

    public void eliminarEmpresa (Empresa empresa) throws EmpresaNoEncontradaExcepcion{
        if(!this.listaEmpresas.contains(empresa)) throw new EmpresaNoEncontradaExcepcion();
        this.listaEmpresas.remove(empresa);
    }

    public void imprimirEmpresas(){
        for (Empresa empresa: this.listaEmpresas){
            System.out.println(empresa);
        }
    }


    public void realizarCopiaSeguridad() {
        try {
            FileOutputStream fos = new FileOutputStream("Bolsa.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);


            oos.writeObject(this.nombreBolsa);
            oos.writeObject(this.listaEmpresas);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restaurarCopiaSeguridad(){
        try{
            FileInputStream fis = new FileInputStream ("Bolsa.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.listaEmpresas.clear();
            this.nombreBolsa= (String) ois.readObject();
            this.listaEmpresas = (ArrayList<Empresa>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*5052|John Nash|Tesla|0003000.00*/




    public String realizarOperacion (String mensaje,String tipo) {

        String[] fields = mensaje.split("\\|");
        int  identificador = Integer.parseInt(fields[0]);
        String   nombreCliente = fields[1];
        String  nombreEmpresa = fields[2];
        Empresa empresa = new Empresa("");
        ArrayList<Object> array = new ArrayList<>();
        Boolean operacion = false;
        array.add(identificador);
        array.add(nombreCliente);
        array.add(nombreEmpresa);

        switch (tipo){
            case "compra":{
                double dinero = Double.parseDouble(fields[3]);
                Object[] numTitulos;
                int accionesCompradas=0;
                double dineroSobrante=0;
                try{
                    empresa = buscarEmpresa(nombreEmpresa);
                    this.listaEmpresas.get(this.listaEmpresas.indexOf(empresa)).valorActualEmpresa(empresa.getValorActual()*(0.01+1));
                    try {
                        numTitulos = calcularNumTitulo(dinero,empresa.getValorActual());
                        accionesCompradas =(int) numTitulos[0];
                        dineroSobrante =(double) numTitulos[1];
                        operacion=true;
                    } catch (NoSePuedeComprarAccionesExcepcion e) {
                        //accionesCompradas = 0;
                        //dineroSobrante = 0;
                        //Operacion = false
                    }
                }catch (EmpresaNoEncontradaExcepcion e){
                    //peracion = false;
                }finally {
                    array.add(dinero);
                    array.add(accionesCompradas);
                    array.add(operacion);
                    array.add(empresa.getValorPrevio());
                    array.add(dineroSobrante);
                    return Utilidades.toString(array);
                }
            }default:{
                int numAccionesVenta =  Integer.parseInt(fields[3]);
                Double dineroRecibido=0.0;
                try{
                    empresa = buscarEmpresa(nombreEmpresa);
                    dineroRecibido =empresa.getValorActual()*numAccionesVenta;
                    operacion = true;
                }catch (EmpresaNoEncontradaExcepcion e){
                    operacion = false;
                }finally {
                    array.add(numAccionesVenta);
                    array.add(operacion);
                    array.add(dineroRecibido);
                    array.add(empresa.getValorActual());
                    return Utilidades.toString(array);
                }
            }
        }
    }





    public String realizarOperacionActualizacion(String mensaje) throws FormatoNoValidoExcepcion, EmpresaNoEncontradaExcepcion {
        String[] fields = mensaje.split("\\|");

        int identificador = 0;
        try{
            identificador = Integer.parseInt(fields[0]);
        } catch (NumberFormatException e){
            throw new FormatoNoValidoExcepcion();
        }
        ArrayList<Object> arrayDatosEmpresa = obtenerDatosEmpresas(this.listaEmpresas);

        ArrayList<Object> array = new ArrayList<>();
        array.add(identificador);
        array.add(arrayDatosEmpresa.get(0));
        array.add(arrayDatosEmpresa.get(1));
        array.add(arrayDatosEmpresa.get(2));
        return Utilidades.toString(array);
    }



    public Empresa buscarEmpresa(String nombreEmpresa) throws EmpresaNoEncontradaExcepcion {
        Iterator<Empresa> itr = this.listaEmpresas.iterator();
        boolean encontrada=false;
        Empresa empresa = null;
        while (!encontrada && itr.hasNext())    {
            empresa = itr.next();
            encontrada=empresa.getNombreEmpresa().equals(nombreEmpresa);
        }
        if (!encontrada) throw new EmpresaNoEncontradaExcepcion();
        return empresa;
    }

    public Object[] calcularNumTitulo(double dinero,double valorActual) throws NoSePuedeComprarAccionesExcepcion {

        double cociente=(dinero/valorActual);

        int numAcciones=(int) Math.floor(cociente);
        double restoAcciones=cociente-numAcciones;
        if(numAcciones==0) throw new NoSePuedeComprarAccionesExcepcion();
        Object[] accionesCompradas = new Object[2];
        accionesCompradas[0]=numAcciones;
        accionesCompradas[1]=redondearDecimales(restoAcciones*valorActual,2);
        return accionesCompradas;
    }






    public void actualizarValoresAcciones(){
        ArrayList<Empresa> empresas=this.listaEmpresas;
        for (Empresa empresa: empresas) {
            empresa.valorActualEmpresa(numeroAleatorio());
        }
    }

   /* public String empresaMayorDiferenciaAcciones (){
        ArrayList<Empresa> empresas=this.listaEmpresas;
        double diferencia=0;
        Empresa empresaMayorDiferencia = null;
        for (Empresa empresa: empresas){
            if(diferencia<empresa.diferenciaAcciones()){
                diferencia=empresa.diferenciaAcciones();
                empresaMayorDiferencia=empresa;
            }
        }
        return empresaMayorDiferencia.getNombreEmpresa();
    }
*/



}

