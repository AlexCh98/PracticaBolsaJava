package poo.mensajes;

import poo.bolsa.Empresa;

import java.util.StringJoiner;

public class MensajeRespuestaCompra extends MensajeCompra{
    private boolean operacion;
    private int accionesCompradas;
    private Double precioAccion;
    private Double dineroSobrante;


    public MensajeRespuestaCompra(int identificador, String nombreCliente, String nombreEmpresa,Double dinero, boolean operacion, int numAccionesCompradas, Double precioAccion, Double dineroSobrante) {
        super(identificador, nombreCliente,nombreEmpresa,dinero);
        this.operacion = operacion;
        this.accionesCompradas=numAccionesCompradas;
        this.precioAccion = precioAccion;
        this.dineroSobrante = dineroSobrante;
    }

    public String toString(){
        StringJoiner sj = new StringJoiner("|");
        sj.add(Integer.toString(this.identificador));
        sj.add(this.nombreCliente);
        sj.add(Boolean.toString(this.operacion));
        sj.add(Integer.toString(this.accionesCompradas));
        sj.add(Double.toString(this.precioAccion));
        sj.add(Double.toString(this.dineroSobrante));
        return sj.toString();
    }

    public boolean isOperacion() {
        return operacion;
    }

    public int getAccionesCompradas() {
        return accionesCompradas;
    }

    public Double getPrecioAccion() {
        return precioAccion;
    }

    public Double getDineroSobrante() {
        return dineroSobrante;
    }
}