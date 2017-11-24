package poo.general;

import poo.Excepciones.FueraRangoExcepcion;
import poo.Excepciones.NoEsEnteroExcepcion;

public class InterfazDeUsuario {
    private int opcion;

    public InterfazDeUsuario(int opcion) {
        imprimrMenu();
        Escaner escaner = new Escaner();
        boolean sinError = false;
        do{
            System.out.print("Introduzca la opción a ejecutar : ");
            try {
                opcion = escaner.leerEntero();
                if (opcion < 0||opcion > 18) throw new FueraRangoExcepcion("La opción introducida no es válida, introduzca una opción entre 0 y 18.");
                sinError = true;
            } catch (NoEsEnteroExcepcion | FueraRangoExcepcion e) {
                System.out.println(e.getMessage());
            }
        }while(!sinError);
        switch (opcion){
            case 0: /*salir*/ break;
            case 1: /*salir*/ break;
            case 2: /*salir*/ break;
            case 3: /*salir*/ break;
            case 4: /*salir*/ break;
            case 5: /*salir*/ break;
            case 6: /*salir*/ break;
            case 7: /*salir*/ break;
            case 8: /*salir*/ break;
            case 9: /*salir*/ break;
            case 10: /*salir*/ break;
            case 11: /*salir*/ break;
            case 12: /*salir*/ break;
            case 13: /*salir*/ break;
            case 14: /*salir*/ break;
            case 15: /*salir*/ break;
            case 16: /*salir*/ break;
            case 17: /*salir*/ break;
            case 18: /*salir*/ break;
        }
    }
    public void imprimrMenu(){
        System.out.println("0.- Salir");
        System.out.println("------------ESTADO------------");
        System.out.println("1.- Imprimir estado de los clientes");
        System.out.println("2.- Imprimir estado de bolsa");
        System.out.println("-------------BANCO------------");
        System.out.println("3.- Añadir Cliente");
        System.out.println("4.- Eliminar Cliente");
        System.out.println("5.- Realizar copia de seguridad");
        System.out.println("6.- Restaurar copia de seguridad");
        System.out.println("7.- Mejorar cliente a premium");
        System.out.println("8.- Solicitar recomendación de inversión");
        System.out.println("-------------BOLSA------------");
        System.out.println("9.- Añadir Empresa a la Bolsa");
        System.out.println("10.- Eliminar Empresa de la Bolsa");
        System.out.println("11.- Actualización de valores");
        System.out.println("12.- Realizar copia de seguridad");
        System.out.println("13.- Restaurar copia de seguridad");
        System.out.println("-----------OPERACIONES----------");
        System.out.println("14.- Solicitar compra de acciones");
        System.out.println("15.- Solicitar venta de acciones");
        System.out.println("16.- Solicitar actualización de valores");
        System.out.println("-------------BROKER------------");
        System.out.println("17.- Imprimir operaciones pendientes");
        System.out.println("18.- Ejecutar operaciones pendientes");

    }

}
