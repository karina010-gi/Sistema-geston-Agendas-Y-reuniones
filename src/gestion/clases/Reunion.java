package gestion.clases;

import java.time.LocalTime;
import java.util.ArrayList;

public class Reunion extends Actividad {

    private String lugar;
    private ArrayList<Participante> participantes;

    public Reunion(int id, String titulo, String descripcion,
                   LocalTime horaInicio, LocalTime horaFin,
                   Etiqueta etiqueta, String lugar) {

        super(id, titulo, descripcion, horaInicio, horaFin, etiqueta);

        this.lugar = lugar;
        this.participantes = new ArrayList<>();
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
    }

    @Override
    public String mostrarDetalle() {
        return "Reunión: " + getTitulo()
                + " | Lugar: " + lugar;
    }
}