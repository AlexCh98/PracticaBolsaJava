package poo.mensajes;

abstract public class Mensaje {
    protected int identificador;



    public Mensaje(int identificador) {
        this.identificador = identificador;
    }
    abstract public String toString();
    abstract public String getTipo();

    public int getIdentificador() {
        return identificador;
    }
}