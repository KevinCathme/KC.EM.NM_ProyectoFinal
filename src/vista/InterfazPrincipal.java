package vista;

import javax.swing.*;
import java.awt.*;

public class InterfazPrincipal extends JFrame {
    private VentanaEspecies ventanaEspecies;
    private VentanaObservadores ventanaObservadores;
    private VentanaAvistamientos ventanaAvistamientos;

    public InterfazPrincipal() {
        initializeComponents();
        setupWindow();
    }

    private void initializeComponents() {
        setTitle("Sistema de Registro y Seguimiento de Especies en Peligro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel del título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(34, 139, 34));
        JLabel titulo = new JLabel("Sistema de Especies en Peligro de Extinción");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton btnEspecies = createStyledButton("Gestionar Especies", new Color(46, 125, 50));
        JButton btnObservadores = createStyledButton("Gestionar Observadores", new Color(25, 118, 210));
        JButton btnAvistamientos = createStyledButton("Gestionar Avistamientos", new Color(255, 111, 97));
        JButton btnSalir = createStyledButton("Salir", new Color(158, 158, 158));

        gbc.gridx = 0; gbc.gridy = 0;
        panelBotones.add(btnEspecies, gbc);
        gbc.gridy = 1;
        panelBotones.add(btnObservadores, gbc);
        gbc.gridy = 2;
        panelBotones.add(btnAvistamientos, gbc);
        gbc.gridy = 3;
        panelBotones.add(btnSalir, gbc);

        // Event listeners
        btnEspecies.addActionListener(e -> abrirVentanaEspecies());
        btnObservadores.addActionListener(e -> abrirVentanaObservadores());
        btnAvistamientos.addActionListener(e -> abrirVentanaAvistamientos());
        btnSalir.addActionListener(e -> System.exit(0));

        add(panelTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        // Panel de información
        JPanel panelInfo = new JPanel();
        JLabel info = new JLabel("Fundación Biodiversa - Sistema de Conservación v1.0");
        info.setFont(new Font("Arial", Font.ITALIC, 12));
        panelInfo.add(info);
        add(panelInfo, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 50));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void setupWindow() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void abrirVentanaEspecies() {
        if (ventanaEspecies == null) {
            ventanaEspecies = new VentanaEspecies();
        }
        ventanaEspecies.setVisible(true);
    }

    private void abrirVentanaObservadores() {
        if (ventanaObservadores == null) {
            ventanaObservadores = new VentanaObservadores();
        }
        ventanaObservadores.setVisible(true);
    }

    private void abrirVentanaAvistamientos() {
        if (ventanaAvistamientos == null) {
            ventanaAvistamientos = new VentanaAvistamientos();
        }
        ventanaAvistamientos.setVisible(true);
    }
}