package poo.Excepciones;

public class NoEsCadenaSinNumeros extends Exception {
    public NoEsCadenaSinNumeros() {
        super("La cadena no puede contener numeros");
    }
}
