package Usuarios;
import Usuarios.utils.Rol;
import java.time.LocalDate;

public class Inversionista extends Usuario{

    public Inversionista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.INVERSIONISTA, rfc);
    }
}
