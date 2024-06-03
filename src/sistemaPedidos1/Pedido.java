package sistemaPedidos1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private String nombreRemitente;
    private String tipoPedido;
    private String descripcion;
    private Date fechaEntrega;
    private Date fechaRecoleccion;
    private int cantidad;
    private double costo;
    private static List<Pedido> listaPedidos = new ArrayList<>();

    public Pedido(String nombreRemitente, String tipoPedido, String descripcion, Date fechaEntrega, Date fechaRecoleccion, int cantidad, double costo) {
        this.id = contadorId++;
        this.nombreRemitente = nombreRemitente;
        this.tipoPedido = tipoPedido;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.fechaRecoleccion = fechaRecoleccion;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public static void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public static void editarPedido(int id, Pedido pedidoActualizado) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.id == id) {
                pedido.nombreRemitente = pedidoActualizado.nombreRemitente;
                pedido.tipoPedido = pedidoActualizado.tipoPedido;
                pedido.descripcion = pedidoActualizado.descripcion;
                pedido.fechaEntrega = pedidoActualizado.fechaEntrega;
                pedido.fechaRecoleccion = pedidoActualizado.fechaRecoleccion;
                pedido.cantidad = pedidoActualizado.cantidad;
                pedido.costo = pedidoActualizado.costo;
                break;
            }
        }
    }

    public static List<Pedido> listarPedidos() {
        return listaPedidos;
    }

    public static void eliminarPedido(int id) {
        listaPedidos.removeIf(pedido -> pedido.id == id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", nombreRemitente='" + nombreRemitente + '\'' +
                ", tipoPedido='" + tipoPedido + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaEntrega=" + fechaEntrega +
                ", fechaRecoleccion=" + fechaRecoleccion +
                ", cantidad=" + cantidad +
                ", costo=" + costo +
                '}';
    }
}
