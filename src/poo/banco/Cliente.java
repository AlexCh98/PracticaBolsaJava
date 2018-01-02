package poo.banco;

import poo.Excepciones.PaqueteNoEnContradoExcepcion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

public class Cliente extends Persona implements Serializable{
    private double saldo;
    private ArrayList<PaqueteDeAcciones> carteraDeAcciones;

    public Cliente(String nombre, String dni, double saldo) {
        super(nombre, dni);
        this.saldo = saldo;
        this.carteraDeAcciones = new ArrayList<>();
    }

    public void anadirPaqueteDeAciones(PaqueteDeAcciones paqueteDeAcciones){
        carteraDeAcciones.add(paqueteDeAcciones);
    }

    public void actulizarSaldo(double saldo){
        this.saldo = saldo;
    }

    public String toString(){
        return super.toString()+", Saldo: " + this.saldo +", Cartera de acciones: \n" + this.carteraDeAcciones.toString();
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }


    public void setCarteraDeAcciones(ArrayList<PaqueteDeAcciones> carteraDeAcciones) {
        this.carteraDeAcciones = carteraDeAcciones;
    }

    public ArrayList<PaqueteDeAcciones> getCarteraDeAcciones() {
        return carteraDeAcciones;
    }



    public PaqueteDeAcciones getPaquete(String nombreEmpresa) throws PaqueteNoEnContradoExcepcion {
        Iterator<PaqueteDeAcciones> itr = carteraDeAcciones.iterator();
        boolean encontrado = false;
        PaqueteDeAcciones paquete = null;
        while(itr.hasNext() && !encontrado){
            paquete =  itr.next();
            encontrado = paquete.getNombreEmpresa().equals(nombreEmpresa);
        }
        if (!encontrado) throw new PaqueteNoEnContradoExcepcion();
        return paquete;
    }

}
