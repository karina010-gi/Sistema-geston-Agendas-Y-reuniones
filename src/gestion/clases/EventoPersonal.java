package gestion.clases;

import java.time.LocalTime;

public class EventoPersonal extends Actividad {

    public EventoPersonal(int id, String titulo, String descripcion,
                          LocalTime horaInicio, LocalTime horaFin,
                          Etiqueta etiqueta) {

        super(id, titulo, descripcion, horaInicio, horaFin, etiqueta);
    }

    @Override
    public String mostrarDetalle() {
        return "Evento Personal: " + getTitulo();
    }
}