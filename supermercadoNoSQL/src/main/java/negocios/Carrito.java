package negocios;

import controladores.*;
import sevicios.CarritoService;

import java.util.*;

public class Carrito {

    public Carrito() {
        carritoService = new CarritoService();
        estadoCarritoActual = new EstadoCarrito();
        estados = new ArrayList<EstadoCarrito>();
        estados.add(estadoCarritoActual);
        indiceCarrito = 0;
    }

    private int id;
    private Cliente cliente;
    private EstadoCarrito estadoCarritoActual;
    private List<EstadoCarrito> estados;
    private int indiceCarrito;
    private CarritoService carritoService;

    public void guardarEstado() {
        if (estadoCarritoActual == estados.get(indiceCarrito)) {
            EstadoCarrito nuevoEstadoCarrito = new EstadoCarrito();
            for (ItemCarrito item: getEstadoCarritoActual().getProductos()) {
                Producto nuevoProducto = new Producto(item.getProducto().getNombreProducto());
                int cantidad = item.getCantidad();
                ItemCarrito nuevoItem = new ItemCarrito(nuevoProducto, cantidad);
                nuevoEstadoCarrito.addProducto(nuevoItem);
            }
            estados.add(nuevoEstadoCarrito);
            setEstadoCarritoActual(nuevoEstadoCarrito);
            indiceCarrito++;
        }
    }

    public void recuperarEstado() {
        if (indiceCarrito != estados.size() ) {
            indiceCarrito++;
            setEstadoCarritoActual(estados.get(indiceCarrito));
        }
    }

    public void volverAEstadoAnterior() {
        if (!estados.isEmpty()) {
            indiceCarrito--;
            setEstadoCarritoActual(estados.get(indiceCarrito));
        }
    }

    public Pedido generarPedido(EstadoCarrito estado) {
        // TODO implement here
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoCarrito getEstadoCarritoActual() {
        return estadoCarritoActual;
    }

    public void setEstadoCarritoActual(EstadoCarrito estadoCarritoActual) {
        this.estadoCarritoActual = estadoCarritoActual;
    }

    public List<EstadoCarrito> getEstados() {
        return estados;
    }

}