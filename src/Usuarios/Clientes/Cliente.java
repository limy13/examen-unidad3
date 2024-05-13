package Usuarios.Clientes;
import Sucursal.Banco;
import Usuarios.Usuario;
import Usuarios.Clientes.Tarjetas.Solicitud;
import Usuarios.Clientes.Tarjetas.Tarjeta;
import Usuarios.Clientes.Tarjetas.utils.TiposTarjeta;
import Usuarios.utils.Rol;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Usuario {

    private ArrayList<Solicitud> solicitudesPendientes = new ArrayList<>();
    private static int CANTIDAD_CLIENTES = 1;
    private int id;
    private boolean solicitudes = false;
    private static Tarjeta tarjetaD, tarjetaS, tarjetaO, tarjetaP;

    public Cliente(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc, Tarjeta tarjeta) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.CLIENTE, rfc);
        this.tarjetaD = tarjeta;
        this.id = CANTIDAD_CLIENTES;
        CANTIDAD_CLIENTES++;
    }

    public boolean getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(boolean band) {
        solicitudes = band;
    }

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudesPendientes.add(solicitud);
    }

    public void mostrarSolicitudesPendientes() {
        int x = 1;
        if(!solicitudesPendientes.isEmpty()) {
            System.out.println("\n**** Solicitudes pendientes ****");
            for(Solicitud solicitud : solicitudesPendientes) {
                System.out.println("\n---- Solicitud " + x + "----");
                System.out.println(solicitud.getData());
                x++;
            }
        }
        else {
            System.out.println("\nNo hay solicitudes pendientes por aprobar todavía");
        }
    }

    public double getMonto() {
        return tarjetaD.getSaldo();
    }

    public int getId() {
        return id;
    }

    public void asignarTarjetaS(Tarjeta tarjeta) {
        this.tarjetaS = tarjeta;
    }

    public void asignarTarjetaP(Tarjeta tarjeta) {
        this.tarjetaP = tarjeta;
    }

    public void asignarTarjetaO(Tarjeta tarjeta) {
        this.tarjetaO = tarjeta;
    }

    private void setId() {
        id--;
    }

    private static void setCantidadClientes() {
        CANTIDAD_CLIENTES--;
    }

    public Tarjeta getTarjetaDebito() {
        return tarjetaD;
    }

    public Tarjeta getTarjetaPlatino() {
        return tarjetaP;
    }

    public Tarjeta getTarjetaSimplicity() {
        return tarjetaS;
    }

    public Tarjeta getTarjetaOro() {
        return tarjetaO;
    }

    public static void registrarCliente() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.CLIENTE);
        String nombre = datos.get(0);
        String apellido = datos.get(1);
        String fechaNacimiento = datos.get(2);
        String estado = datos.get(3);
        String ciudad = datos.get(4);
        String direccion = datos.get(5);
        String curp = datos.get(6);
        String nombreUsuario = datos.get(7);
        String contraseña = datos.get(8);
        String rfc = datos.get(9);
        String sucursal = datos.get(10);
        System.out.print("Saldo actual: ");
        double saldo = scanner.nextDouble();
        
        Tarjeta tarjeta = new Tarjeta(saldo, TiposTarjeta.DÉBITO);

        Cliente cliente = new Cliente(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc, tarjeta);

        System.out.print("\n¿Cancelar registro? (1 = si, 2 = no): ");
        int decision = scanner.nextInt();
        if(decision == 1) {
            System.out.println("\nRegistro cancelado");
        }
        else {
            if(!Banco.sucursal.get(Banco.sucu).containsKey(Rol.CLIENTE)) {
                Banco.sucursal.get(Banco.sucu).put(Rol.CLIENTE, new ArrayList<Usuario>());
            }
    
            Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).add(cliente);
            System.out.println("\nCliente registrado exitosamente");
        }
    }

    @Override
    public String getData() {
        return "\nID: " + this.id + super.getData();
    }

    public static void listarClientes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n---- Consultar clientes -----\n");
        System.out.println("1. Listar clientes");
        System.out.println("2. Consultar cliente");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).isEmpty()) {
            System.out.println("\nNo hay clientes registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nClientes registrados:");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
                System.out.println("\n---- Cliente " + x + " ----");
                System.out.println(usuario.getData());
                tarjetaD.mostrarDatosTarjeta();
                if(tarjetaS != null) {
                    tarjetaS.mostrarDatosTarjeta();
                }
                if(tarjetaP != null) {
                    tarjetaP.mostrarDatosTarjeta();
                }
                if(tarjetaO != null) {
                    tarjetaO.mostrarDatosTarjeta();
                }
                //imprimir dtos tarjeta
                x++;
            }
        }
        else {
            boolean band = false;
            System.out.println("\n---- Consultar cliente ----\n");
            System.out.print("Ingrese el nombre de usuario del cliente que desea consultar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Cliente ----");
                    System.out.println(usuario.getData());
                    tarjetaD.mostrarDatosTarjeta();
                    if(tarjetaS != null) {
                        tarjetaS.mostrarDatosTarjeta();
                    }
                    if(tarjetaP != null) {
                        tarjetaP.mostrarDatosTarjeta();
                    }
                    if(tarjetaO != null) {
                        tarjetaO.mostrarDatosTarjeta();
                    }
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún cliente");
            }
        }
    }

    public static void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar cliente ----\n");
        System.out.print("Ingrese el nombre de usuario del cliente que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
            if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                Cliente cliente = (Cliente) usuario;
                for(Usuario usuario2 : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
                    Cliente cliente2 = (Cliente) usuario2;
                    if(cliente2.getId() > cliente.getId()) {
                        cliente2.setId();
                    }
                }
                Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).remove(usuario);
                setCantidadClientes();
                System.out.println("\nCliente eliminado");
                return;
            }
        }
        if(!band) {
            System.out.println("\nEste nombre de usuario no pertenece a ningún cliente");
        }
    }


    public static void modificarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Modificar cliente ----\n");

        if (Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE).isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.print("Ingrese el nombre de usuario del cliente que desea modificar: ");
        String nombreUsuario = scanner.nextLine();

        boolean continuarModificacion;

        do {
            continuarModificacion = false;

            for (Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CLIENTE)) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    Cliente cliente = (Cliente) usuario;
                    System.out.println("\nSeleccione qué campo desea modificar:");
                    System.out.println("1. Nombre");
                    System.out.println("2. Apellidos");
                    System.out.println("3. Fecha de nacimiento");
                    System.out.println("4. Dirección");
                    System.out.println("5. Estado");
                    System.out.println("6. Ciudad");
                    System.out.println("7. CURP");
                    System.out.println("8. Usuario ");
                    System.out.println("9. Contraseña");
                    System.out.println("10. Terminar modificación");
                    System.out.print("\nIngrese opción: ");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // limpiamos el scanner para que no se buguee
                    System.out.println("\n");

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = scanner.nextLine();
                            cliente.setNombre(nuevoNombre);
                            break;

                        case 2:
                            System.out.print("Apellido paterno: ");
                            String paterno = scanner.nextLine();
                            System.out.println("Apellido materno: ");
                            String materno = scanner.nextLine();
                            cliente.setApellidos(paterno.concat(" ").concat(materno));
                            break;
                            
                        case 3:
                            boolean fechaValida = false;
                            LocalDate nuevaFechaNacimiento = null;
                            int dia = 0, mes = 0, año = 0;
                            do {
                                try {
                                    System.out.print("Ingrese el día de nacimiento: ");
                                    dia = scanner.nextInt();
                                    System.out.print("Ingrese el mes de nacimiento: ");
                                    mes = scanner.nextInt();
                                    System.out.print("Ingrese el año de nacimiento: ");
                                    año = scanner.nextInt();

                                    nuevaFechaNacimiento = LocalDate.of(año, mes, dia);

                                    // Validaciones
                                    if (nuevaFechaNacimiento.isAfter(LocalDate.now())) {
                                        System.out.println("La fecha de nacimiento no puede ser en el futuro.");
                                    } else if (mes > 12 || dia > 31) {
                                        System.out.println("Fecha de nacimiento no válida.");
                                    } else if (mes == 2 && dia > 28) {
                                        System.out.println("En febrero sólo hay 28 días.");
                                    } else {
                                        fechaValida = true;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Formato de fecha incorrecto o fuera de rango. Por favor, ingrese una fecha válida.");
                                    scanner.nextLine(); // Limpiar el buffer del scanner
                                }
                            } while (!fechaValida);

                            cliente.setFechaNacimiento(String.format("%d/%d/%d", año, mes, dia)); // Aquí se establece la fecha de nacimiento
                            scanner.nextLine(); // Limpiar el buffer del scanner después de leer enter
                            break;

                        case 4:
                            System.out.print("Ingrese nueva dirección: ");
                            String nuevaDireccion = scanner.nextLine();
                            cliente.setDirecccion(nuevaDireccion);
                            break;

                        case 5:
                            System.out.print("Ingrese nuevo estado: ");
                            String nuevoEstado = scanner.nextLine();
                            cliente.setEstado(nuevoEstado);
                            break;

                        case 6:
                            System.out.print("Ingrese nueva ciudad: ");
                            String nuevaCiudad = scanner.nextLine();
                            cliente.setCiudad(nuevaCiudad);
                            break;

                        case 7:
                            System.out.print("Ingrese la nueva CURP: ");
                            String nuevaCURP = scanner.nextLine();
                            cliente.setCurp(nuevaCURP);
                            break;

                        case 8:
                            System.out.print("Ingresa nuevo nombre de usuario: ");
                            String nuevoNombreUsuario = scanner.nextLine();
                            cliente.setNombreUsuario(nuevoNombreUsuario);
                            break;

                        case 9:
                            System.out.print("Ingrese nueva contraseña: ");
                            String nuevaContraseña = scanner.nextLine();
                            cliente.setContraseña(nuevaContraseña);
                            break;

                        case 10:
                            return;

                        default:
                            System.out.println("Opción no válida.");
                    }

                    System.out.println("\nDatos modificados exitosamente:");
                    cliente.setRfc(cliente.getFechaNacimiento(), cliente.getNombre(), cliente.getApellidos());
                    System.out.println(cliente.getData());

                    continuarModificacion = true;
                    break; // esto sirve para  salir del bucle para volver a preguntar si se desea continuar la modificación
                }
            }

            if (!continuarModificacion) {
                System.out.println("No se encontró ningún cliente con ese nombre de usuario.");
                return; // Salir si no se encontró ningún cliente
            }

            System.out.print("\n¿Desea seguir modificando datos? (s/n): ");
            char respuesta = scanner.next().charAt(0);
            continuarModificacion = (respuesta == 's' || respuesta == 'S');

        } while (continuarModificacion);
    }

    public void pagarTarjetaCredito() {
        Scanner scanner = new Scanner(System.in);
        boolean band = true;
        String opcion;
        double cantidad;
        if(tarjetaS == null && tarjetaP == null && tarjetaO == null) {
            System.out.println("\nNo posees ninguna tarjeta de crédito todavía");
        }
        else {
            System.out.println("\n---- Pagar tarjeta de crédito ----\n");
            do {
                System.out.println("¿Qué tarjeta desea pagar? (S = simplicity, P = platino, O = Oro): ");
                opcion = scanner.nextLine();
                if(opcion.equalsIgnoreCase("s") || opcion.equalsIgnoreCase("p") || opcion.equalsIgnoreCase("o")) {
                    if(opcion.equalsIgnoreCase("s") && tarjetaS != null) {
                        System.out.println("\nIngrese la cantidad que desea pagar: ");
                        cantidad = scanner.nextDouble();
                        tarjetaS.setSaldo(tarjetaS.getSaldo() + cantidad); 
                    }
                    else if(opcion.equalsIgnoreCase("p") && tarjetaP != null) {
                        System.out.println("\nIngrese la cantidad que desea pagar: ");
                        cantidad = scanner.nextDouble();
                        tarjetaP.setSaldo(tarjetaP.getSaldo() + cantidad);
                    }
                    else if(opcion.equalsIgnoreCase("o") && tarjetaO != null) {
                        System.out.println("\nIngrese la cantidad que desea pagar: ");
                        cantidad = scanner.nextDouble();
                        tarjetaO.setSaldo(tarjetaO.getSaldo() + cantidad);
                    }
                    else {
                        System.out.println("\nNo posees esta tarjeta");
                        band = false;
                    } 
                }
                else {
                    System.out.println("\nIngrese una opción válida");
                    band = false;
                }
            }   
            while (!band);
        }
    }

    public void mostrarDatosTarjeta() {
        tarjetaD.mostrarDatosTarjeta();
        if(tarjetaS != null) {
            tarjetaS.mostrarDatosTarjeta();
        }
        if(tarjetaP != null) {
            tarjetaP.mostrarDatosTarjeta();
        }
        if(tarjetaO != null) {
            tarjetaO.mostrarDatosTarjeta();
        }
    }

    public void pagarCompra() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        String opcion;
        double cantidad;
        if(tarjetaS == null && tarjetaP == null && tarjetaO == null) {
            System.out.println("\nNo posees ninguna tarjeta de crédito todavía");
        }
        else {
            System.out.println("\n---- Pagar con tarjeta de crédito ----\n");
            do {
                System.out.println("¿Con que tarjeta desea pagar? (S = simplicity, P = platino, O = Oro): ");
                opcion = scanner.nextLine();
                if(opcion.equalsIgnoreCase("s") || opcion.equalsIgnoreCase("p") || opcion.equalsIgnoreCase("o")) {
                    if(opcion.equalsIgnoreCase("s") && tarjetaS != null) {
                        System.out.println("\n---- Tarjeta simplicity ----\n");
                        System.out.println("Saldo actual: " + tarjetaS.getSaldo());
                        do {
                            System.out.println("\nIngrese la cantidad que desea pagar: ");
                            cantidad = scanner.nextDouble();
                            if(cantidad > tarjetaS.getSaldo()) {
                                System.out.println("\nLa cantidad a pagar sobrepasa el saldo actual en su tarjeta, por favor ingrese una cantidad válida");
                            }
                            else {
                                tarjetaS.setSaldo(tarjetaS.getSaldo() - cantidad);
                                band = true;
                                System.out.println("\nSe ha retirado " + cantidad + " MX de su tarjeta");
                                tarjetaS.setFechaUltimoMovimiento();
                            }
                        }
                        while(!band);
                    }
                    else if(opcion.equalsIgnoreCase("p") && tarjetaP != null) {
                        System.out.println("\n---- Tarjeta platino ----\n");
                        System.out.println("Saldo actual: " + tarjetaP.getSaldo());
                        do {
                            System.out.println("\nIngrese la cantidad que desea pagar: ");
                            cantidad = scanner.nextDouble();
                            if(cantidad > tarjetaP.getSaldo()) {
                                System.out.println("\nLa cantidad a pagar sobrepasa el saldo actual en su tarjeta, por favor ingrese una cantidad válida");
                            }
                            else {
                                tarjetaP.setSaldo(tarjetaP.getSaldo() - cantidad);
                                band = true;
                                System.out.println("\nSe ha retirado " + cantidad + " MX de su tarjeta");
                                tarjetaP.setFechaUltimoMovimiento();
                            }
                        }
                        while(!band);
                    }
                    else if(opcion.equalsIgnoreCase("o") && tarjetaO != null) {
                        System.out.println("\n---- Tarjeta oro ----\n");
                        System.out.println("Saldo actual: " + tarjetaO.getSaldo());
                        do {
                            System.out.println("\nIngrese la cantidad que desea pagar: ");
                            cantidad = scanner.nextDouble();
                            if(cantidad > tarjetaO.getSaldo()) {
                                System.out.println("\nLa cantidad a pagar sobrepasa el saldo actual en su tarjeta, por favor ingrese una cantidad válida");
                            }
                            else {
                                tarjetaO.setSaldo(tarjetaO.getSaldo() - cantidad);
                                band = true;
                                System.out.println("\nSe ha retirado " + cantidad + " MX de su tarjeta");
                                tarjetaO.setFechaUltimoMovimiento();
                            }
                        }
                        while(!band);
                    }
                    else {
                        System.out.println("\nNo posees esta tarjeta");
                        band = false;
                    } 
                }
                else {
                    System.out.println("\nIngrese una opción válida");
                    band = false;
                }
            }   
            while (!band);
        }
    }
}

