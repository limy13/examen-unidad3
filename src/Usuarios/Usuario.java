package Usuarios;
import Usuarios.utils.Rol;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Sucursal.Banco;

public class Usuario {

    private String nombre, apellidos, ciudad, estado, curp, direcccion, sucursal, rfc, nombreUsuario, contraseña, fechaNacimiento;
    private LocalDate fechaIngreso;
    private Rol rol;

    public Usuario(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, Rol rol, String rfc) {
        this.rfc = rfc;
        this.sucursal = sucursal;
        this.direcccion = direcccion;
        this.curp = curp;
        this.estado = estado;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.fechaIngreso = LocalDate.now();
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Rol getRol() {
        return rol;
    }


    public static ArrayList datosComun(Rol rol) {
        ArrayList<String> datosComun = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String rolUsuario = rol == Rol.CLIENTE ? "Cliente" : rol == Rol.CAPTURISTA ? "Capturista" :
                rol == Rol.EJECUTIVO ? "Ejecutivo" : "Inversionista";

        System.out.println(String.format("\n\n---- Bienvenido al registro del %s", rolUsuario + "----"));
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nNombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        //validar que sean 2 apellidos
        String apellido = scanner.nextLine();
        System.out.print("Fecha de nacimiento: ");
        int dia, mes, año;
        boolean band = false;
        String fechaActual = String.valueOf(LocalDate.now());
        String [] partes = fechaActual.split("-");
        do {
            System.out.print("\nAño: ");
            año = scanner.nextInt();
            
            if (año > Integer.parseInt(partes[0])) {
                System.out.println("\nIngrese un año valido");
            }
            else{
                band = true;
            }
        }
        while(!band);
        band = false;
        do {
            System.out.print("Mes: ");
            mes = scanner.nextInt();
            if (mes > 12 || mes < 1) {
                System.out.println("\nIngrese un mes valido");
            }
            else {
                band = true;
            }
        }
        while (!band);
        //validar dia
        System.out.print("Día: ");
        dia = scanner.nextInt();
        LocalDate fechaNacimiento = LocalDate.of(año, mes, dia);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
        String fechaFormateada = fechaNacimiento.format(pattern);
        scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("CURP: ");
        String curp = scanner.nextLine();
        String nombreUsuario = registrarNombreUsuario();
        String rfc = generarRfc(fechaFormateada, nombre, apellido);
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        datosComun.addAll(Arrays.asList(nombre, apellido, fechaFormateada, estado, ciudad, direccion, curp.toUpperCase(), nombreUsuario, contraseña, rfc, Banco.sucu));
        return datosComun;
    }

    
    public static String registrarNombreUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = "";
        boolean nombreUsuarioExistente = true;
        //cambiar a que no haya usuaris repetidos en las 2 sucurslaes
        Map<Rol, ArrayList<Usuario>> lista = Banco.sucursal.get(Banco.sucu);
        do {
            System.out.print("\nIngresa el nombre de usuario: ");
            nombreUsuario = scanner.nextLine();
            nombreUsuarioExistente = false;
            for(ArrayList<Usuario> usuarios : lista.values()) {
                for(Usuario usuario : usuarios) {
                    if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                        nombreUsuarioExistente = true;
                        break;
                    }
                }
            }
            if(nombreUsuarioExistente) {
                System.out.println("\nYa existe un registro con ese nombre de usuario. Intenta de nuevo.");
            }
        }
        while(nombreUsuarioExistente);
        return nombreUsuario;
    }

    public static String generarRfc(String fechaNacimiento, String nombre, String apellido) {
        String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        String rfc;
        Random random = new Random();
        String homoclave = "";
        for (int i = 0; i < 3; i++) {
            char caracter = caracteres.charAt(random.nextInt(caracteres.length()));
            homoclave += caracter;
        }
        String [] partesNacimiento = fechaNacimiento.split("/");
        String [] partesApellido = apellido.split(" ");
        return rfc = ("" + apellido.charAt(0) + apellido.charAt(1) + partesApellido[1].charAt(0) + nombre.charAt(0) +
        partesNacimiento[2].charAt(2) + partesNacimiento[2].charAt(3) + partesNacimiento[1] + partesNacimiento[0] + homoclave).toUpperCase();
    }

    public String getData() {
        return String.format("\nNombre: %s %s \nSucursal a la que pertenece: %s \nRFC: %s \nCURP: %s \nFecha de nacimiento: %s \nEstado: %s \nCiudad: %s \nDireccion: %s",
                nombre, apellidos, sucursal, rfc, curp, fechaNacimiento, estado, ciudad, direcccion) + "\nRol: " + rol + "\nFecha de ingreso: " + fechaIngreso;
    }
}
