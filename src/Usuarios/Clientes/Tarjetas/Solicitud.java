package Usuarios.Clientes.Tarjetas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Usuarios.Clientes.Tarjetas.utils.EstadoSolicitud;
import Usuarios.Clientes.Tarjetas.utils.TiposTarjeta;

public class Solicitud {
    
    private static ArrayList<Solicitud> solicitudesPendientes = new ArrayList<>();

    private static int CANTIDAD_SOLICITUDES = 1;
    private EstadoSolicitud estado;
    private int idCliente, idSolicitud;
    private String nombre, apellido;
    private double monto;
    private LocalDate fechaSolicitud;
    private TiposTarjeta tipoTarjeta;

    public Solicitud(int idCliente, String nombre, String apellido, double monto, TiposTarjeta tipoTarjeta) {
        this.idCliente = idCliente;
        this.idSolicitud = CANTIDAD_SOLICITUDES;
        this.nombre = nombre;
        this.apellido = apellido;
        this.monto = monto;
        this.tipoTarjeta = tipoTarjeta;
        this.fechaSolicitud = LocalDate.now();
        this.estado = EstadoSolicitud.PENDIENTE;
        CANTIDAD_SOLICITUDES++;
    }

    public static void agregarSolicitud(Solicitud solicitud) {
        solicitudesPendientes.add(solicitud);
    }

    public int getId() {
        return idSolicitud;
    }

    public void setStatus(EstadoSolicitud status) {
        estado = status;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void aprobar() {
        estado = EstadoSolicitud.APROBADA;
    }

    public void rechazar() {
        estado = EstadoSolicitud.RECHAZADA;
    }

    public String getData() {
        return String.format("Nombre del aspirante: %s %s \nID del cliente: %d \nID de la solicitud: %d \nMonto disponible en tarjeta de débito: %.2f", 
        nombre, apellido, idCliente, idSolicitud, monto + "\nTipo de tarjeta solicitada: " + tipoTarjeta);
    }

    public static void solicitud() {
        boolean band = true, band2 = false;
        Scanner scanner = new Scanner(System.in);
        int x = 1;
        System.out.println("\n---- Solicitudes pendientes por aprobar ----\n");
        for(Solicitud i : solicitudesPendientes) {
            System.out.println("\n**** Solicitud " + x + " ****");
            System.out.println(i.getData());
            x++;
        }
        do {
        System.out.println("\n1. Aprobar solicitud");
        System.out.println("2. Rechazar");
        System.out.print("\nIngrese opción: ");
        int id;
        int decision = scanner.nextInt();
        if(decision == 1) {
            System.out.print("\nIngrese el ID de la solicitud que desea aprobar: ");
            id = scanner.nextInt();
            for(Solicitud i : solicitudesPendientes) {
                if(id == i.getId()) {
                    i.setStatus(EstadoSolicitud.APROBADA);
                    System.out.println("\nSolicitud aprobada");
                    band2 = true;
                    return;
                }
            }
            if(!band2) {
                System.out.println("\nEste ID no pertenece a ninguna solicitud en proceso, por favor ingrese un ID válido");
            }
        }
        else if (decision == 2) {
            System.out.print("\nIngrese el ID de la solicitud que desea rechazar: ");
            id = scanner.nextInt();
            for(Solicitud i : solicitudesPendientes) {
                if(id == i.getId()) {
                    i.setStatus(EstadoSolicitud.RECHAZADA);
                    System.out.println("\nSolicitud rechazada");
                    band2 = true;
                    return;
                }
            }
            if(!band2) {
                System.out.println("\nEste ID no pertenece a ninguna solicitud en proceso, por favor ingrese un ID válido");
            }

            //ELIMINAR SOLICITUDES DEL ARRAY
       
        }
        else {
            System.out.println("\nPor favor ingrese una opción válida");
            band = false;
        }
        }
        while(!band);
    }
}

