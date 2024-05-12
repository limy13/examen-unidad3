package Usuarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Sucursal.Banco;
import Usuarios.utils.Rol;

public class Inversionista extends Usuario{

    private ArrayList<String> inversionesRealizadas = new ArrayList<>();

    public Inversionista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.INVERSIONISTA, rfc);
    }

    public static void registrarInversionista() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.INVERSIONISTA);
        String nombre = datos.get(0);
        String apellido = datos.get(1);
        String fechaNacimiento = datos.get(2);
        String estado = datos.get(3);
        String ciudad = datos.get(4);
        String direccion = datos.get(5);
        String curp = datos.get(6);
        String nombreUsuario = datos.get(7);
        String contraseña = datos.get(8);
        String rfc = datos.get(9);
        String sucursal = datos.get(10);

        Inversionista inversionista = new Inversionista(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc);

        System.out.println("\n¿Cancelar registro? (1 = si, 2 = no)");
        int decision = scanner.nextInt();
        if(decision == 1) {
            System.out.println("\nRegistro cancelado");
        }
        else {
            if(!Banco.sucursal.get(Banco.sucu).containsKey(Rol.INVERSIONISTA)) {
                Banco.sucursal.get(Banco.sucu).put(Rol.INVERSIONISTA, new ArrayList<Usuario>());
            }
    
            Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).add(inversionista);
            System.out.println("\nInversionista registrado exitosamente");
        }
    }

    public static void listarInversionistas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Consultar inversionistas -----\n");
        System.out.println("1. Listar inversionistas");
        System.out.println("2. Consultar inversionista");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).isEmpty()) {
            System.out.println("\nNo hay inversionistas registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nInversionistas registrados:");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
                System.out.println("\n---- Inversionista " + x + " ----");
                System.out.println(usuario.getData());
                x++;
            }
        }
        else {
            boolean band = false;
            System.out.println("\n---- Consultar inversionista ----\n");
            System.out.print("Ingrese el nombre de usuario del inversionista que desea consultar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Inversionista ----");
                    System.out.println(usuario.getData());
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún inversionista");
            }
        }
    }

    public static void eliminarInversionista() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar inversionista ----\n");
        System.out.print("Ingrese el nombre de usuario del inversionista que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
            if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).remove(usuario);
                System.out.println("\nInversionista eliminado");
                return;
            }
        }
        if(!band) {
            System.out.println("\nEste nombre de usuario no pertenece a ningún inversionista");
        }
    }

    public void mostrarInversionesRealizadas() {
        System.out.println("\n---- Inversiones realizadas ----\n");
        int x = 1;
        for(String i : inversionesRealizadas) {
            System.out.println("**** Inversión " + x + " ****");
            System.out.println(i);
        }
    }

    public void realizarInversion(Inversionista inversionista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Inversiones ----\n");
        System.out.print("Ingrese la cantidad que desea invertir: ");
        double cantidad = scanner.nextDouble();
        inversionesRealizadas.add(String.format("\nCantidad aportada: %.2f", cantidad + "\nFecha del movimiento: " + LocalDate.now()));
        Banco.inversionesRealizadas.add(String.format("Nombre del inversionista: %s %s \nCantidad aportada: %.2f", inversionista.getNombre(), inversionista.getApellidos(), cantidad + "\nSucursal: " + Banco.sucu + "\nFecha del movimiento: " + LocalDate.now())); 
    }

}
