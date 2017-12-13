package poo.bolsa;


import poo.Excepciones.*;
import poo.general.Utilidades;

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
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos);

            dos.writeUTF(this.nombreBolsa);
            for (Empresa e: this.listaEmpresas){
                dos.writeUTF(e.getNombreEmpresa());
                dos.writeDouble(e.getValorActual());
                dos.writeDouble(e.getValorPrevio());
            }
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restaurarCopiaSeguridad(){
        try{
            FileInputStream fis = new FileInputStream ("Bolsa.dat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);
            int max=this.listaEmpresas.size();
            this.listaEmpresas.clear();
            this.nombreBolsa= dis.readUTF();
            try{
                while(fis!=null){
                    this.listaEmpresas.add(new Empresa(dis.readUTF(),dis.readDouble(),dis.readDouble()));
                }
            }catch(IOException e){
                fis.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        Boolean operacion=false;
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
                    } catch (NoSePuedeComprarAccionesExcepcion e) {
                        //accionesCompradas = 0;
                        //dineroSobrante = 0;
                    }
                }catch (EmpresaNoEncontradaExcepcion e){
                    operacion = false;
                }finally {
                    array.add(accionesCompradas);
                    array.add(accionesCompradas!=0);
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
        String nombreCliente = null;
        String nombreEmpresa = null;


        if (fields.length<3) throw new FormatoNoValidoExcepcion();
        try{
            identificador = Integer.parseInt(fields[0]);
            nombreCliente = fields[1];
            nombreEmpresa = fields[2];
        } catch (NumberFormatException e){
            throw new FormatoNoValidoExcepcion();
        }
        Empresa empresa = buscarEmpresa(nombreEmpresa);

        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(identificador));
        sj.add(nombreCliente);
        sj.add(nombreEmpresa);
        sj.add(Double.toString(empresa.getValorActual()));
        return sj.toString();
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

    public String empresaMayorDiferenciaAcciones (){
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




}

