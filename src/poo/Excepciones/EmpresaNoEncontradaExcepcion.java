package poo.Excepciones;

public class EmpresaNoEncontradaExcepcion extends Exception{
    public EmpresaNoEncontradaExcepcion() {
        super("La empresa no se encuentra actualmente en la bolsa");
    }
}
