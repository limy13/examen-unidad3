package Sucursal;
import Usuarios.Clientes.Cliente;
import Usuarios.Clientes.Tarjetas.Solicitud;
import Usuarios.Empleados.Capturista;
import Usuarios.Empleados.Ejecutivo;
import Usuarios.Inversionista;
import Usuarios.Usuario;
import utils.UsuarioEnSesion;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private Banco banco = new Banco();
    private int decision = 0;
    private String contraseña = "INVERSIONISTA", cadena;
    private boolean band = false;

    public void iniciarSesion() {
        boolean datosCorrectos = true;
        do {
            System.out.println("\n---- Bienvenido al sistema del Banco ----\n");
            System.out.print("¿A qué sucursal desea acceder?: ");
            String sucursal = scanner.nextLine();
            if (sucursal.equalsIgnoreCase("Madero") || sucursal.equalsIgnoreCase("Acueducto")) {
                System.out.print("Ingresa tu usuario para continuar: ");
                String usuario = scanner.nextLine();
                System.out.print("Ingresa tu contraseña: ");
                String contrasena = scanner.nextLine();
                Usuario usuarioActual = banco.verificarInicioSesion(usuario, contrasena, sucursal);
                if(usuarioActual != null) {
                    UsuarioEnSesion.obtenerInstancia().setUsuarioActual(usuarioActual);
                    seleccionarMenu();
                }
                else {
                    System.out.println("\nUsuario o contraseña incorrectos.");
                    datosCorrectos = true;
                }
            }
            else {
                System.out.println("\nEsta sucursal no existe, por favor ingrese una sucursal existente");
            }
        }
        while(datosCorrectos);
    }


    private void seleccionarMenu() {
        Usuario usuario = UsuarioEnSesion.obtenerInstancia().getUsuarioActual();
        switch (usuario.getRol()) {
            case CLIENTE: menuCliente(usuario);
            case GERENTE: menuGerente(usuario.getNombreUsuario());
            case EJECUTIVO: menuEjecutivo(usuario.getNombreUsuario());
            case CAPTURISTA: menuCapturista(usuario.getNombreUsuario());
            case INVERSIONISTA: menuInversionista(usuario);
        }
    }


    private void menuCliente(Usuario usuario) {
        Cliente cliente = (Cliente) usuario;
        do {
            System.out.println("\n\n---- BIENVENIDO CLIENTE ----\n");
            System.out.println(usuario.getNombreUsuario());
            System.out.println("\n1. Pagar compra con tarjeta de crédito");
            System.out.println("2. Pagar tarjeta de crédito");
            System.out.println("3. Mostrar información de tarjetas");
            System.out.println("4. Depositar cantidad de tarjeta de débito");
            System.out.println("5. Retirar cantidad de tarjeta de débito");
            if(cliente.getTarjetaDebito().getSaldo() > 50000 && cliente.getTarjetaSimplicity() == null) {
                System.out.println("6. Solicitar tarjeta simplicity");
            }
            if(cliente.getTarjetaDebito().getSaldo() > 100000 && cliente.getTarjetaPlatino() == null) {
                System.out.println("7. Solicitar tarjeta platino");
            }
            if(cliente.getTarjetaDebito().getSaldo() > 200000 && cliente.getTarjetaOro() == null) {
                System.out.println("8. Solicitar tarjeta oro");
            }

            //agregar opciones para solicitud y ver status de solicitud

            System.out.println("9. Mostrar estatus de solicitudes de tarjetas de crédito");
            System.out.println("10. Cerrar sesión");
            System.out.print("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {

                case 1:
                    break;

                case 2:
                    cliente.pagarTarjetaCredito();
                    break;

                case 3:
                    cliente.mostrarDatosTarjeta();
                    break;

                case 4:
                    cliente.getTarjetaDebito().depositar();
                    break;

                case 5:
                    cliente.getTarjetaDebito().retirar();
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    scanner.nextLine();
                    System.out.println("\nSesión cerrada");
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 10);
    }


    private void menuEjecutivo(String usuario) {
        do {
            System.out.println("\n\n---- BIENVENIDO EJECUTIVO ----\n");
            System.out.println(usuario);
            System.out.println("\n1. Registrar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Modificar datos cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Solicitudes de tarjeta de crédito pendientes");
            System.out.print("6. Cerrar sesión");
            System.out.print("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {
                case 1:
                    Cliente.registrarCliente();
                    break;

                case 2:
                    Cliente.listarClientes();
                    break;

                case 3:
                    Cliente.modificarCliente();
                    break;

                case 4:
                    Cliente.eliminarCliente();
                    break;

                case 5:
                    Solicitud.solicitud();
                    break;

                case 6:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    scanner.nextLine();
                    System.out.println("\nSesión cerrada");
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 6);
    }


    private void menuGerente(String usuario) {
        do {
            System.out.println("\n\n---- BIENVENIDO GERENTE----\n");
            System.out.println(usuario);
            System.out.println("\n1. Registrar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Modificar datos cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Solicitudes de tarjeta de crédito pendientes");
            System.out.println("6. Registrar ejecutivo de cuenta");
            System.out.println("7. Consultar ejecutivo de cuenta");
            System.out.println("8. Modificar datos de ejecutivo de cuenta");
            System.out.println("9. Eliminar ejecutivo de cuenta");
            System.out.println("10. Registrar capturista");
            System.out.println("11. Consultar capturista");
            System.out.println("12. Modificar datos de capturista");
            System.out.println("13. Eliminar capturista");
            System.out.println("14. Registrar inversionista");
            System.out.println("15. Consultar inversionista");
            System.out.println("16. Modificar datos de inversionista");
            System.out.println("17. Eliminar inversionista");
            System.out.println("18. Mostrar inversiones recientes");
            System.out.println("19. Cerrar sesión");
            System.out.print("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {
                case 1:
                    Cliente.registrarCliente();
                    break;

                case 2:
                    Cliente.listarClientes();
                    break;

                case 3:
                    Cliente.modificarCliente();
                    break;

                case 4:
                    Cliente.eliminarCliente();
                    break;

                case 5:
                    Solicitud.solicitud();
                    break;

                case 6:
                    Ejecutivo.registrarEjecutivo();
                    break;

                case 7:
                    Ejecutivo.listarEjecutivos();
                    break;

                case 8:
                    break;

                case 9:
                    Ejecutivo.eliminarEjecutivo();
                    break;

                case 10:
                    Capturista.registrarCapturista();
                    break;

                case 11:
                    Capturista.listarCapturistas();
                    break;

                case 12:
                    break;

                case 13:
                    Capturista.eliminarCapturista();
                    break;

                case 14:
                    do {
                        System.out.print("\nIngrese la contraseña para poder acceder a esta función: ");
                        cadena = scanner.nextLine();
                        if(cadena.equals(contraseña)) {
                            Inversionista.registrarInversionista();
                            band = true;
                        }
                        else {
                            System.out.println("\nContraseña incorrecta, inténtelo de nuevo");
                        }
                    }
                    while(!band);
                    band = false;
                    break;

                case 15:
                    do {
                        System.out.print("\nIngrese la contraseña para poder acceder a esta función: ");
                        cadena = scanner.nextLine();
                        if(cadena.equals(contraseña)) {
                            Inversionista.listarInversionistas();
                            band = true;
                        }
                        else {
                            System.out.println("\nContraseña incorrecta, inténtelo de nuevo");
                        }
                    }
                    while(!band);
                    band = false;
                    break;

                case 16:
                    do {
                        System.out.print("\nIngrese la contraseña para poder acceder a esta función: ");
                        cadena = scanner.nextLine();
                        if(cadena.equals(contraseña)) {
                            //metodo para modficiar
                            band = true;
                        }
                        else {
                            System.out.println("\nContraseña incorrecta, inténtelo de nuevo");
                        }
                    }
                    while(!band);
                    band = false;
                    break;

                case 17:
                    do {
                        System.out.print("\nIngrese la contraseña para poder acceder a esta función: ");
                        cadena = scanner.nextLine();
                        if(cadena.equals(contraseña)) {
                            Inversionista.eliminarInversionista();
                            band = true;
                        }
                        else {
                            System.out.println("\nContraseña incorrecta, inténtelo de nuevo");
                        }
                    }
                    while(!band);
                    band = false;
                    break;

                case 18: 
                    do {
                        System.out.print("\nIngrese la contraseña para poder acceder a esta función: ");
                        cadena = scanner.nextLine();
                        if(cadena.equals(contraseña)) {
                            //metodo para ver inversionies
                            band = true;
                        }
                        else {
                            System.out.println("\nContraseña incorrecta, inténtelo de nuevo");
                        }
                    }
                    while(!band);
                    band = false;
                    break;

                case 19:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    scanner.nextLine();
                    System.out.println("\nSesión cerrada");
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 19);
    }


    private void menuCapturista(String usuario) {
        do {
            System.out.println("\n\n---- BIENVENIDO CAPTURISTA ----\n");
            System.out.println(usuario);
            System.out.println("\n1. Registrar ejecutivo de cuenta");
            System.out.println("2. Consultar ejecutivo de cuenta");
            System.out.println("3. Modificar de datos ejecutivo de cuenta");
            System.out.println("4. Eliminar ejecutivo de cuenta");
            System.out.println("5. Cerrar sesión");
            System.out.print("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {
                case 1:
                    Ejecutivo.registrarEjecutivo();
                    break;

                case 2:
                    Ejecutivo.listarEjecutivos();
                    break;

                case 3:
                    break;

                case 4:
                    Ejecutivo.eliminarEjecutivo();
                    break;

                case 5:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    scanner.nextLine();
                    System.out.println("\nSesión cerrada");
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 5);
        System.out.println("\nSesión cerrada");
    }


    private void menuInversionista(Usuario usuario) {
        Inversionista inversionista = (Inversionista) usuario;
        do {
            System.out.println("\n\n---- BIENVENIDO INVERSIONISTA ----\n");
            System.out.println(inversionista.getNombreUsuario());
            System.out.println("\n1. Proveer fondos");
            System.out.println("2. Mostrar inversiones realizadas");
            System.out.println("3. Cerrar sesión");
            System.out.println("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {
                case 1:
                    inversionista.realizarInversion(inversionista);
                    break;

                case 2:
                    inversionista.mostrarInversionesRealizadas();
                    break;

                case 3:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    scanner.nextLine();
                    System.out.println("\nSesión cerrada");
                    iniciarSesion();                    
                    break;
            }
        }
        while(decision != 3);
    }
}
