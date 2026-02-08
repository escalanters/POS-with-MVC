package view;

import controller.VentaControlador;
import interfaces.IObservador;
import interfaces.IVentaLectura;
import model.Producto;
import styles.Button;
import styles.Style;
import styles.FontUtil;
import styles.TxtFieldPh;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class VentaVista extends JFrame implements IObservador{

    private TxtFieldPh txtNombre, txtCantidad, txtPrecio, txtPago;
    private JLabel lblTotal, lblCambio;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Button btnAgregar, btnQuitar, btnFinalizar;
    private final VentaControlador controlador;

    public VentaVista(VentaControlador controlador) {
        this.controlador = controlador;
        startComponents();
    }

    private void startComponents() {
        setTitle("Punto de Venta - Estilo Moderno");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        getContentPane().setBackground(Style.COLOR_BACKGROUND);

        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelEntrada.setBackground(Style.COLOR_BACKGROUND);
        panelEntrada.setBorder(new EmptyBorder(10, 10, 10, 10));

        txtNombre = new TxtFieldPh("Nombre Producto", 200, 40, 14, 20);
        txtCantidad = new TxtFieldPh("Cantidad", 100, 40, 14, 20);
        txtPrecio = new TxtFieldPh("Precio Unitario", 120, 40, 14, 20);

        btnAgregar = new Button("Agregar", 150, 40, 14, 20,
                Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);

        panelEntrada.add(txtNombre);
        panelEntrada.add(txtCantidad);
        panelEntrada.add(txtPrecio);
        panelEntrada.add(btnAgregar);

        add(panelEntrada, BorderLayout.NORTH);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(new EmptyBorder(0, 20, 0, 20));
        panelTabla.setBackground(Style.COLOR_BACKGROUND);

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Cant.");
        modeloTabla.addColumn("Precio U.");
        modeloTabla.addColumn("Subtotal");

        tablaProductos = new JTable(modeloTabla);
        estilizarTabla(tablaProductos);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        panelTabla.add(scrollPane, BorderLayout.CENTER);
        add(panelTabla, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(Style.COLOR_BACKGROUND);
        panelInferior.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelControlesPago = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelControlesPago.setBackground(Style.COLOR_BACKGROUND);

        btnQuitar = new Button("Quitar Seleccionado", 180, 40, 14, 20,
                Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);

        txtPago = new TxtFieldPh("Monto Pago", 120, 40, 14, 20);

        btnFinalizar = new Button("FINALIZAR VENTA", 180, 40, 14, 20,
                Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);

        panelControlesPago.add(btnQuitar);
        panelControlesPago.add(new JLabel("Pago:"));
        panelControlesPago.add(txtPago);
        panelControlesPago.add(btnFinalizar);

        JPanel panelTotales = new JPanel(new GridLayout(2, 1));
        panelTotales.setBackground(Style.COLOR_BACKGROUND);

        lblTotal = new JLabel("Total: $0.0", SwingConstants.CENTER);
        lblTotal.setFont(FontUtil.loadFont(24, "Inter_Bold"));
        lblTotal.setForeground(Color.DARK_GRAY);

        lblCambio = new JLabel("Cambio: $0.0", SwingConstants.CENTER);
        lblCambio.setFont(FontUtil.loadFont(18, "Inter_Regular"));
        lblCambio.setForeground(new Color(100, 100, 100));

        panelTotales.add(lblTotal);
        panelTotales.add(lblCambio);

        panelInferior.add(panelControlesPago, BorderLayout.CENTER);
        panelInferior.add(panelTotales, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);

        configurarListeners();
    }


    private void estilizarTabla(JTable table) {
        table.setRowHeight(30);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(230, 230, 230));
        table.setFont(FontUtil.loadFont(14, "Inter_Regular"));
        table.setSelectionBackground(Style.COLOR_BTN.brighter());
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setBackground(Color.WHITE);
                lbl.setForeground(Color.GRAY);
                lbl.setFont(FontUtil.loadFont(13, "Inter_SemiBold"));
                lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
                lbl.setHorizontalAlignment(CENTER);
                return lbl;
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void configurarListeners() {
        btnAgregar.addActionListener(e -> {
            try {
                if(txtNombre.getText().isEmpty() || txtCantidad.getText().isEmpty() || txtPrecio.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Complete todos los campos");
                    return;
                }
                boolean exito = controlador.agregarProducto(txtNombre.getText(), txtCantidad.getText(), txtPrecio.getText());
                if(exito){
                    limpiarInputsProducto();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnQuitar.addActionListener(e -> {
            try {
                int row = tablaProductos.getSelectedRow();
                controlador.quitarProducto(row);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnFinalizar.addActionListener(e -> {
            try {
                if(txtPago.getText().isEmpty()){
                    throw new Exception("Ingrese el monto de pago");
                }
                controlador.finalizarVenta(txtPago.getText());
                JOptionPane.showMessageDialog(this, "Venta finalizada con exito");
                txtPago.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

    private void limpiarInputsProducto() {
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtNombre.requestFocus();
    }

    @Override
    public void actualizar(IVentaLectura context) {
        modeloTabla.setRowCount(0);
        List<Producto> lista = context.getListaProductos();

        for(Producto producto : lista){
            modeloTabla.addRow(new Object[]{
                    producto.getNombre(),
                    producto.getCantidad(),
                    producto.getPrecioUnitario(),
                    producto.getSubtotal()
            });
        }

        lblTotal.setText("Total: $" + context.getTotal());

        if(lista.isEmpty()){
            double cambio = context.getCambio();
            lblCambio.setText("Cambio última venta: $" + cambio);

            if (cambio > 0) {
                txtPago.setText("");
                JOptionPane.showMessageDialog(this, "Venta Exitosa. Entregar cambio: $" + cambio);
            }
        } else {
            lblCambio.setText("Cambio: $0.0");
        }
    }
}