package poo.banco;

public class ClientePremium extends Cliente{

    private String nombreGestorInversores;



    public ClientePremium(String nombre, String dni, Double saldo, String nombreGestorInversores) {
        super(nombre, dni, saldo);
        this.nombreGestorInversores = nombreGestorInversores;
    }


}
