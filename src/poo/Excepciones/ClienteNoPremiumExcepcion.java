package poo.Excepciones;

public class ClienteNoPremiumExcepcion extends Exception {
    public ClienteNoPremiumExcepcion() {
        super("El cliente no es premium");
    }

}
