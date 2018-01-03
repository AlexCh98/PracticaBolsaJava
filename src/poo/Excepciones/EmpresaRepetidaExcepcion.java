package poo.Excepciones;

public class EmpresaRepetidaExcepcion extends Exception{
    public EmpresaRepetidaExcepcion() {
        super("La empresa se encuentra en esta bolsa, no puede haber empresas repetidas en la bolsa");
    }
}
