import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ContactosApp extends JFrame {
    private JTextField nombreField, telefonoField, correoField;
    private JTable contactosTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> contactosList = new ArrayList<>();

    public ContactosApp() {
        setTitle("Agenda de Contactos 🗂️");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        Color fondo = new Color(28, 28, 30); // Oscuro elegante
        Color texto = new Color(255, 255, 255);
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(fondo);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(fondo);
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)), "Agregar Contacto", 0, 0, fuente, texto));

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JLabel correoLabel = new JLabel("Correo:");

        nombreLabel.setForeground(texto);
        telefonoLabel.setForeground(texto);
        correoLabel.setForeground(texto);

        nombreLabel.setFont(fuente);
        telefonoLabel.setFont(fuente);
        correoLabel.setFont(fuente);

        nombreField = new JTextField();
        telefonoField = new JTextField();
        correoField = new JTextField();

        formPanel.add(nombreLabel);
        formPanel.add(nombreField);
        formPanel.add(telefonoLabel);
        formPanel.add(telefonoField);
        formPanel.add(correoLabel);
        formPanel.add(correoField);

        JButton agregarBtn = new JButton("Agregar");
        JButton eliminarBtn = new JButton("Eliminar");

        estilizarBoton(agregarBtn);
        estilizarBoton(eliminarBtn);

        formPanel.add(agregarBtn);
        formPanel.add(eliminarBtn);

        agregarBtn.addActionListener(e -> agregarContacto());
        eliminarBtn.addActionListener(e -> eliminarContacto());

        // Tabla
        String[] columnas = {"Nombre", "Teléfono", "Correo"};
        tableModel = new DefaultTableModel(columnas, 0);
        contactosTable = new JTable(tableModel);
        contactosTable.setFont(fuente);
        contactosTable.setBackground(new Color(40, 40, 45));
        contactosTable.setForeground(Color.WHITE);
        contactosTable.setRowHeight(30);
        contactosTable.getTableHeader().setBackground(new Color(60, 60, 65));
        contactosTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(contactosTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    private void agregarContacto() {
        String nombre = nombreField.getText().trim();
        String telefono = telefonoField.getText().trim();
        String correo = correoField.getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] contacto = {nombre, telefono, correo};
        contactosList.add(contacto);
        tableModel.addRow(contacto);

        nombreField.setText("");
        telefonoField.setText("");
        correoField.setText("");
    }

    private void eliminarContacto() {
        int fila = contactosTable.getSelectedRow();
        if (fila >= 0) {
            contactosList.remove(fila);
            tableModel.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un contacto para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}
