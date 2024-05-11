package Usuarios.Clientes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Tarjeta {

    private Random random = new Random();
    private double saldo;
    private LocalDate fechaFormateada;
    private String cvv, fechaCreacion, fechaVencimiento, fechaUltimoMovimiento, clabe, caracteres = "0123456789";
    private String numeroTarjeta, numeroCuenta = "";
    private DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-YYYY"); 

    public Tarjeta(double saldo) {
        this.saldo = saldo;
        this.cvv = generarCvv();
        this.fechaCreacion = String.valueOf(LocalDate.now());
        this.fechaVencimiento = formatoFechaVencimiento();
        this.fechaUltimoMovimiento = formatoFechaUltimoMovimiento();
        this.clabe = generarClabe();
        this.numeroTarjeta = generarNumeroTarjeta();
    }

    private String formatoFechaVencimiento() {
        String partesFechaVencimiento [] = String.valueOf(fechaCreacion).split("-");
        return "" + String.valueOf(5 + Integer.parseInt(partesFechaVencimiento[0])) + "-" + partesFechaVencimiento[1] + "-" + partesFechaVencimiento[2];
    }

    private String formatoFechaUltimoMovimiento() {
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");
        //ver que onda con esto
        ZoneId zonaHoraria = ZoneId.of("America/Mexico_City");
        LocalTime hora = LocalTime.now(zonaHoraria);
        String horaUltimoMovimiento = hora.format(pattern2);
        String fechaUltimoMovimiento = String.valueOf(LocalDate.now());
        return String.format("\nFecha del último movimiento: %s \nHora del último movimiento: %s", fechaUltimoMovimiento, horaUltimoMovimiento);
    }

    private String generarNumeroTarjeta() {
        String numero = "1";
        for (int i = 0; i < 3; i++) {
            char caracter = caracteres.charAt(random.nextInt(caracteres.length() - 1));
            numero += caracter;
        }
        return numero.concat(" ").concat(this.numeroCuenta).concat(String.valueOf(caracteres.charAt(random.nextInt(caracteres.length() - 1))));
    }

    private String generarCvv() {
        String cvv = "";
        for (int i = 0; i < 3; i++) {
            char caracter = caracteres.charAt(random.nextInt(caracteres.length() - 1));
            cvv += caracter;
        }
        return cvv;
    }

    private String generarClabe() {
        String clabe = "458 710 ";
        int x = 4;
        for(int i = 0; i < 11; i++) {
            char caracter = caracteres.charAt(random.nextInt(caracteres.length() - 1));
            clabe += caracter;
            if(i == x) {
                numeroCuenta += " ";
                x += 4;
            }
            numeroCuenta += caracter;
        }
        return clabe + " " + String.valueOf(caracteres.charAt(random.nextInt(caracteres.length() - 1)));
    }

    public void mostrarDatosTarjeta() {
        System.out.println("\n---- Datos tarjeta de débito ----\n");
        System.out.println(String.format("Número de tarjeta: %s \nFecha de creación: %s \nFecha de vencimiento: %s \nSaldo: %.2f MX \nCVV: %s \nClabe interbancaria: %s %s",
        numeroTarjeta, fechaCreacion, fechaVencimiento, saldo, cvv, clabe, fechaUltimoMovimiento));
    }

}
