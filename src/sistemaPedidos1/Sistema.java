package sistemaPedidos1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (usuarioActual == null) {
                System.out.println("1. Crear Cuenta");
                System.out.println("2. Iniciar Sesión");
                System.out.println("3. Salir");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir nueva línea

                switch (opcion) {
                    case 1:
                        crearCuenta(scanner);
                        break;
                    case 2:
                        iniciarSesion(scanner);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("\nBienvenido, " + usuarioActual.getUserName());
                System.out.println("1. Agregar Pedido");
                System.out.println("2. Editar Pedido");
                System.out.println("3. Listar Pedidos");
                System.out.println("4. Eliminar Pedido");
                System.out.println("5. Cerrar Sesión");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir nueva línea

                switch (opcion) {
                    case 1:
                        agregarPedido(scanner);
                        break;
                    case 2:
                        editarPedido(scanner);
                        break;
                    case 3:
                        listarPedidos();
                        break;
                    case 4:
                        eliminarPedido(scanner);
                        break;
                    case 5:
                        usuarioActual = null;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }
    }

    private static void crearCuenta(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        boolean registrado = Usuario.registrar(nombre, userName, password, email);
        if (registrado) {
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("Nombre de usuario ya existe.");
        }
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.print("UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Usuario usuario = Usuario.iniciarSesion(userName, password);
        if (usuario != null) {
            usuarioActual = usuario;
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private static void agregarPedido(Scanner scanner) {
        System.out.print("Nombre Remitente: ");
        String nombreRemitente = scanner.nextLine();
        System.out.print("Tipo de Pedido: ");
        String tipoPedido = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Fecha de Entrega (dd/MM/yyyy): ");
        Date fechaEntrega = parseFecha(scanner.nextLine());
        System.out.print("Fecha de Recolección (dd/MM/yyyy): ");
        Date fechaRecoleccion = parseFecha(scanner.nextLine());
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Costo: ");
        double costo = scanner.nextDouble();
        scanner.nextLine();  // Consumir nueva línea

        Pedido pedido = new Pedido(nombreRemitente, tipoPedido, descripcion, fechaEntrega, fechaRecoleccion, cantidad, costo);
        Pedido.agregarPedido(pedido);
        System.out.println("Pedido agregado exitosamente.");
    }

    private static void editarPedido(Scanner scanner) {
        System.out.print("ID del Pedido a Editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        System.out.print("Nombre Remitente: ");
        String nombreRemitente = scanner.nextLine();
        System.out.print("Tipo de Pedido: ");
        String tipoPedido = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Fecha de Entrega (dd/MM/yyyy): ");
        Date fechaEntrega = parseFecha(scanner.nextLine());
        System.out.print("Fecha de Recolección (dd/MM/yyyy): ");
        Date fechaRecoleccion = parseFecha(scanner.nextLine());
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Costo: ");
        double costo = scanner.nextDouble();
        scanner.nextLine();  // Consumir nueva línea

        Pedido pedidoActualizado = new Pedido(nombreRemitente, tipoPedido, descripcion, fechaEntrega, fechaRecoleccion, cantidad, costo);
        Pedido.editarPedido(id, pedidoActualizado);
        System.out.println("Pedido editado exitosamente.");
    }

    private static void listarPedidos() {
        List<Pedido> pedidos = Pedido.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    private static void eliminarPedido(Scanner scanner) {
        System.out.print("ID del Pedido a Eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        Pedido.eliminarPedido(id);
        System.out.println("Pedido eliminado exitosamente.");
    }

    private static Date parseFecha(String fecha) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return null;
        }
    }
}
