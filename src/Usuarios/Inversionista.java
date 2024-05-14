package Usuarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Sucursal.Banco;
import Usuarios.utils.Rol;

public class Inversionista extends Usuario{

    private ArrayList<String> inversionesRealizadas = new ArrayList<>();

    public Inversionista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.INVERSIONISTA, rfc);
    }

    public static void registrarInversionista() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.INVERSIONISTA);
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

        System.out.println("\n¿Cancelar registro? (1 = si, 2 = no)");
        int decision = scanner.nextInt();
        if(decision == 1) {
            System.out.println("\nRegistro cancelado");
        }
        else {
            Inversionista inversionista = new Inversionista(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc);
            Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).add(inversionista);
            System.out.println("\nInversionista registrado exitosamente");
        }
    }

    public static void listarInversionistas() {
        Scanner scanner = new Scanner(System.in);
        boolean band = true;
       
        if(Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).isEmpty()) {
            System.out.println("\nNo hay inversionistas registrados todavía");
        }
        else {
            do {
                System.out.println("\n---- Consultar inversionistas -----\n");
                System.out.println("1. Listar inversionistas");
                System.out.println("2. Consultar inversionista");
                System.out.print("\nIngrese opción: ");
                int decision = scanner.nextInt();
            
                if(decision == 1) {
                    int x = 1;
                    System.out.println("\nInversionistas registrados:");
                    for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
                        System.out.println("\n---- Inversionista " + x + " ----");
                        System.out.println(usuario.getData());
                        x++;
                    }
                }
                else if(decision == 2){
                    boolean band2 = false;
                    System.out.println("\n---- Consultar inversionista ----\n");
                    System.out.print("Ingrese el nombre de usuario del inversionista que desea consultar: ");
                    String nombreUsuario = scanner.nextLine();
                    for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
                        if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                            System.out.println("\n---- Inversionista ----");
                            System.out.println(usuario.getData());
                            return;
                        }
                    }
                    if(!band2) {
                        System.out.println("\nEste nombre de usuario no pertenece a ningún inversionista");
                    }
                }
                else {
                    System.out.println("\nIngrese una opción válida");
                }
            }
            while(!band);
        }
    }

    public static void eliminarInversionista() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        if(Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).isEmpty()) {
            System.out.println("\nNo hay clientes registrados todavía");
        }
        else {
            System.out.println("\n---- Eliminar inversionista ----\n");
            System.out.print("Ingrese el nombre de usuario del inversionista que desea eliminar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).remove(usuario);
                    System.out.println("\nInversionista eliminado");
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún inversionista");
            }
        }
    }

    public void mostrarInversionesRealizadas() {
        System.out.println("\n---- Inversiones realizadas ----\n");
        int x = 1;
        for(String i : inversionesRealizadas) {
            System.out.println("**** Inversión " + x + " ****");
            System.out.println(i);
        }
    }

    public static void modificarInversionista() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Modificar Inversionista ----\n");

        if (Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA).isEmpty()) {
            System.out.println("No hay inversionistas registrados.");
            return;
        }

        System.out.print("Ingrese el nombre de usuario del inversionistas que desea modificar: ");
        String nombreUsuario = scanner.nextLine();

        boolean continuarModificacion;

        do {
            continuarModificacion = false;

            for (Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.INVERSIONISTA)) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    Inversionista inversionista = (Inversionista) usuario;
                    System.out.println("\nSeleccione qué desea modificar:");
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

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = scanner.nextLine();
                            inversionista.setNombre(nuevoNombre);
                            break;

                        case 2:
                            System.out.print("Apellido paterno: ");
                            String paterno = scanner.nextLine();
                            System.out.println("Apellido materno: ");
                            String materno = scanner.nextLine();
                            inversionista.setApellidos(paterno.concat(" ").concat(materno));
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

                            inversionista.setFechaNacimiento(String.format("%d/%d/%d", año, mes, dia)); // Aquí se establece la fecha de nacimiento
                            scanner.nextLine(); // Limpiar el buffer del scanner después de leer enter
                            break;

                        case 4:
                            System.out.print("Ingrese nueva dirección: ");
                            String nuevaDireccion = scanner.nextLine();
                            inversionista.setDirecccion(nuevaDireccion);
                            break;

                        case 5:
                            System.out.print("Ingrese nuevo estado: ");
                            String nuevoEstado = scanner.nextLine();
                            inversionista.setEstado(nuevoEstado);
                            break;

                        case 6:
                            System.out.print("Ingrese nueva ciudad: ");
                            String nuevaCiudad = scanner.nextLine();
                            inversionista.setCiudad(nuevaCiudad);
                            break;

                        case 7:
                            System.out.print("Ingrese la nueva CURP: ");
                            String nuevaCURP = scanner.nextLine();
                            inversionista.setCurp(nuevaCURP);
                            break;

                        case 8:
                            System.out.print("Ingresa nuevo nombre de usuario: ");
                            String nuevoNombreUsuario = scanner.nextLine();
                            inversionista.setNombreUsuario(nuevoNombreUsuario);
                            break;

                        case 9:
                            System.out.print("Ingrese nueva contraseña: ");
                            String nuevaContraseña = scanner.nextLine();
                            inversionista.setContraseña(nuevaContraseña);
                            break;

                        case 10:
                            return;

                        default:
                            System.out.println("Opción no válida.");
                    }

                    inversionista.setRfc(inversionista.getFechaNacimiento(), inversionista.getNombre(), inversionista.getApellidos());

                    System.out.println("\nDatos modificados exitosamente:");
                    System.out.println(inversionista.getData());

                    continuarModificacion = true;
                    break; // esto sirve para  salir del bucle para volver a preguntar si se desea continuar la modificación
                }
            }

            if (!continuarModificacion) {
                System.out.println("No se encontró ningún cliente con ese nombre de usuario.");
                return; // Salir si no se encontró ningún cliente
            }

            System.out.print("¿Desea seguir modificando datos? (s/n): ");
            char respuesta = scanner.next().charAt(0);
            continuarModificacion = (respuesta == 's' || respuesta == 'S');
        } while (continuarModificacion);
    }


    public void realizarInversion(Inversionista inversionista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Inversiones ----\n");
        System.out.print("Ingrese la cantidad que desea invertir: ");
        double cantidad = scanner.nextDouble();
        inversionesRealizadas.add(String.format("\nCantidad aportada: %.2f", cantidad + "\nFecha del movimiento: " + LocalDate.now()));
        Banco.inversionesRealizadas.add(String.format("Nombre del inversionista: %s %s \nCantidad aportada: %.2f", inversionista.getNombre(), inversionista.getApellidos(), cantidad + "\nSucursal: " + Banco.sucu + "\nFecha del movimiento: " + LocalDate.now())); 
    }

}
