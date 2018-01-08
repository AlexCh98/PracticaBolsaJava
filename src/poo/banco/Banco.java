package poo.banco;

import poo.Excepciones.*;
import poo.mensajes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class Banco {
    private String nombre;
    private HashSet<Cliente> clientes;
    private AgenteDeInversiones agenteDeInversiones;

    public Banco(String nombre, Cliente cliente, AgenteDeInversiones agenteDeInversiones) {
        this.nombre = nombre;
        this.clientes = new HashSet<>();
        this.clientes.add(cliente);
        this.agenteDeInversiones = agenteDeInversiones;
    }
    public void anadirCliente(Cliente cliente)throws ClienteYaEstaExcepcion {
        try {
            buscarClienteNombre(cliente.getNombre());
            throw new ClienteYaEstaExcepcion();
        } catch (ClienteNoEncontradoExcepcion e) {
            //Si no lo encuentra sigue le programa
        }
        if (!this.clientes.add(cliente)) throw new ClienteYaEstaExcepcion();
    }

    public void eliminarCliente(String dni) throws ClienteNoEncontradoExcepcion {
        Cliente cliente  = this.buscarCliente(dni);
        this.clientes.remove(cliente);
    }

    public void realizarCopiaSeguridad() throws IOException, ErrorCerrarExcepcion, ErrorSeguridadExcepcion, ArchivoNoEncontradoExcepcion {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Banco.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);


            oos.writeObject(this.nombre);
            oos.writeObject(this.clientes);

        } catch (SecurityException e) {
            throw new ErrorSeguridadExcepcion();
        } catch (FileNotFoundException e) {
            throw new ArchivoNoEncontradoExcepcion();
        } finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new ErrorCerrarExcepcion();
                }
            }
        }
    }

    public void restaurarCopiaSeguridad() throws IOException, ArchivoNoEncontradoExcepcion, ErrorSeguridadExcepcion, ErrorCerrarExcepcion, ErrorCastingExcepcion {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream ("Banco.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.clientes.clear();
            this.nombre= (String) ois.readObject();
            this.clientes = (HashSet<Cliente>) ois.readObject();
            ois.close();

        }  catch (SecurityException e) {
            throw new ErrorSeguridadExcepcion();
        } catch (FileNotFoundException e) {
            throw new ArchivoNoEncontradoExcepcion();
        } catch (ClassNotFoundException e) {
            throw new ErrorCastingExcepcion();
        } finally {
            if(fis!=null)
            try {
                fis.close();
            } catch (IOException e) {
                throw new ErrorCerrarExcepcion();
            }
        }
    }

    public void imprimirClientes(){
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void subirClienteAPremium(String dniCliente, String nombreGestorInversores, String dniGestorInversiones)
            throws ClienteNoEncontradoExcepcion, ClienteYaEstaExcepcion, ClienteYaEsPremiumExcepcion {
        Cliente cliente = buscarCliente(dniCliente);
        if (cliente instanceof ClientePremium) throw new ClienteYaEsPremiumExcepcion();
        this.clientes.remove(cliente);
        ClientePremium clientePremium = new ClientePremium(cliente.getNombre(), cliente.getDni(),
                cliente.getSaldo(), new GestorInversiones(nombreGestorInversores, dniGestorInversiones));
        clientePremium.setCarteraDeAcciones(cliente.getCarteraDeAcciones());
        this.anadirCliente(clientePremium);
    }

    public String solicitarRecomendacionInversion(String dni, int identificador)
            throws ClienteNoPremiumExcepcion, ClienteNoEncontradoExcepcion {
        Cliente cliente = buscarCliente(dni);
        if(!(cliente instanceof ClientePremium)) throw  new ClienteNoPremiumExcepcion();
        String nombreEmpresa = ((ClientePremium)cliente).getGestorInversiones().SolicitarRecomendacion(identificador, this.agenteDeInversiones);
        return "Deberias invertir en " + nombreEmpresa;
    }

    private boolean clienteTieneSuficienteSaldo(Cliente cliente, Double saldo) {
        return cliente.getSaldo() >= saldo;
    }


    private boolean comprobacionPaquete(Cliente cliente, String nombreEmpresa, int numAcciones){
        try {
            PaqueteDeAcciones paquete = cliente.getPaquete(nombreEmpresa);
            return paquete.getNumeroDeAcciones() >= numAcciones;
        }
        catch (PaqueteNoEnContradoExcepcion e) {
            return false;
        }
    }

    public Cliente buscarCliente(String dni) throws ClienteNoEncontradoExcepcion {
        Iterator<Cliente> itr = clientes.iterator();
        boolean encontrado = false;
        Cliente cliente = null;
        while(itr.hasNext() && !encontrado){
            cliente =  itr.next();
            encontrado = cliente.getDni().equals(dni);
        }
        if (!encontrado) throw new ClienteNoEncontradoExcepcion();
        else return cliente;
    }

    public void hacerCompra(int identificador, String dni, String nombreEmpresa, Double dinero)
            throws ClienteNoEncontradoExcepcion, NoSuficienteSaldoExcepcion {
        Cliente cliente = buscarCliente(dni);
        if (!clienteTieneSuficienteSaldo(cliente, dinero)) throw new NoSuficienteSaldoExcepcion();
        this.agenteDeInversiones.almacenarMensaje(new MensajeCompra(identificador, cliente.nombre,nombreEmpresa, dinero));
    }

    public void hacerVenta(int identificador, String dni, String nombreEmpresa, int numAcciones)
            throws ClienteNoEncontradoExcepcion, NoSuficientesAccionesExcecion {
        Cliente cliente = buscarCliente(dni);
        if(!comprobacionPaquete(cliente, nombreEmpresa, numAcciones)) throw new NoSuficientesAccionesExcecion();
        this.agenteDeInversiones.almacenarMensaje(new MensajeVenta(identificador, cliente.nombre, nombreEmpresa, numAcciones));
    }

    public void hacerActualizacion(int identificador) throws NoHayClientesExcepcion {
        if(this.clientes.isEmpty()) throw  new NoHayClientesExcepcion();
        this.agenteDeInversiones.almacenarMensaje(new MensajeActualizacion(identificador));
    }

    void actualizarCliente(Mensaje mensaje) throws ClienteNoEncontradoExcepcion, CompraNoRealizadaExcepcion,
            VentaNoRealizadaExcepcion {
        switch (mensaje.getTipo()){
            case "compra":{
                if(!(((MensajeRespuestaCompra)mensaje).isOperacion())) throw new CompraNoRealizadaExcepcion();
                Cliente cliente = this.buscarClienteNombre(((MensajeRespuestaCompra)mensaje).getNombreCliente());
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
                Cliente cliente = this.buscarClienteNombre(((MensajeRespuestaVenta)mensaje).getNombreCliente());
                PaqueteDeAcciones paquete = null;
                try {
                    paquete = cliente.getPaquete(((MensajeRespuestaVenta) mensaje).getNombreEmpresa());
                } catch (PaqueteNoEnContradoExcepcion excepcion) {
                    //No llega nunca aqui, se supone que ya se comprobo que el cliente podia vender al realizar el MensajeVenta
                }
                //assert paquete != null;
                assert paquete != null;
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
            case "actualizacion" :
                System.out.println("Operacion nº: " + mensaje.getIdentificador());
                ArrayList<String> nombresEmpresas = ((MensajeRespuestaActualizacion)mensaje).getNombresEmpresas();
                ArrayList<Double> valoresEmpresas = ((MensajeRespuestaActualizacion)mensaje).getValoresEmpresas();
                int numPaquetesActualizados = 0;
                int numClientes = 0;
                for (Cliente cliente : clientes) {
                    System.out.println("Nombre del cliente: " + cliente.getNombre());
                    if (cliente.getCarteraDeAcciones().size() == 0){
                        System.out.println("    El cliente no tiene acciones de ninguna empresa");
                    }else {
                        for (String nombreEmpresa : nombresEmpresas) {
                            try {
                                PaqueteDeAcciones paquete = cliente.getPaquete(nombreEmpresa);
                                if (paquete.getValorPaquete() != paquete.getNumeroDeAcciones() *
                                        valoresEmpresas.get(nombreEmpresa.indexOf(nombreEmpresa))) {
                                    paquete.actualizarPaqueteValor(valoresEmpresas.get(nombreEmpresa.indexOf(nombreEmpresa)));
                                    System.out.println("    Se actualizado el valor de las acciones de " + nombreEmpresa +
                                            " el nuevo valor del paquete es " + paquete.getValorPaquete());
                                    numPaquetesActualizados++;
                                }
                                numClientes++;
                            } catch (PaqueteNoEnContradoExcepcion e) {
                                //Si no existe el paquete pasamos a la siguiente empresa(siguiente iteracion del for each)
                            }
                        }
                        if (numPaquetesActualizados == 0) {
                            System.out.println("    No se ha actualizado ningun paquete de este cliente");
                        } else {
                            System.out.println("    Se han actualizado todos los paquetes de este cliente");
                        }
                    }
                }
                System.out.println("Actualizacion de paquetes de " + numClientes + " cliente/s");
                System.out.println("Actualizacion de paquetes realizada con exito");
                break;
            }
        }

    private Cliente buscarClienteNombre(String nombreCliente) throws ClienteNoEncontradoExcepcion {
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
}

