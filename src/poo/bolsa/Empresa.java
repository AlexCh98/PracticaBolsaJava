package poo.bolsa;

import java.io.*;

public class Empresa implements Serializable{
    private String nombreEmpresa;
    private Double valorActual;
    private Double valorPrevio;

    public Empresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        //this.valorActual=0;
        //this.valorPrevio=0;
    }

    public Empresa(String nombreEmpresa, Double valorActual, Double valorPrevio) {
        this(nombreEmpresa);
        this.valorPrevio=valorPrevio;
        this.valorActual=valorActual;
    }
    public Empresa(String nombreEmpresa, Double valor) {
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

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    public Double getValorPrevio() {
        return valorPrevio;
    }

    public void setValorPrevio(Double valorPrevio) {
        this.valorPrevio = valorPrevio;
    }

    private void calcularValorPrevio (Double valor){
         this.valorPrevio=valor;
    }

    public void valorActualEmpresa (Double valor){ //Actualiza la accion actual y modifica la accion anterior.
        calcularValorPrevio(this.valorActual);
        this.valorActual=valor;
    }

    public Double diferenciaAcciones (){
        Double diferencia=Math.abs(this.valorActual-this.valorPrevio);
        return diferencia;
    }

    public String toString (){
        return "Nombre: " + this.nombreEmpresa  + ", Valor Actual " + this.valorActual+", Valor Previo "+this.valorPrevio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;

        return nombreEmpresa.equals(empresa.nombreEmpresa);
    }

    @Override
    public int hashCode() {
        return nombreEmpresa.hashCode();
    }
}
