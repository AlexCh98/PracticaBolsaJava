package poo.banco;

public class PaqueteDeAcciones {
    private String nombreEmpresa;
    private int numeroDeAcciones;
    private double valorPaquete;

    public PaqueteDeAcciones(String nombreEmpresa, int numeroDeAcciones, double precioCompra) {
        this.nombreEmpresa = nombreEmpresa;
        this.numeroDeAcciones = numeroDeAcciones;
        this.valorPaquete = numeroDeAcciones * precioCompra;
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
}
