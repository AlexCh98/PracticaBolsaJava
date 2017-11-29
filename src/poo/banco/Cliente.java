package poo.banco;

import java.util.ArrayList;

public class Cliente extends Persona{
    private Float saldo;
    private ArrayList<PaqueteDeAcciones> carteraDeAcciones;

    public Cliente(String nombre, String dni, Float saldo) {
        super(nombre, dni);
        this.saldo = saldo;
        this.carteraDeAcciones = new ArrayList<>();
    }

    public void añadirPaqueteDeAciones(PaqueteDeAcciones paqueteDeAcciones){
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

    public String getDni() {
        return dni;
    }

    public void setCarteraDeAcciones(ArrayList<PaqueteDeAcciones> carteraDeAcciones) {
        this.carteraDeAcciones = carteraDeAcciones;
    }
}
