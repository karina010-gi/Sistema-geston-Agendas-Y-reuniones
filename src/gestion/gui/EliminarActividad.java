package gestion.gui;

import gestion.clases.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EliminarActividad extends JFrame {

    private Agenda agenda;

    private JTextField txtFecha;
    private JTextField txtId;
    
    private void limpiarCampos() {

        txtFecha.setText("");
        txtId.setText("");
    }

    public EliminarActividad(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Eliminar Actividad");
        setSize(450, 220);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(
                new GridLayout(3, 2, 10, 10)
        );

        panel.add(
                new JLabel("Fecha (dd/MM/yyyy):")
        );

        txtFecha = new JTextField();
        panel.add(txtFecha);

        panel.add(new JLabel("ID de actividad:"));

        txtId = new JTextField();
        panel.add(txtId);

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
                    LocalDate.parse(
                            txtFecha.getText(),
                            formato
                    );

            int id =
                    Integer.parseInt(txtId.getText());

            Dia dia = agenda.buscarDia(fecha);

            if (dia == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "El día no existe."
                );

                return;
            }

            Actividad actividad =
                    dia.buscarActividad(id);

            if (actividad == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "La actividad no existe."
                );

                return;
            }

            dia.eliminarActividad(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Actividad eliminada correctamente."
            );
            
            limpiarCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Datos inválidos."
            );
        }
    }
}