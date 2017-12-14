package poo.banco;

public class ClientePremium extends Cliente{

    private GestorInversiones gestorInversiones;



    public ClientePremium(String nombre, String dni, Double saldo, GestorInversiones gestorInversiones) {
        super(nombre, dni, saldo);
        this.gestorInversiones = gestorInversiones;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Nombre Gestor: " + this.gestorInversiones.getNombre();
    }
}
