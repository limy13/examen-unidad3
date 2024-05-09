package Usuarios.Clientes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarjeta {

    private String numeroTarjeta, clabe, fechaCreacion, fechaVencimiento;
    private double saldo;
    private int cvv;
    private LocalDateTime ultimoMovimiento;

    public static void asignarTarjeta(double saldo) {
        LocalDate fechaFormateada = LocalDate.now();
        String partesFechaCreacion [] = String.valueOf(fechaFormateada).split("-");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
        String fechaCreacion = fechaFormateada.format(pattern);
        String fechaVencimiento = "" + partesFechaCreacion[0] + "/" + partesFechaCreacion[1] + "/" + String.valueOf(5 + Integer.parseInt(partesFechaCreacion[2]));
    }


}
