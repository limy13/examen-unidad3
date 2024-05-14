package Usuarios;
import Usuarios.utils.Rol;
import java.time.LocalDate;
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

        System.out.println(String.format("\n\n---- Bienvenido al registro del %s", rolUsuario + " ----"));
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nNombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido paterno: ");
        String apellidoP = scanner.nextLine();
        System.out.print("Apellido materno: ");
        String apellidoM = scanner.nextLine();
        String fechaNacimiento = validarFecha();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("CURP: ");
        String curp = scanner.nextLine();
        String nombreUsuario = registrarNombreUsuario();
        String rfc = generarRfc(String.valueOf(fechaNacimiento), nombre, apellidoP.concat(" ").concat(apellidoM));
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        datosComun.addAll(Arrays.asList(nombre, apellidoP.concat(" ").concat(apellidoM), fechaNacimiento, estado, ciudad, direccion, curp.toUpperCase(), nombreUsuario, contraseña, rfc, Banco.sucu));
        return datosComun;
    }


    public static String validarFecha() {
        Scanner scanner = new Scanner(System.in);
        boolean fechaValida = false;
        String fechaIngresada = "";

        while (!fechaValida) {
            System.out.println("Ingresa una fecha con formato yyyy-MM-dd:");
            fechaIngresada = scanner.nextLine();

            String[] partesFecha = fechaIngresada.split("-");
            int año = Integer.parseInt(partesFecha[0]);
            int mes = Integer.parseInt(partesFecha[1]);
            int dia = Integer.parseInt(partesFecha[2]);


            // si ano es == a el actual hacer esto
            if (año == LocalDate.now().getYear()){
                if (mes >= 1 && mes <= 12 && mes <= LocalDate.now().getMonthValue() ) {
                    // Validar el día
                    if (dia >= 1 && dia <= obtenerDiasEnMes(mes)) {
                        fechaValida = true;
                    } else {
                        System.out.println("Error: El día ingresado no corresponde al mes especificado.");
                    }
                } else {
                    System.out.println("Error: El mes ingresado no es válido.");
                }

            }

            




            // Verificar que el año no sea futuro pero tampoco presente
            if (año < LocalDate.now().getYear() && año > 1907 ) {
                // Validar el mes

                if (mes >= 1 && mes <= 12  ) {
                    // Validar el día
                    if (dia >= 1 && dia <= obtenerDiasEnMes(mes)) {
                        fechaValida = true;
                    } else {
                        System.out.println("Error: El día ingresado no corresponde al mes especificado.");
                    }
                } else {
                    System.out.println("Error: El mes ingresado no es válido.");
                }
            } else {
                System.out.println("Error: El año ingresado no puede ser un año futuro o muy muy viejo.");
            }//fin if
        }//while

      return fechaIngresada;
    }//metodovalidar

    
    public static int obtenerDiasEnMes(int mes) {
        switch (mes) {
            case 2:
                return 28; 
                 
            case 4:
                return 30; // Abril, junio, septiembre y noviembre tienen 30 días

            case 6: 
                return 30;

            case 9: 
                return 30;

            case 11: 
                return 30;

            default:
                return 31; // Los demás meses tienen 31 días
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
        String [] partesNacimiento = fechaNacimiento.split("-");
        String [] partesApellido = apellido.split(" ");
        return rfc = ("" + apellido.charAt(0) + apellido.charAt(1) + partesApellido[1].charAt(0) + nombre.charAt(0) +
        partesNacimiento[0].charAt(2) + partesNacimiento[0].charAt(3) + partesNacimiento[1] + partesNacimiento[2] + homoclave).toUpperCase();
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
