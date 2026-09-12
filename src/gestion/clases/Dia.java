package gestion.clases;
import gestion.excepciones.HorarioInvalidoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dia {

    private LocalDate fecha;
    private ArrayList<Actividad> actividades;

    public Dia(LocalDate fecha) {
        this.fecha = fecha;
        this.actividades = new ArrayList<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void agregarActividad(Actividad actividad)
            throws HorarioInvalidoException {

        if (!actividad.getHoraInicio().isBefore(actividad.getHoraFin())) {
            throw new HorarioInvalidoException(
                    "La hora de inicio debe ser anterior a la hora de término."
            );
        }

        for (Actividad otra : actividades) {

            boolean existeConflicto =
                    actividad.getHoraInicio().isBefore(otra.getHoraFin())
                    && actividad.getHoraFin().isAfter(otra.getHoraInicio());

            if (existeConflicto) {
                throw new HorarioInvalidoException(
                        "Existe un conflicto de horario con otra actividad."
                );
            }
        }

        actividades.add(actividad);
    }

    public void eliminarActividad(int id) {

        for (int i = 0; i < actividades.size(); i++) {

            if (actividades.get(i).getId() == id) {
                actividades.remove(i);
                return;
            }
        }
    }

    public Actividad buscarActividad(int id) {

        for (Actividad actividad : actividades) {

            if (actividad.getId() == id) {
                return actividad;
            }
        }

        return null;
    }

    public List<Actividad> buscarActividad(String titulo) {

        List<Actividad> resultado = new ArrayList<>();

        titulo = titulo.trim();

        for (Actividad actividad : actividades) {

            if (actividad.getTitulo().trim().equalsIgnoreCase(titulo)) {
                resultado.add(actividad);
            }
        }

        return resultado;
    }
    
    public void modificarActividad(Actividad nueva)
            throws HorarioInvalidoException {

        for (int i = 0; i < actividades.size(); i++) {

            if (actividades.get(i).getId()
                    == nueva.getId()) {

                Actividad anterior = actividades.get(i);

                actividades.remove(i);

                try {

                    agregarActividad(nueva);

                } catch (HorarioInvalidoException e) {

                    actividades.add(i, anterior);
                    throw e;
                }

                return;
            }
        }
    }
}
