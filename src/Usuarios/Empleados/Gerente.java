package Usuarios.Empleados;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import java.time.LocalDate;

public class Gerente extends Usuario {

    private double salario;

    public Gerente(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc, double salario) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.GERENTE, rfc);
        this.salario = salario;
    }

    @Override
    public String getData() {
        return String.format(super.getData() + "\nSalario: %.2f" + salario);
    }
}
