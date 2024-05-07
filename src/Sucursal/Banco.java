package Sucursal;
import Usuarios.Empleados.Gerente;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import java.util.*;

public class Banco {

    public static final Map<String, Map<Rol, ArrayList<Usuario>>> sucursal = new HashMap<>(); //hashmaps que contiene los usuarios de las 2 sucursales
    private String usuarioEnSesion;
    public static String sucu;

    public Banco() {
        sucursal.put("Madero", new HashMap<>());
        sucursal.get("Madero").put(Rol.GERENTE, new ArrayList<Usuario>());
        Gerente gerente = new Gerente("Madero", "Jazmín #579", "GHEJGRREJDJ343", "Michoacán", "Morelia", "12/12/2000", "Méndex Carillo", "Húgo", "hugoMC", "hugo13", "MECH0012121HO",10000.56);
        sucursal.get("Madero").get(Rol.GERENTE).add(gerente);
        sucursal.put("Acueducto", new HashMap<>());
        sucursal.get("Acueducto").put(Rol.GERENTE, new ArrayList<Usuario>());
        Gerente gerente2 = new Gerente("Acueducto", "Margaritas #57", "HJFDKFJDKDK", "Michoacán", "Morelia", "28/10/1985", "Reyes Cabrera", "Sofía", "soff1", "mar", "RECS851028B7U",20000);
        sucursal.get("Acueducto").get(Rol.GERENTE).add(gerente2);
    }

    public static String getSucu() {
        return sucu;
    }

    Usuario verificarInicioSesion(String nombreUsuario, String contraseña, String key) {
        if(key.equalsIgnoreCase("Madero")) {
            sucu = "Madero";
        }
        else {
            sucu = "Acueducto";
        }
        Map<Rol, ArrayList<Usuario>> lista = sucursal.get(sucu);
        for (ArrayList<Usuario> usuarios : lista.values()) {
            for (Usuario usuario : usuarios) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    if (usuario.getContraseña().equals(contraseña)) {
                        usuarioEnSesion = usuario.getNombreUsuario();
                        return usuario;
                    }
                }
            }
        }
        return null;
    }


    public static ArrayList datosComun(Rol rol) {
        ArrayList<String> datosComun = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String rolUsuario = rol == Rol.CLIENTE ? "Cliente" : rol == Rol.CAPTURISTA ? "Capturista" :
                rol == Rol.EJECUTIVO ? "Ejecutivo" : "Inversionista";

        System.out.println(String.format("\nBienvenido al registro del %s", rolUsuario));
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nNombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Fecha de nacimiento (DD/MM/YYYY): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.println("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.println("CURP: ");
        String curp = scanner.nextLine();
        String nombreUsuario = registrarNombreUsuario();
        String rfc = generarRfc(fechaNacimiento, nombre, apellido);
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        datosComun.addAll(Arrays.asList(nombre, apellido, fechaNacimiento, estado, ciudad, direccion, curp.toUpperCase(), nombreUsuario, contraseña, rfc, sucu));
        return datosComun;
    }


    public static String registrarNombreUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = "";
        boolean nombreUsuarioExistente = true;
        Map<Rol, ArrayList<Usuario>> lista = sucursal.get(sucu);
        do {
            System.out.println("\nIngresa el nombre de usuario: ");
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
        String [] partesApellido = fechaNacimiento.split(" ");
        return rfc = (apellido.charAt(0) + apellido.charAt(1) + partesApellido[0].charAt(0) + nombre.charAt(0)
                + partesNacimiento[2].charAt(2) + partesNacimiento[2].charAt(3) + partesNacimiento[1] + partesNacimiento[0] + homoclave).toUpperCase();
    }
}
