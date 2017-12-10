package poo.bolsa;


import poo.Excepciones.EmpresaNoEncontradaExcepcion;
import poo.Excepciones.EmpresaRepetidaExcepcion;
import poo.Excepciones.FormatoNoValidoExcepcion;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;

import java.io.*;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.StringJoiner;

import static poo.general.Utilidades.numeroAleatorio;
import static poo.general.Utilidades.deserializar;
import static poo.general.Utilidades.redondearDecimales;


public class BolsaDeValoresCopia {
    public String nombreBolsa;
    public ArrayList<Empresa> listaEmpresas;

    public BolsaDeValoresCopia(String nombre, Empresa empresa) {
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
        /*try
        {
            FileOutputStream fos = new FileOutputStream("Bolsa.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // se escriben dos objetos de la clase Persona
            for (Empresa e: this.listaEmpresas){
                oos.writeObject(e);
            }

            oos.close();
            System.out.println("finalizado");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("¡El fichero no existe!");
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }*/
        System.out.println("COPIA CON EXITOO");
    }
    public void restaurarCopiaSeguridad(){
        try{
            FileInputStream fis = new FileInputStream ("Bolsa.dat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);
            int max=this.listaEmpresas.size();
            for (int i=0;i<max;i++){
                this.listaEmpresas.remove(0);
            }
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
        /*{
            try {
                FileInputStream fis = new FileInputStream("Bolsa.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.nombreBolsa =  ois.readUTF();
                this.listaEmpresas.add((Empresa)ois.readObject());
                // se cierra el flujo de objetos objetoEntrada
                ois.close();
            } catch (FileNotFoundException e) {
                System.out.println("¡El fichero no existe!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            };*/
    }
    /*5052|John Nash|Tesla|0003000.00*/




    public String realizarOperacionCompra (String mensaje) throws FormatoNoValidoExcepcion,EmpresaNoEncontradaExcepcion,NoSePuedeComprarAccionesExcepcion {
        Object[] camposMensaje=deserializar(mensaje,4,"Compra");
        int identificador = (Integer) camposMensaje[0];
        String nombreCliente = (String) camposMensaje[1];
        String nombreEmpresa = (String) camposMensaje[2];
        double dinero = (Double) camposMensaje[3];



        Empresa empresa = buscarEmpresa(nombreEmpresa);
        Object[] numTitulos = calcularNumTitulo(dinero,empresa.getValorActual());
        /*Controlar que te devuelve la cadena bien hecha*/
            int accionesCompradas=(int) numTitulos[0];
            double dineroSobrante=(double) numTitulos[1];

            this.listaEmpresas.get(this.listaEmpresas.indexOf(empresa)).valorActualEmpresa((empresa.getValorActual()*0.01)+empresa.getValorActual());

            StringJoiner sj = new StringJoiner("|");
            sj.add(Integer.toString(identificador));
            sj.add(nombreCliente);
            sj.add(Boolean.toString(accionesCompradas!=0));
            sj.add(Integer.toString(accionesCompradas));
            sj.add(Double.toString(empresa.getValorActual()));
            sj.add(Double.toString(dineroSobrante));
            return sj.toString();



    }

    public String realizarOperacionVenta (String mensaje) throws FormatoNoValidoExcepcion,EmpresaNoEncontradaExcepcion,NoSePuedeComprarAccionesExcepcion {
        Object[] camposMensaje=deserializar(mensaje,4,"Venta");

            int identificador = (Integer) camposMensaje[0];
            String nombreCliente = (String) camposMensaje[1];
            String nombreEmpresa = (String) camposMensaje[2];
            int numAccionesVenta = (Integer) camposMensaje[3];


        Empresa empresa = buscarEmpresa(nombreEmpresa);
        Double dineroRecibido =empresa.getValorActual()*numAccionesVenta;
        /*Controlar que te devuelve la cadena bien hecha*/
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(identificador));
        sj.add(nombreCliente);
        sj.add("true");
        sj.add(Double.toString(dineroRecibido));
        sj.add(Double.toString(empresa.getValorActual()));
        return sj.toString();


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
                boolean encontrada=false;
                int i=0;
                while (!encontrada && i<this.listaEmpresas.size())    {
                    encontrada=this.listaEmpresas.get(i).getNombreEmpresa().equals(nombreEmpresa);
                    i++;
                }
                if (!encontrada) throw new EmpresaNoEncontradaExcepcion();
                return this.listaEmpresas.get(i-1);
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

