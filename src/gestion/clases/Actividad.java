package gestion.clases;

import java.time.LocalTime;

public abstract class Actividad {

    private int id;
    private String titulo;
    private String descripcion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Etiqueta etiqueta;

    public Actividad(int id, String titulo, String descripcion,
                     LocalTime horaInicio, LocalTime horaFin,
                     Etiqueta etiqueta) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.etiqueta = etiqueta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public abstract String mostrarDetalle();
}