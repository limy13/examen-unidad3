package Usuarios.Empleados;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Sucursal.Banco;
import Usuarios.utils.Rol;

public class Capturista extends Usuario {

    private double salario;

    public Capturista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc, double salario) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.CAPTURISTA, rfc);
        this.salario = salario;
    }

    @Override
    public String getData() {
        return String.format(super.getData() + "\nSalario: %.2f" + salario);
    }

    public static void registrarCapturista() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.CAPTURISTA);
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
            if(!Banco.sucursal.get(Banco.sucu).containsKey(Rol.CAPTURISTA)) {
                Banco.sucursal.get(Banco.sucu).put(Rol.CAPTURISTA, new ArrayList<Usuario>());
            }

            Capturista capturista = new Capturista(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc, salario);
            Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).add(capturista);
            System.out.println("\nCapturista registrado exitosamente");
        }
    }

    public static void listarCapturistas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Consultar capturistas -----\n");
        System.out.println("1. Listar capturistas");
        System.out.println("2. Consultar capturistas");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).isEmpty()) {
            System.out.println("\nNo hay capturistas registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nCapturistas registrados");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
                System.out.println("\n---- Capturistas " + x + " ----");
                System.out.println(usuario.getData());
            }
        }
        else {
            boolean band = false;
            System.out.println("\n---- Consultar capturista ----\n");
            System.out.print("Ingrese el nombre de usuario del capturista que desea consultar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Capturista ----");
                    System.out.println(usuario.getData());
                    band = true;
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún capturista");
            }

        }
    }

    public static void eliminarCapturista() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar capturista ----\n");
        System.out.print("Ingrese el nombre de usuario del capturista que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
            if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).remove(usuario);
                System.out.println("\nCapturista eliminado");
                return;
            }
        }
        if(!band) {
            System.out.println("\nEste nombre de usuario no pertenece a ningún capturista");
        }
    }
}
