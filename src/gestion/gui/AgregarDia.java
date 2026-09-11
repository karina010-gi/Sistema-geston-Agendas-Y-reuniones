package gestion.gui;

import gestion.clases.Agenda;
import gestion.excepciones.FechaDuplicadaException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgregarDia extends JFrame {

    private Agenda agenda;
    private JTextField txtFecha;

    public AgregarDia(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Agregar Día");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Fecha (dd/MM/yyyy):"));

        txtFecha = new JTextField();
        panel.add(txtFecha);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnCerrar = new JButton("Cerrar");

        panel.add(btnAgregar);
        panel.add(btnCerrar);

        add(panel);

        btnAgregar.addActionListener(e -> agregar());

        btnCerrar.addActionListener(e ->
                dispose()
        );
    }

    private void agregar() {

        try {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate fecha =
                    LocalDate.parse(txtFecha.getText(), formato);

            agenda.agregarDia(fecha);

            JOptionPane.showMessageDialog(
                    this,
                    "Día agregado correctamente."
            );

            txtFecha.setText("");

        } catch (FechaDuplicadaException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Formato de fecha inválido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}