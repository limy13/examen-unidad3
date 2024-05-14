package Usuarios.Empleados;
import Sucursal.Banco;
import Usuarios.Usuario;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejecutivo extends Usuario {

    private double salario;

    public Ejecutivo(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc, double salario) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.EJECUTIVO, rfc);
        this.salario = salario;
    }

    @Override
    public String getData() {
        return String.format(super.getData() + "\nSalario: %.2f", salario);
    }

    public static void registrarEjecutivo() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.EJECUTIVO);
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
        System.out.println("Salario: ");
        double salario = scanner.nextDouble();

        System.out.println("\n¿Cancelar registro? (1 = si, 2 = no)");
        int decision = scanner.nextInt();
        if(decision == 1) {
            System.out.println("\nRegistro cancelado");
        }
        else {
            Ejecutivo ejecutivo = new Ejecutivo(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc, salario);
            Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).add(ejecutivo);
            System.out.println("\nEjecutivo registrado exitosamente");
        }
    }


    public static void listarEjecutivos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Consultar ejecutivos de cuenta -----\n");
        System.out.println("1. Listar ejecutivos de cuenta");
        System.out.println("2. Consultar ejecutivo de cuenta");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).isEmpty()) {
            System.out.println("\nNo hay ejecutivos registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nEjecutivos registrados:");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
                System.out.println("\n---- Ejecutivos " + x + " ----");
                System.out.println(usuario.getData());
                x++;
            }
        }
        else {
            boolean band = false;
            System.out.println("\n---- Consultar ejecutivo de cuenta ----\n");
            System.out.print("Ingrese el nombre de usuario del ejecutivo que desea consultar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Ejecutivo ----");
                    System.out.println(usuario.getData());
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún ejecutivo");
            }
        }
    }

    public static void eliminarEjecutivo() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar ejecutivo de cuenta ----\n");
        System.out.print("Ingrese el nombre de usuario del ejecutivo que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
            if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).remove(usuario);
                System.out.println("\nEjecutivo eliminado");
                return;
            }
        }
        if(!band) {
            System.out.println("\nEste nombre de usuario no pertenece a ningún ejecutivo");
        }
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }


    public static void modificarEjecutivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Modificar Ejecutivo ----\n");

        if (Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO).isEmpty()) {
            System.out.println("No hay ejecutivos registrados.");
            return;
        }

        System.out.print("Ingrese el nombre de usuario del Ejecutivo que desea modificar: ");
        String nombreUsuario = scanner.nextLine();

        boolean continuarModificacion;

        do {
            continuarModificacion = false;

            for (Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.EJECUTIVO)) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    Ejecutivo ejecutivo = (Ejecutivo) usuario;
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
                    System.out.println("10. Salario");
                    System.out.println("11. Terminar modificación");
                    System.out.print("\nIngrese opción: ");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // limpiamos el scanner para que no se buguee
                    System.out.println("\n");

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = scanner.nextLine();
                            ejecutivo.setNombre(nuevoNombre);
                            break;

                        case 2:
                            System.out.print("Apellido paterno: ");
                            String paterno = scanner.nextLine();
                            System.out.println("Apellido materno: ");
                            String materno = scanner.nextLine();
                            ejecutivo.setApellidos(paterno.concat(" ").concat(materno));
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

                            ejecutivo.setFechaNacimiento(String.format("%d/%d/%d", año, mes, dia)); // Aquí se establece la fecha de nacimiento
                            scanner.nextLine(); // Limpiar el buffer del scanner después de leer enter
                            break;

                        case 4:
                            System.out.print("Ingrese nueva dirección: ");
                            String nuevaDireccion = scanner.nextLine();
                            ejecutivo.setDirecccion(nuevaDireccion);
                            break;

                        case 5:
                            System.out.print("Ingrese nuevo estado: ");
                            String nuevoEstado = scanner.nextLine();
                            ejecutivo.setEstado(nuevoEstado);
                            break;

                        case 6:
                            System.out.print("Ingrese nueva ciudad: ");
                            String nuevaCiudad = scanner.nextLine();
                            ejecutivo.setCiudad(nuevaCiudad);
                            break;

                        case 7:
                            System.out.print("Ingrese la nueva CURP: ");
                            String nuevaCURP = scanner.nextLine();
                            ejecutivo.setCurp(nuevaCURP);
                            break;

                        case 8:
                            System.out.print("Ingresa nuevo nombre de usuario: ");
                            String nuevoNombreUsuario = scanner.nextLine();
                            ejecutivo.setNombreUsuario(nuevoNombreUsuario);
                            break;

                        case 9:
                            System.out.print("Ingrese nueva contraseña: ");
                            String nuevaContraseña = scanner.nextLine();
                            ejecutivo.setContraseña(nuevaContraseña);
                            break;

                        case 10:
                            System.out.print("Ingrese el nuevo salario: ");
                            double nuevoSalario = scanner.nextDouble();
                            ejecutivo.setSalario(nuevoSalario);
                            break;

                        case 11:
                            return;

                        default:
                            System.out.println("Opción no válida.");
                    }

                    System.out.println("\nDatos modificados exitosamente:");
                    ejecutivo.setRfc(ejecutivo.getFechaNacimiento(), ejecutivo.getNombre(), ejecutivo.getApellidos());
                    System.out.println(ejecutivo.getData());

                    continuarModificacion = true;
                    break; // esto sirve para  salir del bucle para volver a preguntar si se desea continuar la modificación
                }
            }

            if (!continuarModificacion) {
                System.out.println("No se encontró ningún ejecutivo con ese nombre de usuario.");
                return; // Salir si no se encontró ningún ejecutivo
            }

            System.out.print("\n¿Desea seguir modificando datos? (s/n): ");
            char respuesta = scanner.next().charAt(0);
            continuarModificacion = (respuesta == 's' || respuesta == 'S');
        } while (continuarModificacion);
    }
}
