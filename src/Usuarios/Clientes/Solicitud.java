package Usuarios.Clientes;
import java.time.LocalDate;

public class Solicitud {
    
    private String nombre, apellido;
    private boolean status;
    private double monto;
    private LocalDate fechaSolicitud;
    //id
    //tipos de tarjetas

    public Solicitud(String nombre, String apellido, double monto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.monto = monto;
        this.fechaSolicitud = LocalDate.now();
    }
}
