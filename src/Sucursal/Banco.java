package Sucursal;
import Usuarios.Empleados.Gerente;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import java.util.*;

public class Banco {

    public static final Map<String, Map<Rol, ArrayList<Usuario>>> sucursal = new HashMap<>(); //hashmaps que contiene los usuarios de las 2 sucursales
    public static final ArrayList<String> inversionesRealizadas = new ArrayList<>();
    private String usuarioEnSesion;
    public static String sucu;

    public Banco() {
        sucursal.put("Madero", new HashMap<>());
        sucursal.get("Madero").put(Rol.GERENTE, new ArrayList<Usuario>());
        Gerente gerente = new Gerente("Madero", "Jazmín #579", "GHEJGRREJDJ343", "Michoacán", "Morelia", "12/12/2000", "Méndex Carillo", "Húgo", "hugoMC", "hugo13", "MECH0012121HO",10000.56);
        sucursal.get("Madero").get(Rol.GERENTE).add(gerente);
        sucursal.get("Madero").put(Rol.CLIENTE, new ArrayList<Usuario>());
        sucursal.get("Madero").put(Rol.INVERSIONISTA, new ArrayList<Usuario>());
        sucursal.get("Madero").put(Rol.CAPTURISTA, new ArrayList<Usuario>());
        sucursal.get("Madero").put(Rol.EJECUTIVO, new ArrayList<Usuario>());

        sucursal.put("Acueducto", new HashMap<>());
        sucursal.get("Acueducto").put(Rol.GERENTE, new ArrayList<Usuario>());
        Gerente gerente2 = new Gerente("Acueducto", "Margaritas #57", "HJFDKFJDKDK", "Michoacán", "Morelia", "28/10/1985", "Reyes Cabrera", "Sofía", "soff1", "mar", "RECS851028B7U",20000);
        sucursal.get("Acueducto").get(Rol.GERENTE).add(gerente2);
        sucursal.get("Acueducto").put(Rol.CLIENTE, new ArrayList<Usuario>());
        sucursal.get("Acueducto").put(Rol.INVERSIONISTA, new ArrayList<Usuario>());
        sucursal.get("Acueducto").put(Rol.CAPTURISTA, new ArrayList<Usuario>());
        sucursal.get("Acueducto").put(Rol.EJECUTIVO, new ArrayList<Usuario>());
    }

    Usuario verificarInicioSesion(String nombreUsuario, String contraseña, String key) {
        if(key.equalsIgnoreCase("Madero")) {
            sucu = "Madero";
        }
        else {
            sucu = "Acueducto";
        }
        for (ArrayList<Usuario> usuarios : sucursal.get(sucu).values()) {
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
}
