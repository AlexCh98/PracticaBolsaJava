package poo.general;

import poo.banco.*;
import poo.bolsa.*;
import poo.Excepciones.*;

import java.io.IOException;

public class Simulador {
    public Simulador()  {
        // Declaracion de datos para ejemplos
        Empresa empresa1 = new Empresa("Repsol", 2000.0, 3000.0);
        Empresa empresa2 = new Empresa("Cepsa", 4000.0, 3000.0);
        Empresa empresa3 = new Empresa("Telefonica", 8000.0, 6000.0);
        Empresa empresa4 = new Empresa("BBVA", 2500.0, 1500.0);
        Empresa empresa5 = new Empresa("Santander", 1020.0, 2020.0);
        BolsaDeValores bolsa = new BolsaDeValores("Bolsa de Madrid", empresa1);
        try {
            bolsa.anadirEmpresa(empresa2);
            bolsa.anadirEmpresa(empresa3);
            bolsa.anadirEmpresa(empresa4);
            bolsa.anadirEmpresa(empresa5);
        } catch (EmpresaRepetidaExcepcion e) {
            //En teoria nunga deberia llegar aqui
            System.out.println("Error en la declaracion de datos de ejemplo");
        }
        AgenteDeInversiones broker = new AgenteDeInversiones(bolsa);
        GestorInversiones gestorInversiones = new GestorInversiones("Alex", "34568798K");
        PaqueteDeAcciones paqueteDeAcciones = new PaqueteDeAcciones("Repsol",4,2000.3);
        Cliente cliente1 = new ClientePremium("Jose", "53956431H", 200000.24, gestorInversiones);
        Cliente cliente2 = new Cliente("Ivan", "64967532D", 300000.0);
        Cliente cliente3 = new Cliente("Alex", "47472532A", 250000.0);
        cliente3.anadirPaqueteDeAciones(paqueteDeAcciones);
        Cliente cliente4 = new Cliente("Lucas", "17802852H", 18000.53);
        Banco banco = new Banco("Banco de España",cliente1,broker);
        try {
            banco.anadirCliente(cliente2);
            banco.anadirCliente(cliente3);
            banco.anadirCliente(cliente4);
        } catch (ClienteYaEstaExcepcion e) {
            //En teoria nunga deberia llegar aqui
            System.out.println("Error en la declaracion de datos de ejemplo");
        }
        InterfazDeUsuario interfazDeUsuario = new InterfazDeUsuario();
        int identificadorCompra = 1;
        int identificadorVenta = 1;
        int identificadorActualizacion = 1;
        interfazDeUsuario.imprimirMenu();
        int opcion = interfazDeUsuario.getOpcion();
        while(opcion != 0) {
            switch (opcion) {
                case 1:
                    //Imprimir estado de los clientes
                    banco.imprimirClientes();
                    break;
                case 2:
                    //Imprimir estado de los clientes
                    bolsa.imprimirEmpresas();
                    break;
                case 3:
                    //Añadir cliente
                    boolean sinError3 = false;
                    Cliente nuevoCliente3 = null;
                    String nombre3 = null;
                    String dni3 = null;
                    Double saldo3 = 0.0;
                    do {
                        System.out.println("Introduzca datos del cliente");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca nombre del cliente");
                            nombre3 = escaner.leerCadenaSinNumeros();
                            System.out.println("Introduzca dni del cliente");
                            dni3 = escaner.leerDni();
                            System.out.println("Introduzca saldo inicial del cliente");
                            saldo3 = escaner.leerDouble();
                            nuevoCliente3 = new Cliente(nombre3, dni3, saldo3);
                            banco.anadirCliente(nuevoCliente3);
                            sinError3 = true;
                        } catch (ClienteYaEstaExcepcion | NoEsDoubleExcepcion | NoEsCadenaSinNumeros | NoEsDniExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError3);
                    System.out.println("Cliente añadido con exito");
                    System.out.println(nuevoCliente3);
                    break;
                case 4:
                    //Eliminar cliente
                    boolean sinError4 = false;
                    String dni4 = null;
                    Cliente clienteEliminar = null;
                    do {
                        System.out.println("Introduzca datos del cliente");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca dni del cliente");
                            dni4 = escaner.leerDni();
                            clienteEliminar = banco.buscarCliente(dni4);
                            banco.eliminarCliente(dni4);
                            sinError4 = true;
                        } catch (NoEsDniExcepcion | ClienteNoEncontradoExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError4);
                    System.out.println("Cliente eliminado con exito");
                    System.out.println(clienteEliminar);
                    break;
                case 5:
                    //Realizar copia de seguridad
                    try {
                        banco.realizarCopiaSeguridad();
                    }catch (ErrorCerrarExcepcion |ErrorSeguridadExcepcion | ArchivoNoEncontradoExcepcion e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al realizar la Copia");
                    } catch (IOException e) {
                        System.out.println("Error al realizar la Copia");
                    }
                    System.out.println("Copia de seguridad realizada correctamente en banco.dat");
                    break;
                case 6:
                    //Restaurar copia de seguridad
                    try {
                        banco.restaurarCopiaSeguridad();
                    }catch (ErrorCerrarExcepcion |ErrorSeguridadExcepcion | ArchivoNoEncontradoExcepcion | ErrorCastingExcepcion e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al realizar la Copia");
                    } catch (IOException e) {
                        System.out.println("Error al realizar la Copia");
                    }
                    System.out.println("Copia de seguridad restaurada correctamente de banco.dat");
                    break;
                case 7:
                    //Mejorar cliente a premium
                    boolean sinError7 = false;
                    String dniCliente7 = null;
                    String nombreGestor7 = null;
                    String dniGestor7 = null;
                    Cliente clienteSubirPremium = null;
                    do {
                        System.out.println("Introduzca datos del cliente");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca dni del cliente:");
                            dniCliente7 = escaner.leerDni();
                            System.out.println("Introduzca nombre del gestor:");
                            nombreGestor7 = escaner.leerCadenaSinNumeros();
                            System.out.println("Introduzca dni del gestor:");
                            dniGestor7 = escaner.leerDni();
                            banco.subirClienteAPremium(dniCliente7, nombreGestor7, dniGestor7);
                            clienteSubirPremium = banco.buscarCliente(dniCliente7);
                            sinError7 = true;
                        } catch (NoEsCadenaSinNumeros | NoEsDniExcepcion | ClienteNoEncontradoExcepcion | ClienteYaEsPremiumExcepcion
                                | ClienteYaEstaExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError7);
                    System.out.println("Cliente subido a premium con exito");
                    System.out.println(clienteSubirPremium);
                    break;
                case 8:
                    //Solicitar recomendacion de inversion
                    boolean sinError8 = false;
                    String dniCliente8 = null;
                    do {
                        System.out.println("Introduzca datos del cliente");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca dni del cliente:");
                            dniCliente8 = escaner.leerDni();
                            System.out.println(banco.solicitarRecomendacionInversion(dniCliente8, identificadorActualizacion));
                            identificadorActualizacion++;
                            sinError8 = true;
                        } catch (NoEsDniExcepcion | ClienteNoEncontradoExcepcion | ClienteNoPremiumExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError8);
                    break;
                case 9:
                    //Añadir empresa a la bolsa
                    boolean sinError9 = false;
                    Empresa nuevoEmpresa9 = null;
                    String nombre9 = null;
                    Double valor9 = 0.0;
                    do {
                        System.out.println("Introduzca datos de la empresa");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca nombre de la empresa");
                            nombre9 = escaner.leerCadena();
                            System.out.println("Introduzca valor de la empresa");
                            valor9 = escaner.leerDouble();
                            nuevoEmpresa9 = new Empresa(nombre9, valor9);
                            bolsa.anadirEmpresa(nuevoEmpresa9);
                            sinError9 = true;
                        } catch (CadenaVaciaExcepcion |EmpresaRepetidaExcepcion | NoEsDoubleExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError9);
                    System.out.println("Empresa añadido con exito");
                    System.out.println(nuevoEmpresa9);
                    break;
                case 10:
                    //Eliminar empresa
                    boolean sinError10 = false;
                    String nombre10 = null;
                    Empresa empresaEliminar = null;
                    do {
                        System.out.println("Introduzca datos de la empresa");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca nombre de la empresa");
                            nombre10 = escaner.leerCadena();
                            empresaEliminar = bolsa.buscarEmpresa(nombre10);
                            bolsa.eliminarEmpresa(empresaEliminar);
                            sinError10 = true;
                        } catch ( CadenaVaciaExcepcion |EmpresaNoEncontradaExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError10);
                    System.out.println("Empresa eliminada con exito");
                    System.out.println(empresaEliminar);
                    break;
                case 11:
                    //Actualizacion de valores
                    bolsa.actualizarValoresAcciones();
                    System.out.println("Los nuevos valores");
                    bolsa.imprimirEmpresas();
                    break;
                case 12:
                    //Realizar la copia
                    try {
                        bolsa.realizarCopiaSeguridad();
                    }catch (ErrorCerrarExcepcion |ErrorSeguridadExcepcion | ArchivoNoEncontradoExcepcion e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al realizar la Copia");
                    } catch (IOException e) {
                        System.out.println("Error al realizar la Copia");
                    }
                    System.out.println("Copia de seguridad realizada correctamente en bolsa.dat");
                    break;
                case 13:
                    //Restaurar copia de seguridad
                    try {
                        bolsa.restaurarCopiaSeguridad();
                    }catch (ErrorCerrarExcepcion |ErrorSeguridadExcepcion | ArchivoNoEncontradoExcepcion | ErrorCastingExcepcion e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al realizar la Copia");
                    } catch (IOException e) {
                        System.out.println("Error al realizar la Copia");
                    }
                    System.out.println("Copia de seguridad restaurada correctamente de bolsa.dat");
                    break;
                case 14:
                    //Solicitar compra de acciones
                    boolean sinError14 = false;
                    String dniCliente14 = null;
                    String nombreEmpresa14 = null;
                    Double gastoMaximo14 = 0.0;
                    do {
                        System.out.println("Introduzca datos del cliente y de la empresa");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca dni del cliente");
                            dniCliente14 = escaner.leerDni();
                            System.out.println("Introduzca nombre de la empresa");
                            nombreEmpresa14 = escaner.leerCadena();
                            System.out.println("Introduzca el maximo a gastar");
                            gastoMaximo14 = escaner.leerDouble();
                            banco.hacerCompra(identificadorCompra, dniCliente14, nombreEmpresa14, gastoMaximo14);
                            sinError14 = true;
                            identificadorCompra++;
                        } catch (NoEsDniExcepcion | CadenaVaciaExcepcion | ClienteNoEncontradoExcepcion | NoEsDoubleExcepcion | NoSuficienteSaldoExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError14);
                    System.out.println("Operacion de compra almacenada en la cola del broker correctamente");
                    break;
                case 15:
                    //Solicitar venta de acciones
                    boolean sinError15 = false;
                    String dniCliente15 = null;
                    String nombreEmpresa15 = null;
                    int numAcciones15 = 0;
                    do {
                        System.out.println("Introduzca datos del cliente y de la empresa");
                        Escaner escaner = new Escaner();
                        try {
                            System.out.println("Introduzca dni del cliente");
                            dniCliente15 = escaner.leerDni();
                            System.out.println("Introduzca nombre de la empresa");
                            nombreEmpresa15 = escaner.leerCadena();
                            System.out.println("Introduzca las acciones a vender");
                            numAcciones15 = escaner.leerEntero();
                            banco.hacerVenta(identificadorVenta, dniCliente15, nombreEmpresa15, numAcciones15);
                            sinError15 = true;
                            identificadorVenta++;
                        } catch (NoEsDniExcepcion | CadenaVaciaExcepcion |ClienteNoEncontradoExcepcion | NoEsEnteroExcepcion | NoSuficientesAccionesExcecion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!sinError15);
                    System.out.println("Operacion de venta almacenada en la cola del broker correctamente");
                    break;
                case 16:
                    //Solicitar informacion de estado de las acciones
                    try {
                        banco.hacerActualizacion(identificadorActualizacion);
                        identificadorActualizacion++;
                    } catch (NoHayClientesExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Operacion de actualizacion almacenada en la cola del broker correctamente");
                    break;
                case 17:
                    //Imprimir operaciones pendientes
                    broker.imprimirPeticionesPendientes();
                    break;
                case 18:
                    //Ejecutar operaciones pendientes
                    try {
                        broker.empiezaTrabajar(banco);
                    } catch ( EmpresaNoEncontradaExcepcion | NoSePuedeComprarAccionesExcepcion
                            | ClienteNoEncontradoExcepcion | VentaNoRealizadaExcepcion | CompraNoRealizadaExcepcion e) {
                        System.out.println(e.getMessage());
                        System.out.println("Trabajo interrumpido por que hay algun error");
                        System.out.println("Corregir el error y volver a ponera trabajar al broker");
                    }
                    finally {
                        System.out.println("El broker ha terminado de trabajar");
                    }
                    break;
            }
            System.out.println("Pulse una tecla para continuar");
            Escaner escaner = new Escaner();
            escaner.leerParaContinuar();
            interfazDeUsuario.imprimirMenu();
            opcion = interfazDeUsuario.getOpcion();
        }
    }
}
