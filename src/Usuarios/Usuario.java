package Usuarios;
import Usuarios.utils.Rol;
import java.time.LocalDate;

public class Usuario {

    private String nombre, apellidos, ciudad, estado, curp, direcccion, sucursal, rfc, nombreUsuario, contraseña, fechaNacimiento;
    private LocalDate fechaIngreso;
    private Rol rol;

    public Usuario(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, LocalDate fechaIngreso, String nombreUsuario, String contraseña, Rol rol, String rfc) {
        this.rfc = rfc;
        this.sucursal = sucursal;
        this.direcccion = direcccion;
        this.curp = curp;
        this.estado = estado;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
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

    public String getData() {
        return String.format("\nNombre: %s %s \nSucursal a la que pertenece: %s \nRFC: %s \nCURP: %s \nFecha de nacimiento: %s \nEstado: %s \nCiudad: %s \nDireccion: %s",
                nombre, apellidos, sucursal, rfc, curp, fechaNacimiento, estado, ciudad, direcccion) + "Rol: " + rol;
    }
}
