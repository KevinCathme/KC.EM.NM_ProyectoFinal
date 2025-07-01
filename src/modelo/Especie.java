// archivo: src/modelo/Especie.java
package modelo;

public class Especie {
    private String codigo;
    private String nombreCientifico;
    private String nombreComun;
    private String categoriaConservacion;
    private String habitatPrincipal;

    public Especie(String codigo, String nombreCientifico, String nombreComun,
                   String categoriaConservacion, String habitatPrincipal) {
        this.codigo = codigo;
        this.nombreCientifico = nombreCientifico;
        this.nombreComun = nombreComun;
        this.categoriaConservacion = categoriaConservacion;
        this.habitatPrincipal = habitatPrincipal;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getCategoriaConservacion() {
        return categoriaConservacion;
    }

    public void setCategoriaConservacion(String categoriaConservacion) {
        this.categoriaConservacion = categoriaConservacion;
    }

    public String getHabitatPrincipal() {
        return habitatPrincipal;
    }

    public void setHabitatPrincipal(String habitatPrincipal) {
        this.habitatPrincipal = habitatPrincipal;
    }

    public boolean esCritica() {
        return categoriaConservacion.equalsIgnoreCase("En peligro crítico") ||
                categoriaConservacion.equalsIgnoreCase("Extinta");
    }

    @Override
    public String toString() {
        return codigo + " - " + nombreComun + " (" + nombreCientifico + ")";
    }
}