package gestion.gui;

import gestion.clases.Agenda;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EliminarDia extends JFrame {

    private Agenda agenda;
    private JTextField txtFecha;

    public EliminarDia(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Eliminar Día");
        setSize(400, 180);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        panel.add(new JLabel("Fecha (dd/MM/yyyy):"));

        txtFecha = new JTextField();
        panel.add(txtFecha);

        JButton btnEliminar =
                new JButton("Eliminar");

        JButton btnCerrar =
                new JButton("Cerrar");

        panel.add(btnEliminar);
        panel.add(btnCerrar);

        add(panel);

        btnEliminar.addActionListener(e ->
                eliminar()
        );

        btnCerrar.addActionListener(e ->
                dispose()
        );
    }

    private void eliminar() {

        try {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate fecha =
                    LocalDate.parse(txtFecha.getText(), formato);

            if (agenda.buscarDia(fecha) == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "El día no existe."
                );

                return;
            }

            agenda.eliminarDia(fecha);

            JOptionPane.showMessageDialog(
                    this,
                    "Día eliminado correctamente."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fecha inválida."
            );
        }
    }
}