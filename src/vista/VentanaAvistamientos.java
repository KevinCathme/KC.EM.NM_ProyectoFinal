// archivo: src/vista/VentanaAvistamientos.java
package vista;

import modelo.*;
import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VentanaAvistamientos extends JFrame {
    private ControladorPrincipal controlador;
    private JTextField txtIdentificador, txtFecha, txtUbicacion, txtNumeroIndividuos;
    private JComboBox<Especie> cbEspecies;
    private JComboBox<Observador> cbObservadores;
    private JTextArea areaListado;
    private JTextArea areaEstadisticas;

    public VentanaAvistamientos() {
        controlador = ControladorPrincipal.getInstance();
        initializeComponents();
        setupWindow();
        actualizarComboBoxes();
        actualizarListado();
        actualizarEstadisticas();
    }

    private void initializeComponents() {
        setTitle("Gestión de Avistamientos");
        setLayout(new BorderLayout());

        // Panel principal con tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Registro de avistamientos
        JPanel panelRegistro = createRegistroPanel();
        tabbedPane.addTab("Registrar Avistamiento", panelRegistro);

        // Tab 2: Consultas y reportes
        JPanel panelConsultas = createConsultasPanel();
        tabbedPane.addTab("Consultas y Estadísticas", panelConsultas);

        add(tabbedPane, BorderLayout.CENTER);

        // Panel de botones inferior
        JPanel panelBotonesInferior = new JPanel(new FlowLayout());
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> setVisible(false));
        panelBotonesInferior.add(btnCerrar);

        add(panelBotonesInferior, BorderLayout.SOUTH);
    }

    private JPanel createRegistroPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Avistamiento"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(new JLabel("Identificador:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtIdentificador = new JTextField(15);
        panelFormulario.add(txtIdentificador, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cbEspecies = new JComboBox<>();
        panelFormulario.add(cbEspecies, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Observador:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cbObservadores = new JComboBox<>();
        panelFormulario.add(cbObservadores, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Fecha (DD/MM/YYYY):"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFecha = new JTextField(15);
        txtFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        panelFormulario.add(txtFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Ubicación:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtUbicacion = new JTextField(15);
        panelFormulario.add(txtUbicacion, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(new JLabel("Número de Individuos:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNumeroIndividuos = new JTextField(15);
        panelFormulario.add(txtNumeroIndividuos, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnRegistrar = new JButton("Registrar Avistamiento");
        JButton btnLimpiar = new JButton("Limpiar");

        btnRegistrar.addActionListener(e -> registrarAvistamiento());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);

        // Panel de listado
        JPanel panelListado = new JPanel(new BorderLayout());
        panelListado.setBorder(BorderFactory.createTitledBorder("Avistamientos Registrados"));

        areaListado = new JTextArea(8, 40);
        areaListado.setEditable(false);
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(areaListado);

        panelListado.add(scrollPane, BorderLayout.CENTER);

        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(panelListado, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createConsultasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de estadísticas
        JPanel panelEstadisticas = new JPanel(new BorderLayout());
        panelEstadisticas.setBorder(BorderFactory.createTitledBorder("Estadísticas del Sistema"));

        areaEstadisticas = new JTextArea(15, 50);
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);

        panelEstadisticas.add(scrollEstadisticas, BorderLayout.CENTER);

        // Panel de botones para reportes
        JPanel panelReportes = new JPanel(new FlowLayout());
        JButton btnActualizar = new JButton("Actualizar Estadísticas");
        JButton btnZonas = new JButton("Clasificar Zonas");
        JButton btnReporteEspecies = new JButton("Reporte Especies");
        JButton btnReporteAvistamientos = new JButton("Reporte Avistamientos");

        btnActualizar.addActionListener(e -> actualizarEstadisticas());
        btnZonas.addActionListener(e -> mostrarClasificacionZonas());
        btnReporteEspecies.addActionListener(e -> mostrarReporte("especies"));
        btnReporteAvistamientos.addActionListener(e -> mostrarReporte("avistamientos"));

        panelReportes.add(btnActualizar);
        panelReportes.add(btnZonas);
        panelReportes.add(btnReporteEspecies);
        panelReportes.add(btnReporteAvistamientos);

        panel.add(panelEstadisticas, BorderLayout.CENTER);
        panel.add(panelReportes, BorderLayout.SOUTH);

        return panel;
    }

    private void setupWindow() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void registrarAvistamiento() {
        try {
            String identificador = txtIdentificador.getText().trim();
            String fecha = txtFecha.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String numIndividuosStr = txtNumeroIndividuos.getText().trim();

            if (identificador.isEmpty() || fecha.isEmpty() || ubicacion.isEmpty() || numIndividuosStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cbEspecies.getSelectedItem() == null || cbObservadores.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una especie y un observador.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numeroIndividuos = Integer.parseInt(numIndividuosStr);
            if (numeroIndividuos <= 0) {
                JOptionPane.showMessageDialog(this, "El número de individuos debe ser mayor a cero.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Especie especie = (Especie) cbEspecies.getSelectedItem();
            Observador observador = (Observador) cbObservadores.getSelectedItem();

            Avistamiento nuevoAvistamiento = new Avistamiento(identificador, fecha, ubicacion,
                    numeroIndividuos, especie, observador);

            if (controlador.registrarAvistamiento(nuevoAvistamiento)) {
                JOptionPane.showMessageDialog(this, "Avistamiento registrado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                actualizarListado();
                actualizarEstadisticas();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Datos del avistamiento no válidos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de individuos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el avistamiento: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdentificador.setText("");
        txtUbicacion.setText("");
        txtNumeroIndividuos.setText("");
        txtFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (cbEspecies.getItemCount() > 0) cbEspecies.setSelectedIndex(0);
        if (cbObservadores.getItemCount() > 0) cbObservadores.setSelectedIndex(0);
    }

    private void actualizarComboBoxes() {
        cbEspecies.removeAllItems();
        for (Especie especie : controlador.listarEspecies()) {
            cbEspecies.addItem(especie);
        }

        cbObservadores.removeAllItems();
        for (Observador observador : controlador.listarObservadores()) {
            cbObservadores.addItem(observador);
        }
    }

    private void actualizarListado() {
        StringBuilder listado = new StringBuilder();
        listado.append("ID\t\tESPECIE\t\t\tUBICACIÓN\t\tFECHA\t\tINDIV.\n");
        listado.append("=".repeat(80)).append("\n");

        for (Avistamiento avistamiento : controlador.listarAvistamientos()) {
            listado.append(String.format("%-10s\t%-15s\t%-15s\t%-12s\t%d\n",
                    avistamiento.getIdentificador(),
                    avistamiento.getEspecieAvistada().getNombreComun(),
                    avistamiento.getUbicacion(),
                    avistamiento.getFecha(),
                    avistamiento.getNumeroIndividuos()));
        }

        areaListado.setText(listado.toString());
    }

    private void actualizarEstadisticas() {
        String estadisticas = controlador.calcularEstadisticas();
        areaEstadisticas.setText(estadisticas);
    }

    private void mostrarClasificacionZonas() {
        StringBuilder zonas = new StringBuilder();
        zonas.append("=== CLASIFICACIÓN DE ZONAS ===\n\n");

        for (String zona : controlador.clasificarZonas()) {
            zonas.append(zona).append("\n");
        }

        JTextArea areaZonas = new JTextArea(zonas.toString());
        areaZonas.setEditable(false);
        areaZonas.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(areaZonas);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Clasificación de Zonas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarReporte(String tipoReporte) {
        String reporte = controlador.generarReporte(tipoReporte);

        JTextArea areaReporte = new JTextArea(reporte);
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 11));

        JScrollPane scrollPane = new JScrollPane(areaReporte);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        String titulo = "Reporte de " + tipoReporte.substring(0, 1).toUpperCase() + tipoReporte.substring(1);
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}