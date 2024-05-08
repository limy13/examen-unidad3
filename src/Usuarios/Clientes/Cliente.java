package Usuarios.Clientes;

import Sucursal.Banco;
import Usuarios.Usuario;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Usuario {

    public Cliente(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, LocalDate.now(), nombreUsuario, contraseña, Rol.CLIENTE, rfc);
    }

    public static void registrarCliente() {
        ArrayList<String> datos = datosComun(Rol.CLIENTE);
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

        //agregar metodo para asignar tarjeta
        Cliente cliente = new Cliente(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc);

        if(!Banco.sucursal.get(Banco.sucu).containsKey(Rol.CLIENTE)) {
            Banco.sucursal.get(Banco.sucu).put(Rol.CLIENTE, new ArrayList<Usuario>());
        }

        Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).add(cliente);
    }

    public static void listarClientes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Consultar clientes -----\n");
        System.out.println("1. Listar clientes");
        System.out.println("2. Consultar cliente");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).isEmpty()) {
            System.out.println("\nNo hay clientes registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nClientes registrados");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
                System.out.println("\n---- Cliente " + x + "----\n");
                System.out.println(usuario.getData());
                System.out.println("\n* Datos sobre tarjeta *\n");
                //imprimir dtos tarjeta
            }
        }
        else {
        System.out.println("\n ---- Consultar cliente ----\n");
        System.out.print("Ingrese el nombre de usuario del cliente que desea consultar: ");
        String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Cliente ----\n");
                    System.out.println(usuario.getData());
                    System.out.println("\n* Datos sobre tarjeta *\n");
                    //imprimir dtos tarjeta
                    return;
                }
            }
        }
    }

    public static void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar cliente ----\n");
        System.out.print("Ingrese el nombre de usuario del cliente que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
            if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).remove(usuario);
                System.out.println("\nCliente eliminado");
                return;
            }
        }
        if(!band) {
            System.out.println("Este nombre de usuario no pertenece a ningún cliente");
        }
    }
}

