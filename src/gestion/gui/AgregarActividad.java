package gestion.gui;

import gestion.clases.*;
import gestion.excepciones.HorarioInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AgregarActividad extends JFrame {

    private Agenda agenda;

    private JTextField txtFecha;
    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtDescripcion;
    private JTextField txtInicio;
    private JTextField txtFin;
    private JTextField txtEtiqueta;
    private JTextField txtLugar;

    private JComboBox<String> comboTipo;

    public AgregarActividad(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Agregar Actividad");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(
                new GridLayout(9, 2, 10, 10)
        );

        panel.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFecha = new JTextField();
        panel.add(txtFecha);

        panel.add(new JLabel("Tipo:"));

        comboTipo = new JComboBox<>(
                new String[]{"Evento Personal", "Reunión"}
        );

        panel.add(comboTipo);

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);

        panel.add(new JLabel("Hora inicio (HH:mm):"));
        txtInicio = new JTextField();
        panel.add(txtInicio);

        panel.add(new JLabel("Hora fin (HH:mm):"));
        txtFin = new JTextField();
        panel.add(txtFin);

        panel.add(new JLabel("Etiqueta:"));
        txtEtiqueta = new JTextField();
        panel.add(txtEtiqueta);

        panel.add(new JLabel("Lugar (solo reunión):"));
        txtLugar = new JTextField();
        panel.add(txtLugar);

        JButton btnAgregar =
                new JButton("Agregar");

        JButton btnCerrar =
                new JButton("Cerrar");

        panel.add(btnAgregar);
        panel.add(btnCerrar);

        add(panel);

        btnAgregar.addActionListener(e ->
                agregarActividad()
        );

        btnCerrar.addActionListener(e ->
                dispose()
        );
    }

    private void limpiarCampos() {

        txtFecha.setText("");
        txtId.setText("");
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtInicio.setText("");
        txtFin.setText("");
        txtEtiqueta.setText("");
        txtLugar.setText("");

        comboTipo.setSelectedIndex(0);
    }    

    private void agregarActividad() {

        try {

            DateTimeFormatter formatoFecha =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate fecha =
                    LocalDate.parse(
                            txtFecha.getText(),
                            formatoFecha
                    );

            Dia dia = agenda.buscarDia(fecha);

            if (dia == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Primero debes crear ese día."
                );

                return;
            }

            int id = Integer.parseInt(txtId.getText());

            String titulo = txtTitulo.getText();
            String descripcion = txtDescripcion.getText();

            LocalTime inicio =
                    LocalTime.parse(txtInicio.getText());

            LocalTime fin =
                    LocalTime.parse(txtFin.getText());

            Etiqueta etiqueta =
                    new Etiqueta(
                            txtEtiqueta.getText(),
                            ""
                    );

            Actividad actividad;

            if (comboTipo.getSelectedItem()
                    .equals("Reunión")) {

                actividad = new Reunion(
                        id,
                        titulo,
                        descripcion,
                        inicio,
                        fin,
                        etiqueta,
                        txtLugar.getText()
                );

            } else {

                actividad = new EventoPersonal(
                        id,
                        titulo,
                        descripcion,
                        inicio,
                        fin,
                        etiqueta
                );
            }

            dia.agregarActividad(actividad);

            JOptionPane.showMessageDialog(
                    this,
                    "Actividad agregada correctamente."
            );
            
            limpiarCampos();

        } catch (HorarioInvalidoException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Horario inválido",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Revisa los datos ingresados.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}