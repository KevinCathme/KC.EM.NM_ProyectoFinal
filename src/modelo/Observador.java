package modelo;

public class Observador {
    private String identificador;
    private String nombre;
    private String apellido;
    private String nivelExperiencia;
    private String organizacion;

    public Observador(String identificador, String nombre, String apellido,
                      String nivelExperiencia, String organizacion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivelExperiencia = nivelExperiencia;
        this.organizacion = organizacion;
    }

    // Getters y Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(String nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return identificador + " - " + getNombreCompleto() + " (" + nivelExperiencia + ")";
    }
}