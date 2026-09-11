package gestion.gui;

import gestion.clases.*;
import gestion.excepciones.HorarioInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ModificarActividad extends JFrame {

    private Agenda agenda;

    private JTextField txtFecha;
    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtDescripcion;
    private JTextField txtInicio;
    private JTextField txtFin;
    private JTextField txtEtiqueta;

    private void limpiarCampos() {

        txtFecha.setText("");
        txtId.setText("");
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtInicio.setText("");
        txtFin.setText("");
        txtEtiqueta.setText("");
    }
    
    public ModificarActividad(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Modificar Actividad");
        setSize(500, 450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(
                new GridLayout(8, 2, 10, 10)
        );

        panel.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        panel.add(txtFecha);

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nuevo título:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel("Nueva descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);

        panel.add(new JLabel("Nueva hora inicio:"));
        txtInicio = new JTextField();
        panel.add(txtInicio);

        panel.add(new JLabel("Nueva hora fin:"));
        txtFin = new JTextField();
        panel.add(txtFin);

        panel.add(new JLabel("Nueva etiqueta:"));
        txtEtiqueta = new JTextField();
        panel.add(txtEtiqueta);

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

            Actividad anterior =
                    dia.buscarActividad(id);

            if (anterior == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "La actividad no existe."
                );

                return;
            }

            Actividad nueva;

            Etiqueta etiqueta =
                    new Etiqueta(
                            txtEtiqueta.getText(),
                            ""
                    );

            if (anterior instanceof Reunion) {

                Reunion reunion =
                        (Reunion) anterior;

                nueva = new Reunion(
                        id,
                        txtTitulo.getText(),
                        txtDescripcion.getText(),
                        LocalTime.parse(
                                txtInicio.getText()
                        ),
                        LocalTime.parse(
                                txtFin.getText()
                        ),
                        etiqueta,
                        reunion.getLugar()
                );

            } else {

                nueva = new EventoPersonal(
                        id,
                        txtTitulo.getText(),
                        txtDescripcion.getText(),
                        LocalTime.parse(
                                txtInicio.getText()
                        ),
                        LocalTime.parse(
                                txtFin.getText()
                        ),
                        etiqueta
                );
            }

            dia.modificarActividad(nueva);

            JOptionPane.showMessageDialog(
                    this,
                    "Actividad modificada correctamente."
            );
            
            limpiarCampos();

        } catch (HorarioInvalidoException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
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