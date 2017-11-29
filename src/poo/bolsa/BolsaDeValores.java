package poo.bolsa;


import poo.Excepciones.EmpresaNoEncontrada;
import poo.Excepciones.FormatoNoValido;
import poo.Excepciones.NoSePuedeComprarAccionesExcepcion;

import java.util.ArrayList;

public class BolsaDeValores {
    public String nombreBolsa;
    public ArrayList<Empresa> listaEmpresas;

    public BolsaDeValores(String nombre,Empresa empresa) {
        this.nombreBolsa = nombre;
        this.listaEmpresas = new ArrayList<>();
        this.listaEmpresas.add(empresa);
    }

    public void añadirEmpresa(Empresa empresa){
        this.listaEmpresas.add(empresa);
    }

    public void eliminarEmpresa (Empresa empresa){
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
    public StringBuffer realizarOperacion (String mensaje) throws FormatoNoValido,EmpresaNoEncontrada,NoSePuedeComprarAccionesExcepcion {
        String[] fields = mensaje.split("\\|"); // "99|entero"
                /*  fields[0] = "99";
                    fields[1] = "entero";
                 */

            //if (fields.length<2) throw new FormatoNoValido("Formato de entrada invalido");
            int identificador = (Integer.parseInt(fields[0]));
            String nombreCliente = fields[1];
            String nombreEmpresa = fields[2];
            double dinero = (double) Integer.parseInt(fields[3]);

            Empresa empresa = buscarEmpresa(nombreEmpresa);
            Object[] numTitulos = calcularNumTitulo(dinero,empresa.getValorActual());
            /*Controlar que te devuelve la cadena bien hecha*/
            return new StringBuffer(identificador+nombreCliente+numTitulos[0]+empresa.getValorActual()+numTitulos[1]);


    }

            public Empresa buscarEmpresa(String nombreEmpresa) throws EmpresaNoEncontrada{
                int posicion=this.listaEmpresas.indexOf(new Empresa(nombreEmpresa));
                if(posicion==-1)throw new EmpresaNoEncontrada("Formato de entrada invalido");
                Empresa empresa =this.listaEmpresas.get(posicion);
                return empresa;
            }

            public Object[] calcularNumTitulo(double dinero,double valorActual) throws NoSePuedeComprarAccionesExcepcion {
                    double cociente=(dinero/valorActual);

                    int numAcciones=(int) Math.floor(cociente);
                    double restoAcciones=cociente-numAcciones;
                    if(numAcciones==0) throw new NoSePuedeComprarAccionesExcepcion();
                    Object[] accionesCompradas = new Object[2];
                    accionesCompradas[0]=numAcciones;
                    accionesCompradas[1]=restoAcciones;
                    return accionesCompradas;
            }





    public void actualizarValoresAcciones(){
        ArrayList<Empresa> empresas=this.listaEmpresas;
        for (Empresa empresa: empresas){
            empresa.valorActualEmpresa(Math.random());//
        }
    }
}
