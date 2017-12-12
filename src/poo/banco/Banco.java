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

    public MensajeActualizacion hacerActualizacion(int identificador){
        return new MensajeActualizacion(identificador);
    }

    public int actualizarCliente(Mensaje mensaje) throws ClienteNoEncontradoExcepcion, CompraNoRealizadaExcepcion,
            VentaNoRealizadaExcepcion, PaqueteNoEnContradoExcepcion {
        switch (mensaje.getTipo()){
            case "compra":{
                if(!(((MensajeRespuestaCompra)mensaje).isOperacion())) throw new CompraNoRealizadaExcepcion();
                Cliente cliente = this.buscarCliente(((MensajeRespuestaCompra)mensaje).getNombreCliente());
                try {
                    PaqueteDeAcciones paquete = cliente.getPaquete(((MensajeRespuestaCompra)mensaje).getNombreEmpresa());
                    paquete.actualizarPaqueteCompra(
                            ((MensajeRespuestaCompra)mensaje).getAccionesCompradas(), ((MensajeRespuestaCompra)mensaje).getPrecioAccion());
                } catch (PaqueteNoEnContradoExcepcion e) {
                    PaqueteDeAcciones paquete = new PaqueteDeAcciones(((MensajeRespuestaCompra)mensaje).getNombreEmpresa(),
                            ((MensajeRespuestaCompra)mensaje).getAccionesCompradas(), ((MensajeRespuestaCompra)mensaje).getPrecioAccion());
                    cliente.anadirPaqueteDeAciones(paquete);
                } finally {
                    cliente.setSaldo(cliente.getSaldo() - ((MensajeRespuestaCompra)mensaje).getDinero() +
                            ((MensajeRespuestaCompra)mensaje).getDineroSobrante());
                    System.out.println("Operacion nº: " + mensaje.getIdentificador());
                    System.out.println("Nombre del cliente: " + ((MensajeRespuestaCompra)mensaje).getNombreCliente());
                    System.out.println("Ha comprado " + ((MensajeRespuestaCompra)mensaje).getAccionesCompradas() + " acciones de "+
                            ((MensajeRespuestaCompra)mensaje).getNombreEmpresa()  +" a un " +
                            " precio de " + ((MensajeRespuestaCompra)mensaje).getPrecioAccion());
                    System.out.println("El nuevo saldo del cliente es: " + cliente.getSaldo());
                    System.out.println("Compra realizada con exito");
                }
                break;
            }
            case "venta":{
                if(!(((MensajeRespuestaVenta)mensaje).isOperacion())) throw new VentaNoRealizadaExcepcion();
                Cliente cliente = this.buscarCliente(((MensajeRespuestaVenta)mensaje).getNombreCliente());
                PaqueteDeAcciones paquete = cliente.getPaquete(((MensajeRespuestaVenta)mensaje).getNombreEmpresa());
                paquete.actualizarPaqueteVenta(((MensajeRespuestaVenta)mensaje).getAccionesVenta(),
                        ((MensajeRespuestaVenta)mensaje).getPrecioAccion());
                cliente.setSaldo(cliente.getSaldo() + ((MensajeRespuestaVenta)mensaje).getDineroDevuelto());
                System.out.println("Operacion nº: " + mensaje.getIdentificador());
                System.out.println("Nombre del cliente: " + ((MensajeRespuestaVenta)mensaje).getNombreCliente());
                System.out.println("Ha vendido " + ((MensajeRespuestaVenta)mensaje).getAccionesVenta() + " acciones de "+
                        ((MensajeRespuestaVenta)mensaje).getNombreEmpresa() +" a un" +
                        " precio de " + ((MensajeRespuestaVenta)mensaje).getPrecioAccion());
                System.out.println("El nuevo saldo del cliente es: " + cliente.getSaldo());
                System.out.println("Compra realizada con exito");
                break;
            }
            default:{//actualizacion

            }
        }
        return 0;
    }
}
