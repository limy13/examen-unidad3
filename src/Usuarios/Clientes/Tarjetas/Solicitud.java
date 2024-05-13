package Usuarios.Clientes.Tarjetas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Usuarios.Clientes.Cliente;
import Usuarios.Clientes.Tarjetas.utils.EstadoSolicitud;
import Usuarios.Clientes.Tarjetas.utils.TiposTarjeta;

public class Solicitud {
    
    public static final ArrayList<Solicitud> solicitudesPendientes = new ArrayList<>();

    private static int CANTIDAD_SOLICITUDES = 1;
    private EstadoSolicitud estado;
    private int idCliente, idSolicitud;
    private String nombre, apellido;
    private double monto;
    private LocalDate fechaSolicitud;
    private TiposTarjeta tipoTarjeta;
    private Cliente cliente;

    public Solicitud(Cliente cliente, TiposTarjeta tipoTarjeta) {
        this.cliente = cliente;
        this.idCliente = cliente.getId();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellidos();
        this.monto = cliente.getMonto();
        this.idSolicitud = CANTIDAD_SOLICITUDES;
        this.tipoTarjeta = tipoTarjeta;
        this.fechaSolicitud = LocalDate.now();
        this.estado = EstadoSolicitud.PENDIENTE;
        CANTIDAD_SOLICITUDES++;
    }

    public void setCANTIDAD_SOLICITUDES() {
        CANTIDAD_SOLICITUDES--;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public static void agregarSolicitud(Solicitud solicitud) {
        solicitudesPendientes.add(solicitud);
    }

    public TiposTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    private int getId() {
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

    public void setId() {
        idSolicitud--;
    }

    public String getData() {
        return String.format("Nombre del aspirante: %s %s \nID del cliente: %d \nID de la solicitud: %d \nMonto disponible en tarjeta de débito: %.2f", 
        nombre, apellido, idCliente, idSolicitud, monto + "\nTipo de tarjeta solicitada: " + tipoTarjeta + "\nFecha en que se solicita: " + fechaSolicitud + "\nStatus: " + estado);
    }

    public static void solicitud() {
        Solicitud solicitudEliminada = null;
        Tarjeta tarjeta;
        boolean band = true, band2 = false;
        Scanner scanner = new Scanner(System.in);
        int x = 1;
        if(!solicitudesPendientes.isEmpty()) {
    
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
                            i.getCliente().setSolicitudes(false);
                            if(i.getTipoTarjeta() == TiposTarjeta.SIMPLICITY) {
                                tarjeta = new Tarjeta(60000, TiposTarjeta.SIMPLICITY);
                                i.getCliente().asignarTarjetaS(tarjeta);
                            }
                            else if(i.getTipoTarjeta() == TiposTarjeta.PLATINO) {
                                tarjeta = new Tarjeta(150000, TiposTarjeta.PLATINO);
                                i.getCliente().asignarTarjetaP(tarjeta);
                            }
                            else {
                                tarjeta = new Tarjeta(400000, TiposTarjeta.ORO);
                                i.getCliente().asignarTarjetaO(tarjeta);
                            }
                            solicitudEliminada = i;
                            band2 = true;
                            return;
                        }
                    }
                    if(!band2) {
                        System.out.println("\nEste ID no pertenece a ninguna solicitud en proceso, por favor ingrese un ID válido");
                    }
                    else {
                        for(Solicitud solicitud : solicitudesPendientes) {
                            if(solicitudEliminada.getId() < solicitud.getId()) {
                                solicitud.setId();
                            }
                        }
                        solicitudEliminada.setCANTIDAD_SOLICITUDES();
                        solicitudesPendientes.remove(solicitudEliminada);
                        System.out.println("\nSolicitud aprobada");
                    }
                }
                else if (decision == 2) {
                    System.out.print("\nIngrese el ID de la solicitud que desea rechazar: ");
                    id = scanner.nextInt();
                    for(Solicitud i : solicitudesPendientes) {
                        if(id == i.getId()) {
                            i.setStatus(EstadoSolicitud.RECHAZADA);
                            i.getCliente().setSolicitudes(false);
                            band2 = true;
                            solicitudEliminada = i;
                            return;
                        }
                    }
                    if(!band2) {
                        System.out.println("\nEste ID no pertenece a ninguna solicitud en proceso, por favor ingrese un ID válido");
                    }
                    else {
                        for(Solicitud solicitud : solicitudesPendientes) {
                            if(solicitudEliminada.getId() < solicitud.getId()) {
                                solicitud.setId();
                            }
                        }
                        solicitudEliminada.setCANTIDAD_SOLICITUDES();
                        solicitudesPendientes.remove(solicitudEliminada);
                        System.out.println("\nSolicitud rechazada");
                    }            
                }
                else {
                    System.out.println("\nPor favor ingrese una opción válida");
                    band = false;
                }
            }
            while(!band);
        }
        else {
            System.out.println("\nNo hay solicitudes registradas todavía");
        }
    }
}

