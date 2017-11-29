package poo.bolsa;

public class Empresa {
    private String nombreEmpresa;
    private double valorActual;
    private double valorPrevio;

    public Empresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    private void calcularValorPrevio (double valor){
         this.valorPrevio=valor;
    }

    public void valorActualEmpresa (double valor){ //Actualiza la accion actual y modifica la accion anterior.
        calcularValorPrevio(valor);
        this.valorActual=valor;
    }

    public double diferenciaAcciones (){
        double diferencia=Math.abs(this.valorActual-this.valorPrevio);
        return diferencia;
    }
}
