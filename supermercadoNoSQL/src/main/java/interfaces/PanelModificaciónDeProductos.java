package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelModificaciónDeProductos extends JFrame {
    private JPanel panel;
    private JButton atrásButton;
    private JComboBox<String> productos;
    private JTextField nuevoPrecioTextField;
    private JTextField nuevaDescripciónTextField;
    private JButton cambiarPrecioButton;
    private JButton cambiarDescripciónButton;
    private JButton eliminarProductoButton;
    private PanelControlAdmin panelControlAdmin;

    public PanelModificaciónDeProductos() {

        setTitle("Panel de Control de Catalogo");
        setContentPane(panel);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        atrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                productos.removeAllItems();
                panelControlAdmin.setVisible(true);
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String producto = (String) productos.getSelectedItem();
                productos.removeItem(producto);
                if (producto != null) {
                    panelControlAdmin.getAdministrador().eliminarProducto(producto);
                    JOptionPane.showMessageDialog(panel, "Producto eliminado correctamente.");
                }
            }
        });

        cambiarDescripciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) productos.getSelectedItem();
                String nuevaDescripcion = nuevaDescripciónTextField.getText();
                if (selectedProduct != null && !nuevaDescripcion.isEmpty()) {
                    panelControlAdmin.getAdministrador().actualizarDescripcionProducto(selectedProduct, nuevaDescripcion);
                    JOptionPane.showMessageDialog(panel, "Descripción cambiada correctamente.");
                }
                nuevaDescripciónTextField.setText("");

            }
        });
        cambiarPrecioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) productos.getSelectedItem();
                String nuevoPrecioText = nuevoPrecioTextField.getText();
                if (selectedProduct != null && !nuevoPrecioText.isEmpty()) {
                    try {
                        double nuevoPrecio = Double.parseDouble(nuevoPrecioText);
                        panelControlAdmin.getAdministrador().actualizarPrecioProducto(selectedProduct, nuevoPrecio);
                        JOptionPane.showMessageDialog(panel, "Precio cambiado correctamente.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Por favor ingrese un precio válido.");
                    }
                }
                nuevoPrecioTextField.setText("");
            }
        });
    }

    public PanelControlAdmin getPanelControlAdmin() {
        return panelControlAdmin;
    }

    public void setPanelControlAdmin(PanelControlAdmin panelControlAdmin) {
        this.panelControlAdmin = panelControlAdmin;
    }

    public void llenarListas () {
        List<String> products = panelControlAdmin.getPanelIniciarSesionAdmin().getPanelAdministradores().getPanelPrincipal().getSistema().obtenerNombreProducto();
        for (String product: products) {
            productos.addItem(product);
        }
    }
}
