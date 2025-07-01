package vista;

import modelo.*;
import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class VentanaEspecies extends JFrame {
    private ControladorPrincipal controlador;
    private JTextField txtCodigo, txtNombreCientifico, txtNombreComun, txtHabitat;
    private JComboBox<String> cbCategoria;
    private JTextArea areaListado;

    public VentanaEspecies() {
        controlador = ControladorPrincipal.getInstance();
        initializeComponents();
        setupWindow();
        actualizarListado();
    }

    private void initializeComponents() {
        setTitle("Gestión de Especies");
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Nueva Especie"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos del formulario
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCodigo = new JTextField(15);
        panelFormulario.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Nombre Científico:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombreCientifico = new JTextField(15);
        panelFormulario.add(txtNombreCientifico, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Nombre Común:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombreComun = new JTextField(15);
        panelFormulario.add(txtNombreComun, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] categorias = {"Extinta", "En peligro crítico", "En peligro", "Vulnerable", "Casi amenazada", "Preocupación menor"};
        cbCategoria = new JComboBox<>(categorias);
        panelFormulario.add(cbCategoria, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Hábitat:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtHabitat = new JTextField(15);
        panelFormulario.add(txtHabitat, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnRegistrar = new JButton("Registrar Especie");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        btnRegistrar.addActionListener(e -> registrarEspecie());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> setVisible(false));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        // Panel de listado
        JPanel panelListado = new JPanel(new BorderLayout());
        panelListado.setBorder(BorderFactory.createTitledBorder("Especies Registradas"));

        areaListado = new JTextArea(10, 30);
        areaListado.setEditable(false);
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaListado);

        panelListado.add(scrollPane, BorderLayout.CENTER);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(panelListado, BorderLayout.SOUTH);
    }

    private void setupWindow() {
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void registrarEspecie() {
        try {
            String codigo = txtCodigo.getText().trim();
            String nombreCientifico = txtNombreCientifico.getText().trim();
            String nombreComun = txtNombreComun.getText().trim();
            String categoria = (String) cbCategoria.getSelectedItem();
            String habitat = txtHabitat.getText().trim();

            if (codigo.isEmpty() || nombreCientifico.isEmpty() || nombreComun.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Especie nuevaEspecie = new Especie(codigo, nombreCientifico, nombreComun, categoria, habitat);

            if (controlador.registrarEspecie(nuevaEspecie)) {
                JOptionPane.showMessageDialog(this, "Especie registrada exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                actualizarListado();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Ya existe una especie con ese código.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la especie: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombreCientifico.setText("");
        txtNombreComun.setText("");
        txtHabitat.setText("");
        cbCategoria.setSelectedIndex(0);
    }

    private void actualizarListado() {
        StringBuilder listado = new StringBuilder();
        listado.append("CÓDIGO\t\tNOMBRE COMÚN\t\tCATEGORÍA\n");
        listado.append("=".repeat(70)).append("\n");

        for (Especie especie : controlador.listarEspecies()) {
            listado.append(String.format("%-10s\t%-20s\t%s\n",
                    especie.getCodigo(),
                    especie.getNombreComun(),
                    especie.getCategoriaConservacion()));
        }

        areaListado.setText(listado.toString());
    }
}