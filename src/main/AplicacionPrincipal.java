package main;

import vista.InterfazPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AplicacionPrincipal {
    public static void main(String[] args) {
        // Configurar el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel del sistema: " + e.getMessage());
        }

        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                InterfazPrincipal ventanaPrincipal = new InterfazPrincipal();
                ventanaPrincipal.setVisible(true);
                System.out.println("Sistema de Especies en Peligro iniciado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}