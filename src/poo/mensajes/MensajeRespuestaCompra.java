package poo.mensajes;

import poo.bolsa.Empresa;

import java.util.StringJoiner;

public class MensajeRespuestaCompra extends MensajeCompra{
    private boolean operacion;
    private int accionesCompradas;
    private double precioAccion;
    private double dineroSobrante;


    public MensajeRespuestaCompra(int identificador, String nombreCliente, String nombreEmpresa,double dinero, boolean operacion, int numAccionesCompradas, double precioAccion, double dineroSobrante) {
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


}