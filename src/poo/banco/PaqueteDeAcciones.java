package poo.banco;

import java.io.Serializable;

public class PaqueteDeAcciones implements Serializable{
    private String nombreEmpresa;
    private int numeroDeAcciones;
    private Double valorPaquete;

    public PaqueteDeAcciones(String nombreEmpresa, int numeroDeAcciones, Double precioCompra) {
        this.nombreEmpresa = nombreEmpresa;
        this.numeroDeAcciones = numeroDeAcciones;
        this.valorPaquete = numeroDeAcciones * precioCompra;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public int getNumeroDeAcciones() {
        return numeroDeAcciones;
    }

    public Double getValorPaquete() {
        return valorPaquete;
    }

    public void actualizarPaqueteCompra(int numeroDeAcciones, Double precioCompra){
        this.numeroDeAcciones = this.numeroDeAcciones + numeroDeAcciones;
        this.valorPaquete = numeroDeAcciones * precioCompra;
    }

    public void actualizarPaqueteVenta(int numeroDeAcciones, Double precioVenta){
        this.numeroDeAcciones = this.numeroDeAcciones - numeroDeAcciones;
        this.valorPaquete = this.valorPaquete - this.numeroDeAcciones * precioVenta;
    }

    public void actualizarPaqueteValor(Double precioActulizado){
        this.valorPaquete = this.numeroDeAcciones * precioActulizado;
    }

    public String toString() {
        return "Empresa: "+ nombreEmpresa +", Nº de Acciones: "+ numeroDeAcciones+ ", Valor del Paquete: " +valorPaquete;
    }
}
