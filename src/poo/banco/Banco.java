package poo.banco;

import poo.Excepciones.ClienteNoEncontradoExcepcion;
import poo.Excepciones.ClienteYaEstaExcepcion;
import poo.Excepciones.PaqueteNoEnContradoExcepcion;

import java.util.HashSet;

public class Banco {
    private String nombre;
    private HashSet<Cliente> clientes;

    public Banco(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.clientes = new HashSet<>();
        this.clientes.add(cliente);
    }
    public void anadirCliente(Cliente cliente)throws ClienteYaEstaExcepcion {
        if (!this.clientes.add(cliente)) throw new ClienteYaEstaExcepcion();
    }

    public void eliminarCliente(Cliente cliente) throws ClienteNoEncontradoExcepcion {
        if (!this.clientes.contains(cliente)) throw new ClienteNoEncontradoExcepcion();
        this.clientes.remove(cliente);
    }

    public void realizarCopiaSeguridad(){/**/}
    public void restaurarCopiaSeguridad(){/**/}

    public void imprimirClientes(){
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void subirClienteAPremium(Cliente cliente, String nombreGestorInversores) throws ClienteNoEncontradoExcepcion {
        if (!this.clientes.contains(cliente)) throw new ClienteNoEncontradoExcepcion();
        this.clientes.remove(cliente);
        ClientePremium clientePremium = new ClientePremium(cliente.getNombre(), cliente.getDni(),
                cliente.getSaldo(), nombreGestorInversores);
        clientePremium.setCarteraDeAcciones(cliente.getCarteraDeAcciones());
        try {
            this.anadirCliente(clientePremium);
        } catch (ClienteYaEstaExcepcion clienteYaEstaExcepcion) {
            // No da nunca esta excepcion, pongo catch para que no de error el intellije idea
        }
    }
    public boolean existe(Cliente cliente){
        return clientes.contains(cliente) ;
    }

    public boolean clienteTieneSuficienteSaldo(Cliente cliente, int saldo) {
        return cliente.getSaldo() >= saldo;
    }


    public boolean comprobacionPaquete(Cliente cliente, String nombreEmpresa, int numAcciones){
        try {
            PaqueteDeAcciones paquete = cliente.getPaquete(nombreEmpresa);
            return paquete.getNumeroDeAcciones() >= numAcciones;
        }
        catch (PaqueteNoEnContradoExcepcion e) {
            return false;
        }
    }
}
