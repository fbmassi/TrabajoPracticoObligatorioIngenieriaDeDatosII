package controladores;

import java.util.*;
import negocios.*;
import org.bson.Document;
import sevicios.AdminService;
import sevicios.LogService;

public class Administrador {

    public Administrador(String usuario, String contraseña) {
        this.adminService = new AdminService();
        setSesion(iniciarSesion(usuario, contraseña));
        if (sesion) {
            this.logService = new LogService();
            setDocumentoAdmin(adminService.obtenerAdmin(usuario, contraseña));
            setId(getDocumentoAdmin().getObjectId("_id").toString());
            setUsuario(getDocumentoAdmin().getString("usuario"));
        }
    }

    private String id;
    private String usuario;
    private AdminService adminService;
    private LogService logService;
    private Document documentoAdmin;
    private boolean sesion;

    public boolean iniciarSesion(String usuario, String contraseña) {
        return adminService.iniciarSesion(usuario, contraseña);
    }

    public void cerrarSesion() {
        documentoAdmin = null;
        System.gc();
    }

    public void agregarProducto(String nombreProducto, String descripcion, double precio) {
        new Producto(nombreProducto, descripcion, precio);
        Producto producto = new Producto(nombreProducto);
        CambioProducto cambioProducto = new CambioProducto(producto, this.getUsuario());
        cambioProducto.registrarCreacionenLog();
    }

    public void eliminarProducto(String nombreProducto) {
        Producto producto = new Producto(nombreProducto);
        producto.eliminarProducto(getUsuario());
    }

    public void actualizarPrecioProducto(String nombreProducto, double nuevoPrecio) {
        Producto producto = new Producto(nombreProducto);
        producto.actualizarPrecio(nuevoPrecio, this.getUsuario());
    }

    public void actualizarDescripcionProducto(String nombreProducto, String nuevaDescripción) {
        Producto producto = new Producto(nombreProducto);
        producto.actualizarDescripción(nuevaDescripción, this.getUsuario());
    }

    public List<String> traerLogDeTodoElCatalogo() {
        return logService.obtenerTodosLosLogs();
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setId(String id) { this.id = id; }
    public Document getDocumentoAdmin() {
        return documentoAdmin;
    }
    public void setDocumentoAdmin(Document documentoAdmin) {
        this.documentoAdmin = documentoAdmin;
    }
    public boolean getSesion() { return sesion; }
    public void setSesion(boolean sesion) { this.sesion = sesion; }
}