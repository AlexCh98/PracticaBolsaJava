package poo.Excepciones;

public class ClienteYaEsPremiumExcepcion extends Exception {
    public ClienteYaEsPremiumExcepcion() {
        super("El cliente ya es premium");
    }
}
