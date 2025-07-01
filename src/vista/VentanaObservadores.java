package vista;

import modelo.*;
import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class VentanaObservadores extends JFrame {
    private ControladorPrincipal controlador;
    private JTextField txtIdentificador, txtNombre, txtApellido, txtOrganizacion;
    private JComboBox<String> cbExperiencia;
    private JTextArea areaListado;

    public VentanaObservadores() {
        controlador = ControladorPrincipal.getInstance();
        initializeComponents();
        setupWindow();
        actualizarListado();
    }

    private void initializeComponents() {
        setTitle("Gestión de Observadores");
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Observador"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(new JLabel("Identificador:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtIdentificador = new JTextField(15);
        panelFormulario.add(txtIdentificador, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtApellido = new JTextField(15);
        panelFormulario.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Experiencia:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] niveles = {"Principiante", "Intermedio", "Experto"};
        cbExperiencia = new JComboBox<>(niveles);
        panelFormulario.add(cbExperiencia, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Organización:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtOrganizacion = new JTextField(15);
        panelFormulario.add(txtOrganizacion, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnRegistrar = new JButton("Registrar Observador");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        btnRegistrar.addActionListener(e -> registrarObservador());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> setVisible(false));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        // Panel de listado
        JPanel panelListado = new JPanel(new BorderLayout());
        panelListado.setBorder(BorderFactory.createTitledBorder("Observadores Registrados"));

        areaListado = new JTextArea(8, 30);
        areaListado.setEditable(false);
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaListado);

        panelListado.add(scrollPane, BorderLayout.CENTER);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(panelListado, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void registrarObservador() {
        try {
            String identificador = txtIdentificador.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String experiencia = (String) cbExperiencia.getSelectedItem();
            String organizacion = txtOrganizacion.getText().trim();

            if (identificador.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Observador nuevoObservador = new Observador(identificador, nombre, apellido, experiencia, organizacion);

            if (controlador.registrarObservador(nuevoObservador)) {
                JOptionPane.showMessageDialog(this, "Observador registrado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                actualizarListado();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Ya existe un observador con ese identificador.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el observador: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdentificador.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtOrganizacion.setText("");
        cbExperiencia.setSelectedIndex(0);
    }

    private void actualizarListado() {
        StringBuilder listado = new StringBuilder();
        listado.append("ID\t\tNOMBRE\t\t\tEXPERIENCIA\n");
        listado.append("=".repeat(60)).append("\n");

        for (Observador observador : controlador.listarObservadores()) {
            listado.append(String.format("%-10s\t%-20s\t%s\n",
                    observador.getIdentificador(),
                    observador.getNombreCompleto(),
                    observador.getNivelExperiencia()));
        }

        areaListado.setText(listado.toString());
    }
}