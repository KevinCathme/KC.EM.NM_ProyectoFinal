// archivo: src/modelo/Avistamiento.jav
package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Avistamiento {
    private String identificador;
    private String fecha;
    private String ubicacion;
    private int numeroIndividuos;
    private Especie especieAvistada;
    private Observador observadorReporte;

    public Avistamiento(String identificador, String fecha, String ubicacion,
                        int numeroIndividuos, Especie especieAvistada, Observador observadorReporte) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.numeroIndividuos = numeroIndividuos;
        this.especieAvistada = especieAvistada;
        this.observadorReporte = observadorReporte;
    }

    // Getters y Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getNumeroIndividuos() {
        return numeroIndividuos;
    }

    public void setNumeroIndividuos(int numeroIndividuos) {
        this.numeroIndividuos = numeroIndividuos;
    }

    public Especie getEspecieAvistada() {
        return especieAvistada;
    }

    public void setEspecieAvistada(Especie especieAvistada) {
        this.especieAvistada = especieAvistada;
    }

    public Observador getObservadorReporte() {
        return observadorReporte;
    }

    public void setObservadorReporte(Observador observadorReporte) {
        this.observadorReporte = observadorReporte;
    }

    public boolean validarDatos() {
        if (numeroIndividuos <= 0) return false;

        try {
            LocalDate fechaAvistamiento = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaActual = LocalDate.now();
            return !fechaAvistamiento.isAfter(fechaActual);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return identificador + " - " + especieAvistada.getNombreComun() +
                " en " + ubicacion + " (" + fecha + ")";
    }
}