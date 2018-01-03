package poo.Excepciones;

public class CadenaVaciaExcepcion extends  Exception {
    public CadenaVaciaExcepcion() {
        super("La cadena no puede estar vacia");
    }
}
