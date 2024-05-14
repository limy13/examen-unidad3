package Usuarios;
import Usuarios.utils.Rol;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        System.out.print("Apellido paterno: ");
        String apellidoP = scanner.nextLine();
        System.out.print("Apellido materno: ");
        String apellidoM = scanner.nextLine();

        System.out.print("Fecha de nacimiento: ");
        //String fechaNacimientoString = validarFecha();
        System.out.print("\nAño: ");
        int año = scanner.nextInt();
        System.out.print("Mes: ");
        int mes = scanner.nextInt();
        System.out.print("Día: ");
        int dia = scanner.nextInt();
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
        String rfc = generarRfc(fechaFormateada, nombre, apellidoP.concat(" ").concat(apellidoM));
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        datosComun.addAll(Arrays.asList(nombre, apellidoP.concat(" ").concat(apellidoM), fechaFormateada, estado, ciudad, direccion, curp.toUpperCase(), nombreUsuario, contraseña, rfc, Banco.sucu));
        return datosComun;
    }


    public static String validarFecha() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa una fecha con formato yyyy-MM-dd:");
        String fechaIngresada = scanner.nextLine();

        // Formateador de fecha para analizar la fecha ingresada
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Convertir la fecha ingresada a un objeto Date
            Date fechaIngresadaDate = sdf.parse(fechaIngresada);

            // Obtener la fecha actual
            Date fechaActual = new Date();

            // Comparar la fecha ingresada con la fecha actual
            if (fechaIngresadaDate.before(fechaActual) || fechaIngresadaDate.equals(fechaActual)) {
                // La fecha ingresada es válida
                return sdf.format(fechaIngresadaDate); // Devolver la fecha formateada como cadena
            } else {
                // La fecha ingresada aún no sucede
                return "\nLa fecha ingresada aun no sucede";
            }
        } catch (ParseException e) {
            // Error al analizar la fecha ingresada
            return "\nError: Formato de fecha incorrecto.";
        }
    }

    
    public static String registrarNombreUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = "";
        boolean nombreUsuarioExistente = true;
        do {
            System.out.print("\nIngresa el nombre de usuario: ");
            nombreUsuario = scanner.nextLine();
            nombreUsuarioExistente = false;
            for(Map<Rol, ArrayList<Usuario>> lista : Banco.sucursal.values()) {
                for(ArrayList<Usuario> usuarios : lista.values()) {
                    for(Usuario usuario : usuarios) {
                        if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                            nombreUsuarioExistente = true;
                            break;
                        }
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

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRfc(String fechaNacimiento, String nombre, String apellidos) {
        this.rfc = generarRfc(fechaNacimiento, nombre, apellidos);
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setDirecccion(String direcccion) {
        this.direcccion = direcccion;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getData() {
        return String.format("\nNombre: %s %s \nSucursal a la que pertenece: %s \nRFC: %s \nCURP: %s \nFecha de nacimiento: %s \nEstado: %s \nCiudad: %s \nDireccion: %s",
                nombre, apellidos, sucursal, rfc, curp, fechaNacimiento, estado, ciudad, direcccion) + "\nRol: " + rol + "\nFecha de ingreso: " + fechaIngreso;
    }
}
