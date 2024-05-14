package Usuarios.Empleados;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Sucursal.Banco;
import Usuarios.utils.Rol;

public class Capturista extends Usuario {

    private double salario;

    public Capturista(String sucursal, String direcccion, String curp, String estado, String ciudad, String fechaNacimiento, String apellidos, String nombre, String nombreUsuario, String contraseña, String rfc, double salario) {
        super(sucursal, direcccion, curp, estado, ciudad, fechaNacimiento, apellidos, nombre, nombreUsuario, contraseña, Rol.CAPTURISTA, rfc);
        this.salario = salario;
    }

    @Override
    public String getData() {
        return String.format(super.getData() + "\nSalario: %.2f" + salario);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public static void registrarCapturista() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datos = datosComun(Rol.CAPTURISTA);
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
            Capturista capturista = new Capturista(sucursal, direccion, curp, estado, ciudad, fechaNacimiento, apellido, nombre, nombreUsuario, contraseña, rfc, salario);
            Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).add(capturista);
            System.out.println("\nCapturista registrado exitosamente");
        }
    }

    public static void listarCapturistas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Consultar capturistas -----\n");
        System.out.println("1. Listar capturistas");
        System.out.println("2. Consultar capturistas");
        System.out.print("\nIngrese opción: ");
        int decision = scanner.nextInt();

        if(Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).isEmpty()) {
            System.out.println("\nNo hay capturistas registrados todavía");
        }
        else if (decision == 1) {
            int x = 1;
            System.out.println("\nCapturistas registrados");
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
                System.out.println("\n---- Capturistas " + x + " ----");
                System.out.println(usuario.getData());
            }
        }
        else {
            boolean band = false;
            System.out.println("\n---- Consultar capturista ----\n");
            System.out.print("Ingrese el nombre de usuario del capturista que desea consultar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    System.out.println("\n---- Capturista ----");
                    System.out.println(usuario.getData());
                    band = true;
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún capturista");
            }

        }
    }

    public static void eliminarCapturista() {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        if(Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).isEmpty()) {
            System.out.println("\nNo hay clientes registrados todavía");
        }
        else {
            System.out.println("\n---- Eliminar capturista ----\n");
            System.out.print("Ingrese el nombre de usuario del capturista que desea eliminar: ");
            String nombreUsuario = scanner.nextLine();
            for(Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
                if(usuario.getNombreUsuario().equals(nombreUsuario)) {
                    Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).remove(usuario);
                    System.out.println("\nCapturista eliminado");
                    return;
                }
            }
            if(!band) {
                System.out.println("\nEste nombre de usuario no pertenece a ningún capturista");
            }
        }
    }


    
    public static void modificarCapturista() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Modificar Capturista----\n");

        if (Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA).isEmpty()) {
            System.out.println("No hay Capturistas registrados.");
            return;
        }

        System.out.print("Ingrese el nombre de usuario del Capturista que desea modificar: ");
        String nombreUsuario = scanner.nextLine();

        boolean continuarModificacion;

        do {
            continuarModificacion = false;

            for (Usuario usuario : Banco.sucursal.get(Banco.sucu).get(Rol.CAPTURISTA)) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    Capturista capturista = (Capturista) usuario;
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

                    try {
                        int opcion = scanner.nextInt();
                        scanner.nextLine(); // limpiamos el scanner para que no se buguee

                        switch (opcion) {
                            case 1:
                                System.out.print("Ingrese el nuevo nombre: ");
                                String nuevoNombre = scanner.nextLine();
                                capturista.setNombre(nuevoNombre);
                                break;

                            case 2:
                                System.out.print("Apellido paterno: ");
                                String paterno = scanner.nextLine();
                                System.out.println("Apellido materno: ");
                                String materno = scanner.nextLine();
                                capturista.setApellidos(paterno.concat(" ").concat(materno));
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

                                capturista.setFechaNacimiento(String.format("%d/%d/%d", año, mes, dia)); // Aquí se establece la fecha de nacimiento
                                scanner.nextLine(); // Limpiar el buffer del scanner después de leer enter
                                break;

                            case 4:
                                System.out.print("Ingrese nueva dirección: ");
                                String nuevaDireccion = scanner.nextLine();
                                capturista.setDirecccion(nuevaDireccion);
                                break;

                            case 5:
                                System.out.print("Ingrese nuevo estado: ");
                                String nuevoEstado = scanner.nextLine();
                                capturista.setEstado(nuevoEstado);
                                break;

                            case 6:
                                System.out.print("Ingrese nueva ciudad: ");
                                String nuevaCiudad = scanner.nextLine();
                                capturista.setCiudad(nuevaCiudad);
                                break;

                            case 7:
                                System.out.print("Ingrese la nueva CURP: ");
                                String nuevaCURP = scanner.nextLine();
                                capturista.setCurp(nuevaCURP);
                                break;

                            case 8:
                                System.out.print("Ingresa nuevo nombre de usuario: ");
                                String nuevoNombreUsuario = scanner.nextLine();
                                capturista.setNombreUsuario(nuevoNombreUsuario);
                                break;

                            case 9:
                                System.out.print("Ingrese nueva contraseña: ");
                                String nuevaContraseña = scanner.nextLine();
                                capturista.setContraseña(nuevaContraseña);
                                break;

                            case 10:
                                System.out.print("Ingrese el nuevo salario: ");
                                double nuevoSalario = scanner.nextDouble();
                                capturista.setSalario(nuevoSalario);
                                break;

                            case 11:
                                return;
                                
                            default:
                                System.out.println("Opción no válida.");
                        }

                        // VOLVEMOS A SACAR EL RFC
                        System.out.println("\nDatos modificados exitosamente:");
                        capturista.setRfc(capturista.getFechaNacimiento(), capturista.getNombre(), capturista.getApellidos());
                        System.out.println(capturista.getData());

                        continuarModificacion = true;
                        break; // esto sirve para  salir del bucle para volver a preguntar si se desea continuar la modificación
                    } catch (Exception e) {
                        System.out.println("Por favor, ingrese un número válido.");
                        scanner.nextLine(); // Limpiar el buffer del scanner
                    }
                }
            }

            if (!continuarModificacion) {
                System.out.println("No se encontró ningún capturista con ese nombre de usuario.");
                return;
            }

            System.out.print("\n¿Desea seguir modificando datos? (s/n): ");
            char respuesta = scanner.next().charAt(0);
            continuarModificacion = (respuesta == 's' || respuesta == 'S');
        } while (continuarModificacion);
    }    
}

