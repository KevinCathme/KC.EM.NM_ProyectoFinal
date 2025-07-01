// archivo: src/controlador/ControladorPrincipal.java
package controlador;

import modelo.*;
import java.util.ArrayList;

public class ControladorPrincipal {
    private static ControladorPrincipal instance;
    private SistemaEspecies sistema;

    private ControladorPrincipal() {
        sistema = new SistemaEspecies();
    }

    public static ControladorPrincipal getInstance() {
        if (instance == null) {
            instance = new ControladorPrincipal();
        }
        return instance;
    }

    // Métodos para especies
    public boolean registrarEspecie(Especie especie) {
        return sistema.registrarEspecie(especie);
    }

    public ArrayList<Especie> listarEspecies() {
        return sistema.listarEspecies();
    }

    public Especie buscarEspecie(String codigo) {
        return sistema.buscarEspecie(codigo);
    }

    // Métodos para observadores
    public boolean registrarObservador(Observador observador) {
        return sistema.registrarObservador(observador);
    }

    public ArrayList<Observador> listarObservadores() {
        return sistema.listarObservadores();
    }

    public Observador buscarObservador(String identificador) {
        return sistema.buscarObservador(identificador);
    }

    // Métodos para avistamientos
    public boolean registrarAvistamiento(Avistamiento avistamiento) {
        return sistema.registrarAvistamiento(avistamiento);
    }

    public ArrayList<Avistamiento> listarAvistamientos() {
        return sistema.listarAvistamientos();
    }

    public ArrayList<Avistamiento> consultarAvistamientosPorEspecie(String codigoEspecie) {
        return sistema.consultarAvistamientosPorEspecie(codigoEspecie);
    }

    public ArrayList<Avistamiento> consultarAvistamientosPorUbicacion(String ubicacion) {
        return sistema.consultarAvistamientosPorUbicacion(ubicacion);
    }

    // Métodos para estadísticas y reportes
    public String calcularEstadisticas() {
        return sistema.calcularEstadisticas();
    }

    public ArrayList<String> clasificarZonas() {
        return sistema.clasificarZonas();
    }

    public String generarReporte(String tipoReporte) {
        return sistema.generarReporte(tipoReporte);
    }
}