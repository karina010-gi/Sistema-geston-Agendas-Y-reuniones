package gestion.gui;

import gestion.clases.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListarActividades extends JFrame {

    private Agenda agenda;

    private JTextField txtFecha;
    private JTextArea area;

    public ListarActividades(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Listar Actividades");
        setSize(550, 450);
        setLocationRelativeTo(null);

        JPanel superior = new JPanel();

        superior.add(
                new JLabel("Fecha (dd/MM/yyyy):")
        );

        txtFecha = new JTextField(10);
        superior.add(txtFecha);

        JButton btnBuscar =
                new JButton("Mostrar");

        superior.add(btnBuscar);

        add(superior, BorderLayout.NORTH);

        area = new JTextArea();
        area.setEditable(false);

        add(
                new JScrollPane(area),
                BorderLayout.CENTER
        );

        btnBuscar.addActionListener(e ->
                listar()
        );
    }

    private void listar() {

        try {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate fecha =
                    LocalDate.parse(
                            txtFecha.getText(),
                            formato
                    );

            Dia dia = agenda.buscarDia(fecha);

            if (dia == null) {

                area.setText("El día no existe.");
                return;
            }

            area.setText("");

            if (dia.getActividades().isEmpty()) {

                area.setText(
                        "No hay actividades en este día."
                );

                return;
            }

            for (Actividad actividad :
                    dia.getActividades()) {

                area.append(
                        "ID: " + actividad.getId() + "\n"
                        + "Título: " + actividad.getTitulo() + "\n"
                        + "Horario: "
                        + actividad.getHoraInicio()
                        + " - "
                        + actividad.getHoraFin()
                        + "\n"
                        + actividad.mostrarDetalle()
                        + "\n"
                        + "-----------------------------\n"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fecha inválida."
            );
        }
    }
}