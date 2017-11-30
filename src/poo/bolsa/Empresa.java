package poo.bolsa;

import java.io.*;

public class Empresa implements Serializable{
    private String nombreEmpresa;
    private double valorActual;
    private double valorPrevio;

    public Empresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Empresa(String nombreEmpresa, double valorActual, double valorPrevio) {
        this(nombreEmpresa);
        this.valorPrevio=valorPrevio;
        this.valorActual=valorActual;
    }
    public Empresa(String nombreEmpresa, double valor) {
        this(nombreEmpresa);
        this.valorPrevio=valor;
        this.valorActual=valor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public double getValorActual() {
        return valorActual;
    }

    public void setValorActual(double valorActual) {
        this.valorActual = valorActual;
    }

    public double getValorPrevio() {
        return valorPrevio;
    }

    public void setValorPrevio(double valorPrevio) {
        this.valorPrevio = valorPrevio;
    }

    private void calcularValorPrevio (double valor){
         this.valorPrevio=valor;
    }

    public void valorActualEmpresa (double valor){ //Actualiza la accion actual y modifica la accion anterior.
        calcularValorPrevio(this.valorActual);
        this.valorActual=valor;
    }

    public double diferenciaAcciones (){
        double diferencia=Math.abs(this.valorActual-this.valorPrevio);
        return diferencia;
    }

    public String toString (){
        return (new StringBuilder("Nombre: " + this.nombreEmpresa  + ", Valor Actual " + this.valorActual+", Valor Previo "+this.valorPrevio).toString());
    }


}
