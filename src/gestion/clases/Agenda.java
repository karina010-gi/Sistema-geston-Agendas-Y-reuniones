package gestion.clases;

import gestion.excepciones.FechaDuplicadaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agenda {

    private String nombre;
    private HashMap<LocalDate, Dia> dias;

    public Agenda(String nombre) {
        this.nombre = nombre;
        this.dias = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<LocalDate, Dia> getDias() {
        return dias;
    }

    public void setDias(HashMap<LocalDate, Dia> dias) {
        this.dias = dias;
    }

    public void agregarDia(LocalDate fecha)
            throws FechaDuplicadaException {

        if (dias.containsKey(fecha)) {
            throw new FechaDuplicadaException(
                    "La fecha ya existe en la agenda."
            );
        }

        Dia nuevoDia = new Dia(fecha);
        dias.put(fecha, nuevoDia);
    }

    public Dia buscarDia(LocalDate fecha) {
        return dias.get(fecha);
    }

    public void eliminarDia(LocalDate fecha) {
        dias.remove(fecha);
    }

    public List<Actividad> buscarActividades(LocalDate fecha) {

        Dia dia = dias.get(fecha);

        if (dia == null) {
            return new ArrayList<>();
        }

        return dia.getActividades();
    }

    public List<Actividad> buscarActividades(
            LocalDate inicio, LocalDate fin) {

        List<Actividad> resultado = new ArrayList<>();

        for (Dia dia : dias.values()) {

            LocalDate fecha = dia.getFecha();

            if (!fecha.isBefore(inicio)
                    && !fecha.isAfter(fin)) {

                resultado.addAll(dia.getActividades());
            }
        }

        return resultado;
    }

    public List<Actividad> filtrarPorEtiqueta(String nombreEtiqueta) {

        List<Actividad> resultado = new ArrayList<>();

        for (Dia dia : dias.values()) {

            for (Actividad actividad : dia.getActividades()) {

                if (actividad.getEtiqueta() != null
                        && actividad.getEtiqueta()
                                .getNombre()
                                .equalsIgnoreCase(nombreEtiqueta)) {

                    resultado.add(actividad);
                }
            }
        }

        return resultado;
    }

    public Actividad buscarActividadPorId(int id) {

        for (Dia dia : dias.values()) {

            Actividad actividad = dia.buscarActividad(id);

            if (actividad != null) {
                return actividad;
            }
        }

        return null;
    }

    public Actividad buscarActividadPorTitulo(String titulo) {

        for (Dia dia : dias.values()) {

            List<Actividad> resultado =
                    dia.buscarActividad(titulo);

            if (!resultado.isEmpty()) {
                return resultado.get(0);
            }
        }

        return null;
    }
    
    public void modificarDia(LocalDate fechaActual, LocalDate nuevaFecha)
        throws FechaDuplicadaException {

        if (!dias.containsKey(fechaActual)) {
            return;
        }

        if (dias.containsKey(nuevaFecha)) {
            throw new FechaDuplicadaException(
                    "La nueva fecha ya existe en la agenda."
            );
        }

        Dia dia = dias.remove(fechaActual);

        dia.setFecha(nuevaFecha);

        dias.put(nuevaFecha, dia);
    }
    
}