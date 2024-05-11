package Usuarios.Empleados;
import Sucursal.Banco;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejecutivo extends Usuario {

    private double salario;

    public Ejecutivo(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc, double salario) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.EJECUTIVO, rfc);
        this.salario = salario;
    }

    @Override
    public String getData() {
        return String.format(super.getData() + "\nSalario: %.2f" + salario);
    }

    public static void registrarEjecutivo() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.EJECUTIVO);
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
        System.out.println("Salario: ");
        double salario = scanner.nextDouble();

        System.out.println("\n¿Cancelar registro? (1 = si, 2 = no)");
        int decision = scanner.nextInt();
        if(decision == 1) {
            System.out.println("\nRegistro cancelado");
        }
        else {
            if(!Banco.sucursal.get(Banco.sucu).containsKey(Rol.EJECUTIVO)) {
                Banco.sucursal.get(Banco.sucu).put(Rol.EJECUTIVO, new ArrayList<Usuario>());
            }
    
            Ejecutivo ejecutivo = new Ejecutivo(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc, salario);
            Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).add(ejecutivo);
            System.out.println("\nEjecutivo registrado exitosamente");
        }
    }

    public static void listarEjecutivos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Consultar ejecutivos de cuenta -----\n");
        System.out.println("1. Listar ejecutivos de cuenta");
        System.out.println("2. Consultar ejecutivo de cuenta");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).isEmpty()) {
            System.out.println("\nNo hay ejecutivos registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nEjecutivos registrados:");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
                System.out.println("\n---- Ejecutivos " + x + " ----");
                System.out.println(usuario.getData());
                x++;
            }
        }
        else {
            boolean band = false;
            System.out.println("\n---- Consultar ejecutivo de cuenta ----\n");
            System.out.print("Ingrese el nombre de usuario del ejecutivo que desea consultar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Ejecutivo ----");
                    System.out.println(usuario.getData());
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún ejecutivo");
            }
        }
    }

    public static void eliminarEjecutivo() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar ejecutivo de cuenta ----\n");
        System.out.print("Ingrese el nombre de usuario del ejecutivo que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
            if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).remove(usuario);
                System.out.println("\nEjecutivo eliminado");
                return;
            }
        }
        if(!band) {
            System.out.println("\nEste nombre de usuario no pertenece a ningún ejecutivo");
        }
    }
}
