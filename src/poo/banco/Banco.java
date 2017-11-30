package poo.banco;

import poo.Excepciones.ClienteNoEncontradoExcepcion;
import poo.Excepciones.ClienteYaEstaExcepcion;

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
    public void anadirCliente(Cliente cliente)throws ClienteYaEstaExcepcion {
        String dni = cliente.getDni();
        Cliente clienteCopia = cliente.copiarCliente();
        if (this.clientes.containsKey(dni)) throw new ClienteYaEstaExcepcion();
        this.clientes.put(dni, clienteCopia);
    }

    public void eliminarCliente(Cliente cliente) throws ClienteNoEncontradoExcepcion {
        String dni = cliente.getDni();
        if (!this.clientes.containsKey(dni)) throw new ClienteNoEncontradoExcepcion();
        this.clientes.remove(dni);
    }

    public void realizarCopiaSeguridad(){/**/}
    public void restaurarCopiaSeguridad(){/**/}

    public void imprimirClientes(){
        for(Map.Entry<String,Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            System.out.println(cliente);
        }
    }

    public void subirClienteAPremium(Cliente cliente, String nombreGestorInversores) throws ClienteNoEncontradoExcepcion {
        String dni = cliente.getDni();
        if (!this.clientes.containsKey(dni)) throw new ClienteNoEncontradoExcepcion();
        this.clientes.remove(dni);
        ClientePremium clientePremium = new ClientePremium(cliente.getNombre(), cliente.getDni(),
                cliente.getSaldo(), nombreGestorInversores);
        clientePremium.setCarteraDeAcciones(cliente.getCarteraDeAcciones());
        try {
            this.anadirCliente(clientePremium);
        } catch (ClienteYaEstaExcepcion clienteYaEstaExcepcion) {
            // No da nunca esta excepcion, pongo catch para que no de error el intellije idea
        }

    }
}
