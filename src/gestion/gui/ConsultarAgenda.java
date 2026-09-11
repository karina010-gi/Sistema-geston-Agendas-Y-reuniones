package gestion.gui;

import gestion.clases.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsultarAgenda extends JFrame {

    private Agenda agenda;

    private JTextField txtInicio;
    private JTextField txtFin;
    private JTextField txtEtiqueta;

    private JTextArea area;

    public ConsultarAgenda(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Consultar Agenda");
        setSize(600, 450);
        setLocationRelativeTo(null);

        JPanel superior = new JPanel(
                new GridLayout(2, 4, 10, 10)
        );

        superior.add(
                new JLabel("Fecha inicio:")
        );

        txtInicio = new JTextField();
        superior.add(txtInicio);

        superior.add(
                new JLabel("Fecha fin:")
        );

        txtFin = new JTextField();
        superior.add(txtFin);

        superior.add(
                new JLabel("Etiqueta:")
        );

        txtEtiqueta = new JTextField();
        superior.add(txtEtiqueta);

        JButton btnConsultar =
                new JButton("Consultar");

        superior.add(btnConsultar);

        add(superior, BorderLayout.NORTH);

        area = new JTextArea();
        area.setEditable(false);

        add(
                new JScrollPane(area),
                BorderLayout.CENTER
        );

        btnConsultar.addActionListener(e ->
                consultar()
        );
    }

    private void consultar() {

        try {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate inicio =
                    LocalDate.parse(
                            txtInicio.getText(),
                            formato
                    );

            LocalDate fin =
                    LocalDate.parse(
                            txtFin.getText(),
                            formato
                    );

            String etiqueta =
                    txtEtiqueta.getText();

            List<Actividad> actividades =
                    agenda.buscarActividades(
                            inicio,
                            fin
                    );

            area.setText("");

            for (Actividad actividad : actividades) {

                if (actividad.getEtiqueta() != null
                        && actividad.getEtiqueta()
                        .getNombre()
                        .equalsIgnoreCase(etiqueta)) {

                    area.append(
                            actividad.getId()
                            + " - "
                            + actividad.getTitulo()
                            + " - "
                            + actividad.getHoraInicio()
                            + "\n"
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Revisa las fechas ingresadas."
            );
        }
    }
}