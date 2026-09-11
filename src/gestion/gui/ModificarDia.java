package gestion.gui;

import gestion.clases.Agenda;
import gestion.excepciones.FechaDuplicadaException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ModificarDia extends JFrame {

    private Agenda agenda;

    private JTextField txtActual;
    private JTextField txtNueva;

    public ModificarDia(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Modificar Día");
        setSize(450, 220);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(
                new GridLayout(3, 2, 10, 10)
        );

        panel.add(new JLabel("Fecha actual (dd/MM/yyyy):"));
        txtActual = new JTextField();
        panel.add(txtActual);

        panel.add(new JLabel("Nueva fecha (dd/MM/yyyy):"));
        txtNueva = new JTextField();
        panel.add(txtNueva);

        JButton btnModificar =
                new JButton("Modificar");

        JButton btnCerrar =
                new JButton("Cerrar");

        panel.add(btnModificar);
        panel.add(btnCerrar);

        add(panel);

        btnModificar.addActionListener(e ->
                modificar()
        );

        btnCerrar.addActionListener(e ->
                dispose()
        );
    }

    private void modificar() {

        try {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate actual =
                    LocalDate.parse(txtActual.getText(), formato);

            LocalDate nueva =
                    LocalDate.parse(txtNueva.getText(), formato);

            agenda.modificarDia(actual, nueva);

            JOptionPane.showMessageDialog(
                    this,
                    "Día modificado correctamente."
            );

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
                    "Fecha inválida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}