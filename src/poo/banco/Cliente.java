package poo.banco;

import poo.Excepciones.PaqueteNoEnContradoExcepcion;

import java.util.ArrayList;

public class Cliente extends Persona{
    private float saldo;
    private ArrayList<PaqueteDeAcciones> carteraDeAcciones;

    public Cliente(String nombre, String dni, Float saldo) {
        super(nombre, dni);
        this.saldo = saldo;
        this.carteraDeAcciones = new ArrayList<>();
    }

    public void anadirPaqueteDeAciones(PaqueteDeAcciones paqueteDeAcciones){
        carteraDeAcciones.add(paqueteDeAcciones);
    }

    public void actulizarSaldo(float saldo){
        this.saldo = saldo;
    }

    public String toString(){
        return "Nombre : " + this.nombre +
                "DNI: " + this.dni +
                "Saldo: " + this.saldo +
                "Cartera de acciones: \n" + this.carteraDeAcciones;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getSaldo() {
        return saldo;
    }


    public void setCarteraDeAcciones(ArrayList<PaqueteDeAcciones> carteraDeAcciones) {
        this.carteraDeAcciones = carteraDeAcciones;
    }

    public ArrayList<PaqueteDeAcciones> getCarteraDeAcciones() {
        return carteraDeAcciones;
    }

    public PaqueteDeAcciones getPaquete(String nombreEmpresa) throws PaqueteNoEnContradoExcepcion {
        boolean encontrada=false;
        int i=0;
        while (!encontrada && i<this.carteraDeAcciones.size()){
            encontrada=this.carteraDeAcciones.get(i).getNombreEmpresa().equals(nombreEmpresa);
            i++;
        }
        if (!encontrada) throw new PaqueteNoEnContradoExcepcion();
        return this.carteraDeAcciones.get(i-1);
    }
}
