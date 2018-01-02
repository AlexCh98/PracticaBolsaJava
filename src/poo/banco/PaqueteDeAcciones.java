package poo.banco;

import java.io.Serializable;

public class PaqueteDeAcciones implements Serializable{
    private String nombreEmpresa;
    private int numeroDeAcciones;
    private double valorPaquete;

    public PaqueteDeAcciones(String nombreEmpresa, int numeroDeAcciones, double precioCompra) {
        this.nombreEmpresa = nombreEmpresa;
        this.numeroDeAcciones = numeroDeAcciones;
        this.valorPaquete = numeroDeAcciones * precioCompra;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public double getNumeroDeAcciones() {
        return numeroDeAcciones;
    }

    public double getValorPaquete() {
        return valorPaquete;
    }

    public void actualizarPaqueteCompra(int numeroDeAcciones, double precioCompra){
        this.numeroDeAcciones = this.numeroDeAcciones + numeroDeAcciones;
        this.valorPaquete = numeroDeAcciones * precioCompra;
    }

    public void actualizarPaqueteVenta(int numeroDeAcciones, double precioVenta){
        this.numeroDeAcciones = this.numeroDeAcciones - numeroDeAcciones;
        this.valorPaquete = this.valorPaquete - this.numeroDeAcciones * precioVenta;
    }

    public void actulizarPaqueteValor(double precioActulizado){
        this.valorPaquete = this.numeroDeAcciones * precioActulizado;
    }

    public String toString() {
        return "Empresa: "+ nombreEmpresa +", Nº de Acciones: "+ numeroDeAcciones+ ", Valor del Paquete: " +valorPaquete;
    }
}
