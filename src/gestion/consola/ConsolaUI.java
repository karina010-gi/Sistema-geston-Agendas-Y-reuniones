package gestion.consola;

import gestion.clases.Agenda;
import gestion.clases.Actividad;
import gestion.clases.Dia;
import gestion.clases.Etiqueta;
import gestion.clases.Reunion;
import gestion.clases.EventoPersonal;
import gestion.clases.Participante;

import gestion.excepciones.FechaDuplicadaException;
import gestion.excepciones.HorarioInvalidoException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsolaUI {

    private Agenda agenda;
    private Scanner scanner;
    private DateTimeFormatter formatoFecha;

    public ConsolaUI(Agenda agenda) {
        this.agenda = agenda;
        this.scanner = new Scanner(System.in);
        this.formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {

                case 1:
                    agregarDia();
                    break;

                case 2:
                    listarDias();
                    break;

                case 3:
                    modificarDia();
                    break;

                case 4:
                    eliminarDia();
                    break;

                case 5:
                    buscarDia();
                    break;

                case 6:
                    agregarActividad();
                    break;

                case 7:
                    listarActividades();
                    break;

                case 8:
                    modificarActividad();
                    break;

                case 9:
                    eliminarActividad();
                    break;

                case 10:
                    buscarActividad();
                    break;

                case 11:
                    consultarAgenda();
                    break;

                case 12:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 12);
    }

    private void mostrarMenu() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("     SISTEMA DE GESTIÓN DE AGENDA");
        System.out.println("======================================");

        System.out.println();
        System.out.println("=== GESTIÓN DE DÍAS ===");
        System.out.println("1. Agregar día");
        System.out.println("2. Listar días");
        System.out.println("3. Modificar día");
        System.out.println("4. Eliminar día");
        System.out.println("5. Buscar día");

        System.out.println();
        System.out.println("=== GESTIÓN DE ACTIVIDADES ===");
        System.out.println("6. Agregar actividad");
        System.out.println("7. Listar actividades de un día");
        System.out.println("8. Modificar actividad");
        System.out.println("9. Eliminar actividad");
        System.out.println("10. Buscar actividad");

        System.out.println();
        System.out.println("=== UTILIDADES ===");
        System.out.println("11. Consultar agenda por período y etiqueta");
        System.out.println("12. Salir");

        System.out.println();
    }

    // ==========================================
    // 1. AGREGAR DÍA
    // ==========================================

    private void agregarDia() {

        try {

            LocalDate fecha = leerFecha(
                    "Ingrese la fecha (dd/MM/yyyy): "
            );

            agenda.agregarDia(fecha);

            System.out.println(
                    "Día agregado correctamente."
            );

        } catch (FechaDuplicadaException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // ==========================================
    // 2. LISTAR DÍAS
    // ==========================================

    private void listarDias() {

        if (agenda.getDias().isEmpty()) {

            System.out.println(
                    "No hay días registrados."
            );

            return;
        }

        System.out.println();
        System.out.println("DÍAS REGISTRADOS:");

        for (Dia dia : agenda.getDias().values()) {

            System.out.println(
                    "- " + dia.getFecha().format(formatoFecha)
            );
        }
    }

    // ==========================================
    // 3. MODIFICAR DÍA
    // ==========================================

    private void modificarDia() {

        try {

            LocalDate fechaActual = leerFecha(
                    "Ingrese la fecha actual: "
            );

            if (agenda.buscarDia(fechaActual) == null) {

                System.out.println(
                        "El día no existe."
                );

                return;
            }

            LocalDate nuevaFecha = leerFecha(
                    "Ingrese la nueva fecha: "
            );

            agenda.modificarDia(
                    fechaActual,
                    nuevaFecha
            );

            System.out.println(
                    "Día modificado correctamente."
            );

        } catch (FechaDuplicadaException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // ==========================================
    // 4. ELIMINAR DÍA
    // ==========================================

    private void eliminarDia() {

        LocalDate fecha = leerFecha(
                "Ingrese la fecha a eliminar: "
        );

        if (agenda.buscarDia(fecha) == null) {

            System.out.println(
                    "El día no existe."
            );

            return;
        }

        agenda.eliminarDia(fecha);

        System.out.println(
                "Día eliminado correctamente."
        );
    }

    // ==========================================
    // 5. BUSCAR DÍA
    // ==========================================

    private void buscarDia() {

        LocalDate fecha = leerFecha(
                "Ingrese la fecha a buscar: "
        );

        Dia dia = agenda.buscarDia(fecha);

        if (dia == null) {

            System.out.println(
                    "No se encontró el día."
            );

            return;
        }

        System.out.println(
                "Día encontrado: "
                + dia.getFecha().format(formatoFecha)
        );

        System.out.println(
                "Cantidad de actividades: "
                + dia.getActividades().size()
        );
    }

    // ==========================================
    // 6. AGREGAR ACTIVIDAD
    // ==========================================

    private void agregarActividad() {

        LocalDate fecha = leerFecha(
                "Ingrese la fecha de la actividad: "
        );

        Dia dia = agenda.buscarDia(fecha);

        if (dia == null) {

            System.out.println(
                    "El día no existe. Debe crearlo primero."
            );

            return;
        }

        int id = leerEntero("ID de la actividad: ");

        String titulo = leerTexto("Título: ");

        String descripcion =
                leerTexto("Descripción: ");

        LocalTime inicio =
                leerHora("Hora de inicio (HH:mm): ");

        LocalTime fin =
                leerHora("Hora de término (HH:mm): ");

        String nombreEtiqueta =
                leerTexto("Etiqueta: ");

        Etiqueta etiqueta =
                new Etiqueta(nombreEtiqueta, "");

        System.out.println();
        System.out.println("Tipo de actividad:");
        System.out.println("1. Reunión");
        System.out.println("2. Evento personal");

        int tipo = leerEntero("Seleccione: ");

        Actividad actividad;

        if (tipo == 1) {

            String lugar =
                    leerTexto("Lugar: ");

            actividad = new Reunion(
                    id,
                    titulo,
                    descripcion,
                    inicio,
                    fin,
                    etiqueta,
                    lugar
            );

        } else if (tipo == 2) {

            actividad = new EventoPersonal(
                    id,
                    titulo,
                    descripcion,
                    inicio,
                    fin,
                    etiqueta
            );

        } else {

            System.out.println(
                    "Tipo inválido."
            );

            return;
        }

        try {

            dia.agregarActividad(actividad);

            System.out.println(
                    "Actividad agregada correctamente."
            );

        } catch (HorarioInvalidoException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // ==========================================
    // 7. LISTAR ACTIVIDADES
    // ==========================================

    private void listarActividades() {

        LocalDate fecha = leerFecha(
                "Ingrese la fecha: "
        );

        Dia dia = agenda.buscarDia(fecha);

        if (dia == null) {

            System.out.println(
                    "El día no existe."
            );

            return;
        }

        if (dia.getActividades().isEmpty()) {

            System.out.println(
                    "No hay actividades para este día."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "ACTIVIDADES DEL "
                + fecha.format(formatoFecha)
        );

        for (Actividad actividad :
                dia.getActividades()) {

            mostrarActividad(actividad);
        }
    }

    // ==========================================
    // 8. MODIFICAR ACTIVIDAD
    // ==========================================

    private void modificarActividad() {

        LocalDate fecha = leerFecha(
                "Ingrese la fecha: "
        );

        Dia dia = agenda.buscarDia(fecha);

        if (dia == null) {

            System.out.println(
                    "El día no existe."
            );

            return;
        }

        int id = leerEntero(
                "Ingrese el ID de la actividad: "
        );

        Actividad anterior =
                dia.buscarActividad(id);

        if (anterior == null) {

            System.out.println(
                    "La actividad no existe."
            );

            return;
        }

        String titulo =
                leerTexto("Nuevo título: ");

        String descripcion =
                leerTexto("Nueva descripción: ");

        LocalTime inicio =
                leerHora("Nueva hora de inicio (HH:mm): ");

        LocalTime fin =
                leerHora("Nueva hora de término (HH:mm): ");

        String nombreEtiqueta =
                leerTexto("Nueva etiqueta: ");

        Etiqueta etiqueta =
                new Etiqueta(nombreEtiqueta, "");

        Actividad nueva;

        if (anterior instanceof Reunion) {

            Reunion reunion =
                    (Reunion) anterior;

            nueva = new Reunion(
                    id,
                    titulo,
                    descripcion,
                    inicio,
                    fin,
                    etiqueta,
                    reunion.getLugar()
            );

        } else {

            nueva = new EventoPersonal(
                    id,
                    titulo,
                    descripcion,
                    inicio,
                    fin,
                    etiqueta
            );
        }

        try {

            dia.modificarActividad(nueva);

            System.out.println(
                    "Actividad modificada correctamente."
            );

        } catch (HorarioInvalidoException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // ==========================================
    // 9. ELIMINAR ACTIVIDAD
    // ==========================================

    private void eliminarActividad() {

        LocalDate fecha = leerFecha(
                "Ingrese la fecha: "
        );

        Dia dia = agenda.buscarDia(fecha);

        if (dia == null) {

            System.out.println(
                    "El día no existe."
            );

            return;
        }

        int id = leerEntero(
                "Ingrese el ID de la actividad: "
        );

        if (dia.buscarActividad(id) == null) {

            System.out.println(
                    "La actividad no existe."
            );

            return;
        }

        dia.eliminarActividad(id);

        System.out.println(
                "Actividad eliminada correctamente."
        );
    }

    // ==========================================
    // 10. BUSCAR ACTIVIDAD
    // ==========================================

    private void buscarActividad() {

        System.out.println();
        System.out.println("Buscar por:");
        System.out.println("1. ID");
        System.out.println("2. Título");

        int opcion = leerEntero(
                "Seleccione: "
        );

        Actividad actividad = null;

        if (opcion == 1) {

            int id = leerEntero(
                    "Ingrese el ID: "
            );

            actividad =
                    agenda.buscarActividadPorId(id);

        } else if (opcion == 2) {

            String titulo =
                    leerTexto("Ingrese el título: ");

            actividad =
                    agenda.buscarActividadPorTitulo(titulo);

        } else {

            System.out.println(
                    "Opción inválida."
            );

            return;
        }

        if (actividad == null) {

            System.out.println(
                    "No se encontró la actividad."
            );

            return;
        }

        System.out.println();
        System.out.println("ACTIVIDAD ENCONTRADA:");

        mostrarActividad(actividad);
    }

    // ==========================================
    // 11. CONSULTA POR PERÍODO Y ETIQUETA
    // ==========================================

    private void consultarAgenda() {

        LocalDate inicio = leerFecha(
                "Fecha inicial: "
        );

        LocalDate fin = leerFecha(
                "Fecha final: "
        );

        String etiquetaBuscada =
                leerTexto("Etiqueta: ");

        List<Actividad> actividades =
                agenda.buscarActividades(
                        inicio,
                        fin
                );

        System.out.println();
        System.out.println(
                "ACTIVIDADES ENCONTRADAS:"
        );

        boolean encontrada = false;

        for (Actividad actividad : actividades) {

            if (actividad.getEtiqueta() != null
                    && actividad.getEtiqueta()
                    .getNombre()
                    .equalsIgnoreCase(
                            etiquetaBuscada.trim()
                    )) {

                mostrarActividad(actividad);
                encontrada = true;
            }
        }

        if (!encontrada) {

            System.out.println(
                    "No se encontraron actividades."
            );
        }
    }

    // ==========================================
    // MOSTRAR ACTIVIDAD
    // ==========================================

    private void mostrarActividad(
            Actividad actividad) {

        System.out.println(
                "------------------------------"
        );

        System.out.println(
                "ID: " + actividad.getId()
        );

        System.out.println(
                "Título: " + actividad.getTitulo()
        );

        System.out.println(
                "Descripción: "
                + actividad.getDescripcion()
        );

        System.out.println(
                "Horario: "
                + actividad.getHoraInicio()
                + " - "
                + actividad.getHoraFin()
        );

        if (actividad.getEtiqueta() != null) {

            System.out.println(
                    "Etiqueta: "
                    + actividad.getEtiqueta().getNombre()
            );
        }

        System.out.println(
                actividad.mostrarDetalle()
        );
    }

    // ==========================================
    // LEER FECHA
    // ==========================================

    private LocalDate leerFecha(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                String texto =
                        scanner.nextLine();

                return LocalDate.parse(
                        texto,
                        formatoFecha
                );

            } catch (Exception e) {

                System.out.println(
                        "Fecha inválida. "
                        + "Use dd/MM/yyyy."
                );
            }
        }
    }

    // ==========================================
    // LEER HORA
    // ==========================================

    private LocalTime leerHora(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                String texto =
                        scanner.nextLine();

                return LocalTime.parse(texto);

            } catch (Exception e) {

                System.out.println(
                        "Hora inválida. Use HH:mm."
                );
            }
        }
    }

    // ==========================================
    // LEER ENTERO
    // ==========================================

    private int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Debe ingresar un número."
                );
            }
        }
    }

    // ==========================================
    // LEER TEXTO
    // ==========================================

    private String leerTexto(String mensaje) {

        System.out.print(mensaje);

        return scanner.nextLine();
    }
}