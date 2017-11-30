package poo.banco;

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

    public Cliente copiarCliente(){
        Cliente clienteSalida = new Cliente(this.nombre, this.dni, this.saldo);
        if (!this.carteraDeAcciones.isEmpty()){
            for(PaqueteDeAcciones paquete : carteraDeAcciones){
                clienteSalida.anadirPaqueteDeAciones(paquete);
            }
        }else{

            this.carteraDeAcciones = new ArrayList<>();
        }
        return clienteSalida;
    }
}
