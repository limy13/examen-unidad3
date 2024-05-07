package Usuarios.Empleados;
import Usuarios.Usuario;
import java.time.LocalDate;
import Usuarios.utils.Rol;

public class Capturista extends Usuario {

    private double salario;

    public Capturista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, LocalDate fechaIngreso, String nombreUsuario, String contraseña, String rfc, double salario) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, fechaIngreso, nombreUsuario, contraseña, Rol.CAPTURISTA, rfc);
        this.salario = salario;
    }

    @Override
    public String getData() {
        return String.format(super.getData() + "\nSalario: %.2f" + salario);
    }
}
