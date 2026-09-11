package gestion.gui;

import gestion.clases.Agenda;
import gestion.clases.Dia;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ListarDias extends JFrame {

    private Agenda agenda;
    private JTextArea area;

    public ListarDias(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Días registrados");
        setSize(400, 400);
        setLocationRelativeTo(null);

        area = new JTextArea();
        area.setEditable(false);

        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnActualizar =
                new JButton("Actualizar");

        add(btnActualizar, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e ->
                mostrarDias()
        );

        mostrarDias();
    }

    private void mostrarDias() {

        area.setText("");

        if (agenda.getDias().isEmpty()) {

            area.setText("No hay días registrados.");
            return;
        }

        for (LocalDate fecha : agenda.getDias().keySet()) {

            Dia dia = agenda.buscarDia(fecha);

            area.append(
                    "Fecha: " + dia.getFecha() + "\n"
            );
        }
    }
}