package Sucursal;
import Usuarios.Clientes.Cliente;
import Usuarios.Empleados.Ejecutivo;
import Usuarios.Usuario;
import utils.UsuarioEnSesion;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private Banco banco = new Banco();
    private int decision = 0;

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
            case CLIENTE: menuCliente(usuario.getNombreUsuario());
            case GERENTE: menuGerente(usuario.getNombreUsuario());
            case EJECUTIVO: menuEjecutivo(usuario.getNombreUsuario());
            case CAPTURISTA: menuCapturista(usuario.getNombreUsuario());
            case INVERSIONISTA: menuInversionista(usuario.getNombreUsuario());
        }
    }


    private void menuCliente(String usuario) {
        do {
            System.out.println("\n\n---- BIENVENIDO CLIENTE ----\n");
            System.out.println(usuario);
            System.out.println("\n1. Consultar libros");
            System.out.println("2. Consultar rentas");
            System.out.println("3. Mostrar mis datos");
            System.out.println("4. Editar mi información");
            System.out.println("5. Cerrar sesión");
            System.out.print("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
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
    }


    private void menuEjecutivo(String usuario) {
        do {
            System.out.println("\n\n---- BIENVENIDO EJECUTIVO ----\n");
            System.out.println(usuario);
            System.out.println("\n1. Registrar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Modificar datos cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Solicitudes de tarjeta de crédito");
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
                    break;

                case 4:
                    break;

                case 5:
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
            System.out.println("5. Solicitudes de tarjeta de crédito");
            System.out.println("6. Registrar ejecutivo de cuenta");
            System.out.println("7. Consultar ejecutivo de cuenta");
            System.out.println("8. Modificar datos de ejecutivo de cuenta");
            System.out.println("9. Eliminar ejecutivo de cuenta");
            System.out.println("10. Registrar capturista");
            System.out.println("11. Consultar capturista");
            System.out.println("12. Modificar datos de capturista");
            System.out.println("13. Cerrar sesión");
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
                    break;

                case 4:
                    Cliente.eliminarCliente();
                    break;

                case 5:
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
                    break;

                case 11:
                    break;

                case 12:
                    break;

                case 13:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    scanner.nextLine();
                    System.out.println("\nSesión cerrada");
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 13);
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


    private void menuInversionista(String usuario) {
        do {
            System.out.println("\n\n---- BIENVENIDO INVERSIONISTA ----\n");
            System.out.println(usuario);
            System.out.println("\n1. Consultar libros");
            System.out.println("2. Consultar rentas");
            System.out.println("3. Mostrar mis datos");
            System.out.println("4. Editar mi información");
            System.out.println("5. Cerrar sesión");
            System.out.println("\nIngrese opción: ");
            decision = scanner.nextInt();

            switch(decision) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
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
    }
}
