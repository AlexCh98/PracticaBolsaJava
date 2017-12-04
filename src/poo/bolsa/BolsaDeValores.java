package poo.bolsa;


import poo.Excepciones.*;

import java.util.ArrayList;
import java.util.StringJoiner;

import static poo.general.Utilidades.numeroAleatorio;

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
        ArrayList<Empresa> empresas=this.listaEmpresas;
        for (Empresa empresa: empresas){
            System.out.println(empresa.toString());
        }
    }

    public void realizarCopiaSeguridad(){
        //HACER COPIA DE SEGURIDAD
    }

    public void restaurarCopiaSeguridad(){
        //HACER COPIA DE SEGURIDAD
    }
    /*5052|John Nash|Tesla|0003000.00*/
    public String realizarOperacionCompra (String mensaje) throws FormatoNoValidoExcepcion,EmpresaNoEncontradaExcepcion,NoSePuedeComprarAccionesExcepcion {
        String[] fields = mensaje.split("\\|"); // "99|entero"
                /*  fields[0] = "99";
                    fields[1] = "entero";
                 */
            int identificador = 0;
            String nombreEmpresa = null;
            double dinero = 0;
            String nombreCliente = null;
            if (fields.length<4) throw new FormatoNoValidoExcepcion();
            try{
                 identificador = Integer.parseInt(fields[0]);
                 nombreCliente = fields[1];
                 nombreEmpresa = fields[2];
                 dinero = (double) Integer.parseInt(fields[3]);
            } catch (NumberFormatException e){
                    throw new FormatoNoValidoExcepcion();
            }
            //CONTROLAMOS QUE EL DINERO NO SEA CERO EN EL BANCO
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
        String[] fields = mensaje.split("\\|"); // "99|entero"
                /*  fields[0] = "99";
                    fields[1] = "entero";
                 */
        int identificador = 0;
        String nombreEmpresa = null;
        int numAccionesVenta = 0;
        String nombreCliente = null;
        if (fields.length<4) throw new FormatoNoValidoExcepcion();
        try{
            identificador = Integer.parseInt(fields[0]);
            nombreCliente = fields[1];
            nombreEmpresa = fields[2];
            numAccionesVenta =  Integer.parseInt(fields[3]);
        } catch (NumberFormatException e){
            throw new FormatoNoValidoExcepcion();
        }
        //CONTROLAMOS QUE EL DINERO NO SEA CERO EN EL BANCO
        Empresa empresa = buscarEmpresa(nombreEmpresa);
        Object[] numTitulos = calcularNumTitulo(numAccionesVenta,empresa.getValorActual());
            /*Controlar que te devuelve la cadena bien hecha*/
        int accionesCompradas=(int) numTitulos[0];
        this.listaEmpresas.get(this.listaEmpresas.indexOf(empresa)).valorActualEmpresa(empresa.getValorActual()-(empresa.getValorActual()*0.01));
        return new String(identificador+"|"+nombreCliente+"|"+(accionesCompradas!=0)+"|"+numTitulos[0]+"|"+empresa.getValorActual()+"|"+numTitulos[1]);


    }



    public String realizarOperacionActualizacion(String mensaje){
        return mensaje;

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
                    accionesCompradas[1]=restoAcciones*valorActual;
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

