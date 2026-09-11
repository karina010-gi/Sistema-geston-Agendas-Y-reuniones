package gestion.gui;

import gestion.clases.*;

import javax.swing.*;
import java.awt.*;

public class BuscarActividad extends JFrame {

    private Agenda agenda;

    private JTextField txtBusqueda;
    private JTextArea area;

    private JComboBox<String> comboTipo;

    public BuscarActividad(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Buscar Actividad");
        setSize(500, 350);
        setLocationRelativeTo(null);

        JPanel superior = new JPanel();

        comboTipo = new JComboBox<>(
                new String[]{"ID", "Título"}
        );

        txtBusqueda = new JTextField(15);

        JButton btnBuscar =
                new JButton("Buscar");

        superior.add(comboTipo);
        superior.add(txtBusqueda);
        superior.add(btnBuscar);

        add(superior, BorderLayout.NORTH);

        area = new JTextArea();
        area.setEditable(false);

        add(
                new JScrollPane(area),
                BorderLayout.CENTER
        );

        btnBuscar.addActionListener(e ->
                buscar()
        );
    }

    private void buscar() {

        String texto = txtBusqueda.getText();

        Actividad actividad = null;

        if (comboTipo.getSelectedItem()
                .equals("ID")) {

            try {

                int id = Integer.parseInt(texto);

                actividad =
                        agenda.buscarActividadPorId(id);

            } catch (NumberFormatException e) {

                area.setText("El ID debe ser un número.");
                return;
            }

        } else {

            actividad =
                    agenda.buscarActividadPorTitulo(texto);
        }

        if (actividad == null) {

            area.setText(
                    "No se encontró la actividad."
            );

            return;
        }

        area.setText(
                "ID: " + actividad.getId()
                + "\nTítulo: " + actividad.getTitulo()
                + "\nDescripción: "
                + actividad.getDescripcion()
                + "\nHorario: "
                + actividad.getHoraInicio()
                + " - "
                + actividad.getHoraFin()
                + "\n"
                + actividad.mostrarDetalle()
        );
    }
}