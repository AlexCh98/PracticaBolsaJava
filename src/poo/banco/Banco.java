package poo.banco;

import java.util.Map;
import java.util.TreeMap;

public class Banco {
    public String nombre;
    public Map<String, Cliente> clientes;

    public Banco(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.clientes = new TreeMap<String, Cliente>();
        String dni = cliente.getDni();
        Cliente clienteCopia = cliente.copiarCliente();
        this.clientes.put(dni, clienteCopia);
    }
    public void anadirCliente(Cliente cliente){
        String dni = cliente.getDni();
        Cliente clienteCopia = cliente.copiarCliente();
        this.clientes.put(dni, clienteCopia);
    }

    public void eliminarCliente(Cliente cliente) {
        String dni = cliente.getDni();
        this.clientes.remove(dni);
    }

    public void realizarCopiaSeguridad(){}
    public void restaurarCopiaSeguridad(){}

    public void imprimirClientes(){
        for(Map.Entry<String,Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            System.out.println(cliente);
        }
    }

    public void subirClienteAPremium(Cliente cliente){
        Cliente clienteCopia = cliente.copiarCliente();
        Clie
    }


}
