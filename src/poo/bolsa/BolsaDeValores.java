package poo.bolsa;

import java.util.ArrayList;

public class BolsaDeValores {
    public String nombreBolsa;
    public ArrayList<Empresa> listaEmpresas;

    public BolsaDeValores(String nombre) {
        this.nombreBolsa = nombre;
        this.listaEmpresas = new ArrayList<>();
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

    public StringBuffer realizarOperacion (StringBuffer mensaje){

        return mensaje;
    }
    public void actualizarValoresAcciones(){
        ArrayList<Empresa> empresas=this.listaEmpresas;
        for (Empresa empresa: empresas){
            empresa.valorActualEmpresa(Math.random());
        }
    }
}
