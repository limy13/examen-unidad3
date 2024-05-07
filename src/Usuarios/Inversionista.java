package Usuarios;
import Usuarios.utils.Rol;
import java.time.LocalDate;

public class Inversionista extends Usuario{

    public Inversionista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, LocalDate fechaIngreso, String nombreUsuario, String contraseña, String rfc) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, fechaIngreso, nombreUsuario, contraseña, Rol.INVERSIONISTA, rfc);
    }
}
