package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SistemaEspecies {
    private ArrayList<Especie> especies;
    private ArrayList<Observador> observadores;
    private ArrayList<Avistamiento> avistamientos;

    public SistemaEspecies() {
        especies = new ArrayList<>();
        observadores = new ArrayList<>();
        avistamientos = new ArrayList<>();
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        // Especies ejemplo
        registrarEspecie(new Especie("ESP001", "Panthera onca", "Jaguar", "Vulnerable", "Selva tropical"));
        registrarEspecie(new Especie("ESP002", "Chelonia mydas", "Tortuga verde", "En peligro", "Océanos tropicales"));
        registrarEspecie(new Especie("ESP003", "Tremarctos ornatus", "Oso de anteojos", "Vulnerable", "Bosques andinos"));

        // Observadores de ejemplo
        registrarObservador(new Observador("OBS001", "María", "González", "Experto", "WWF Ecuador"));
        registrarObservador(new Observador("OBS002", "Carlos", "Rodríguez", "Intermedio", "Fundación EcoCiencia"));
    }

    public boolean registrarEspecie(Especie especie) {
        if (existeEspecie(especie.getCodigo())) {
            return false;
        }
        especies.add(especie);
        return true;
    }

    public boolean registrarObservador(Observador observador) {
        if (existeObservador(observador.getIdentificador())) {
            return false;
        }
        observadores.add(observador);
        return true;
    }

    public boolean registrarAvistamiento(Avistamiento avistamiento) {
        if (avistamiento.validarDatos()) {
            avistamientos.add(avistamiento);
            return true;
        }
        return false;
    }

    public Especie buscarEspecie(String codigo) {
        for (Especie especie : especies) {
            if (especie.getCodigo().equals(codigo)) {
                return especie;
            }
        }
        return null;
    }

    public Observador buscarObservador(String identificador) {
        for (Observador observador : observadores) {
            if (observador.getIdentificador().equals(identificador)) {
                return observador;
            }
        }
        return null;
    }

    public ArrayList<Especie> listarEspecies() {
        return new ArrayList<>(especies);
    }

    public ArrayList<Observador> listarObservadores() {
        return new ArrayList<>(observadores);
    }

    public ArrayList<Avistamiento> listarAvistamientos() {
        return new ArrayList<>(avistamientos);
    }

    public ArrayList<Avistamiento> consultarAvistamientosPorEspecie(String codigoEspecie) {
        ArrayList<Avistamiento> resultado = new ArrayList<>();
        for (Avistamiento avistamiento : avistamientos) {
            if (avistamiento.getEspecieAvistada().getCodigo().equals(codigoEspecie)) {
                resultado.add(avistamiento);
            }
        }
        return resultado;
    }

    public ArrayList<Avistamiento> consultarAvistamientosPorUbicacion(String ubicacion) {
        ArrayList<Avistamiento> resultado = new ArrayList<>();
        for (Avistamiento avistamiento : avistamientos) {
            if (avistamiento.getUbicacion().toLowerCase().contains(ubicacion.toLowerCase())) {
                resultado.add(avistamiento);
            }
        }
        return resultado;
    }

    public String calcularEstadisticas() {
        if (avistamientos.isEmpty()) {
            return "No hay avistamientos registrados.";
        }

        // Contar avistamientos
        Map<String, Integer> avistamientosPorEspecie = new HashMap<>();
        int totalIndividuos = 0;

        for (Avistamiento avistamiento : avistamientos) {
            String nombreEspecie = avistamiento.getEspecieAvistada().getNombreComun();
            avistamientosPorEspecie.put(nombreEspecie,
                    avistamientosPorEspecie.getOrDefault(nombreEspecie, 0) + 1);
            totalIndividuos += avistamiento.getNumeroIndividuos();
        }

        // Encontrar especie con más avistamientos
        String especieMasAvistada = "";
        int maxAvistamientos = 0;
        for (Map.Entry<String, Integer> entry : avistamientosPorEspecie.entrySet()) {
            if (entry.getValue() > maxAvistamientos) {
                maxAvistamientos = entry.getValue();
                especieMasAvistada = entry.getKey();
            }
        }

        double promedio = (double) totalIndividuos / avistamientos.size();

        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("=== ESTADÍSTICAS DEL SISTEMA ===\n");
        estadisticas.append("Total de avistamientos: ").append(avistamientos.size()).append("\n");
        estadisticas.append("Total de individuos observados: ").append(totalIndividuos).append("\n");
        estadisticas.append("Promedio de individuos por avistamiento: ").append(String.format("%.2f", promedio)).append("\n");
        estadisticas.append("Especie más avistada: ").append(especieMasAvistada)
                .append(" (").append(maxAvistamientos).append(" avistamientos)\n");

        // Contar especie por categoría
        Map<String, Integer> especiesPorCategoria = new HashMap<>();
        for (Especie especie : especies) {
            String categoria = especie.getCategoriaConservacion();
            especiesPorCategoria.put(categoria, especiesPorCategoria.getOrDefault(categoria, 0) + 1);
        }

        estadisticas.append("\n=== ESPECIES POR CATEGORÍA ===\n");
        for (Map.Entry<String, Integer> entry : especiesPorCategoria.entrySet()) {
            estadisticas.append(entry.getKey()).append(": ").append(entry.getValue()).append(" especies\n");
        }

        return estadisticas.toString();
    }

    public ArrayList<String> clasificarZonas() {
        ArrayList<String> zonasClasificadas = new ArrayList<>();
        Map<String, String> clasificaciones = new HashMap<>();

        for (Avistamiento avistamiento : avistamientos) {
            String ubicacion = avistamiento.getUbicacion();
            String categoria = avistamiento.getEspecieAvistada().getCategoriaConservacion();

            if (categoria.equalsIgnoreCase("En peligro crítico") || categoria.equalsIgnoreCase("Extinta")) {
                clasificaciones.put(ubicacion, "Zona crítica");
            } else if (categoria.equalsIgnoreCase("En peligro") &&
                    !clasificaciones.containsKey(ubicacion)) {
                clasificaciones.put(ubicacion, "Zona de alta prioridad");
            } else if (!clasificaciones.containsKey(ubicacion)) {
                clasificaciones.put(ubicacion, "Zona de monitoreo");
            }
        }

        for (Map.Entry<String, String> entry : clasificaciones.entrySet()) {
            zonasClasificadas.add(entry.getKey() + " - " + entry.getValue());
        }

        return zonasClasificadas;
    }

    public String generarReporte(String tipoReporte) {
        StringBuilder reporte = new StringBuilder();

        switch (tipoReporte.toLowerCase()) {
            case "especies":
                reporte.append("=== REPORTE DE ESPECIES ===\n");
                for (Especie especie : especies) {
                    reporte.append("Código: ").append(especie.getCodigo()).append("\n");
                    reporte.append("Nombre científico: ").append(especie.getNombreCientifico()).append("\n");
                    reporte.append("Nombre común: ").append(especie.getNombreComun()).append("\n");
                    reporte.append("Categoría: ").append(especie.getCategoriaConservacion()).append("\n");
                    reporte.append("Hábitat: ").append(especie.getHabitatPrincipal()).append("\n");
                    reporte.append("---\n");
                }
                break;

            case "avistamientos":
                reporte.append("=== REPORTE DE AVISTAMIENTOS ===\n");
                for (Avistamiento avistamiento : avistamientos) {
                    reporte.append("ID: ").append(avistamiento.getIdentificador()).append("\n");
                    reporte.append("Especie: ").append(avistamiento.getEspecieAvistada().getNombreComun()).append("\n");
                    reporte.append("Fecha: ").append(avistamiento.getFecha()).append("\n");
                    reporte.append("Ubicación: ").append(avistamiento.getUbicacion()).append("\n");
                    reporte.append("Individuos: ").append(avistamiento.getNumeroIndividuos()).append("\n");
                    reporte.append("Observador: ").append(avistamiento.getObservadorReporte().getNombreCompleto()).append("\n");
                    reporte.append("---\n");
                }
                break;

            case "observadores":
                reporte.append("=== REPORTE DE OBSERVADORES ===\n");
                for (Observador observador : observadores) {
                    int conteoAvistamientos = 0;
                    for (Avistamiento avistamiento : avistamientos) {
                        if (avistamiento.getObservadorReporte().getIdentificador().equals(observador.getIdentificador())) {
                            conteoAvistamientos++;
                        }
                    }
                    reporte.append("ID: ").append(observador.getIdentificador()).append("\n");
                    reporte.append("Nombre: ").append(observador.getNombreCompleto()).append("\n");
                    reporte.append("Experiencia: ").append(observador.getNivelExperiencia()).append("\n");
                    reporte.append("Organización: ").append(observador.getOrganizacion()).append("\n");
                    reporte.append("Avistamientos reportados: ").append(conteoAvistamientos).append("\n");
                    reporte.append("---\n");
                }
                break;

            default:
                reporte.append("Tipo de reporte no válido.");
        }

        return reporte.toString();
    }

    public boolean existeEspecie(String codigo) {
        return buscarEspecie(codigo) != null;
    }

    public boolean existeObservador(String identificador) {
        return buscarObservador(identificador) != null;
    }
}