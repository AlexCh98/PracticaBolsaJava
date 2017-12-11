package poo.banco;

import poo.Excepciones.*;
import poo.bolsa.BolsaDeValores;
import poo.mensajes.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


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

    public void subirClienteAPremium(Cliente cliente, String nombreGestorInversores)
            throws ClienteNoEncontradoExcepcion, ClienteYaEstaExcepcion, ClienteYaEsPremiumExcepcion {
        if (!this.clientes.contains(cliente)) throw new ClienteNoEncontradoExcepcion();
        if (cliente instanceof ClientePremium) throw new ClienteYaEsPremiumExcepcion();
        this.clientes.remove(cliente);
        ClientePremium clientePremium = new ClientePremium(cliente.getNombre(), cliente.getDni(),
                cliente.getSaldo(), nombreGestorInversores);
        clientePremium.setCarteraDeAcciones(cliente.getCarteraDeAcciones());
        this.anadirCliente(clientePremium);
    }

    public String solicitarRecomendacionInversion(Cliente cliente, BolsaDeValores bolsa)
            throws ClienteNoPremiumExcepcion, ClienteNoEncontradoExcepcion {
        Cliente clienteBuscar = buscarCliente(cliente.getNombre());
        if(!(cliente instanceof ClientePremium)) throw  new ClienteNoPremiumExcepcion();
        return  bolsa.empresaMayorDiferenciaAcciones();
    }

    public boolean clienteTieneSuficienteSaldo(Cliente cliente, double saldo) {
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

    private Cliente buscarCliente(String nombreCliente) throws ClienteNoEncontradoExcepcion {
        Iterator<Cliente> itr = clientes.iterator();
        boolean encontrado = false;
        Cliente cliente = null;
        while(itr.hasNext() && !encontrado){
            cliente =  itr.next();
            encontrado = cliente.getNombre().equals(nombreCliente);
        }
        if (!encontrado) throw new ClienteNoEncontradoExcepcion();
        else return cliente;
    }

    public MensajeCompra hacerCompra(int identificador, String nombreCliente, String nombreEmpresa, double dinero)
            throws ClienteNoEncontradoExcepcion, NoSuficienteSaldoExcepcion {
        Cliente cliente = buscarCliente(nombreCliente);
        if (!clienteTieneSuficienteSaldo(cliente, dinero)) throw new NoSuficienteSaldoExcepcion();
        return  new MensajeCompra(identificador, nombreCliente,nombreEmpresa, dinero);
    }

    public MensajeVenta hacerVenta(int identificador, String nombreCliente, String nombreEmpresa, int numAcciones)
            throws ClienteNoEncontradoExcepcion, NoSuficientesAccionesExcecion {
        Cliente cliente = buscarCliente(nombreCliente);
        if(!comprobacionPaquete(cliente, nombreEmpresa, numAcciones)) throw new NoSuficientesAccionesExcecion();
        return  new MensajeVenta(identificador, nombreCliente, nombreEmpresa, numAcciones);
    }

    public MensajeActualizacion hacerActualizacion(int identificador, String nombreCliente, String nombreEmpresa)
            throws ClienteNoEncontradoExcepcion, NoTieneEmpresaExcepcion {
        Cliente cliente =  buscarCliente(nombreCliente);
        if(!comprobacionPaquete(cliente, nombreEmpresa, 0)) throw new NoTieneEmpresaExcepcion();
        return new MensajeActualizacion(identificador, nombreCliente, nombreEmpresa);
    }

    public void actualizarCliente(Mensaje mensaje){
        if (mensaje instanceof MensajeCompra){

        }else if (mensaje instanceof  MensajeVenta){

        }else{

        }
    }
}
