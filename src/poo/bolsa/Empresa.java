package poo.bolsa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Empresa {
    private String nombreEmpresa;
    private double valorActual;
    private double valorPrevio;

    public Empresa(String nombreEmpresa, double valor) {
        this.nombreEmpresa = nombreEmpresa;
        this.valorPrevio=valor;
        this.valorActual=valor;
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

    private void serializar (Empresa empresa,FileOutputStream file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(file);
        oos.writeObject(empresa);
        oos.close();
    }
}
