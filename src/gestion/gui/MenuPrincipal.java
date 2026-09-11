package gestion.gui;

import gestion.clases.Agenda;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private Agenda agenda;

    public MenuPrincipal(Agenda agenda) {

        this.agenda = agenda;

        setTitle("Sistema de Gestión de Agenda");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel titulo = new JLabel(
                "SISTEMA DE GESTIÓN DE AGENDA",
                SwingConstants.CENTER
        );

        panel.add(titulo);

        JButton btnAgregarDia = new JButton("Agregar día");
        JButton btnListarDias = new JButton("Listar días");
        JButton btnModificarDia = new JButton("Modificar día");
        JButton btnEliminarDia = new JButton("Eliminar día");

        JButton btnAgregarActividad =
                new JButton("Agregar actividad");

        JButton btnListarActividades =
                new JButton("Listar actividades");

        JButton btnModificarActividad =
                new JButton("Modificar actividad");

        JButton btnEliminarActividad =
                new JButton("Eliminar actividad");

        JButton btnBuscarActividad =
                new JButton("Buscar actividad");

        JButton btnConsultar =
                new JButton("Consultar por período y etiqueta");

        JButton btnSalir = new JButton("Salir");

        panel.add(btnAgregarDia);
        panel.add(btnListarDias);
        panel.add(btnModificarDia);
        panel.add(btnEliminarDia);

        panel.add(btnAgregarActividad);
        panel.add(btnListarActividades);
        panel.add(btnModificarActividad);
        panel.add(btnEliminarActividad);
        panel.add(btnBuscarActividad);

        panel.add(btnConsultar);
        panel.add(btnSalir);

        add(panel);

        // BOTONES

        btnAgregarDia.addActionListener(e ->
                new AgregarDia(agenda).setVisible(true)
        );

        btnListarDias.addActionListener(e ->
                new ListarDias(agenda).setVisible(true)
        );

        btnModificarDia.addActionListener(e ->
                new ModificarDia(agenda).setVisible(true)
        );

        btnEliminarDia.addActionListener(e ->
                new EliminarDia(agenda).setVisible(true)
        );

        btnAgregarActividad.addActionListener(e ->
                new AgregarActividad(agenda).setVisible(true)
        );

        btnListarActividades.addActionListener(e ->
                new ListarActividades(agenda).setVisible(true)
        );

        btnModificarActividad.addActionListener(e ->
                new ModificarActividad(agenda).setVisible(true)
        );

        btnEliminarActividad.addActionListener(e ->
                new EliminarActividad(agenda).setVisible(true)
        );

        btnBuscarActividad.addActionListener(e ->
                new BuscarActividad(agenda).setVisible(true)
        );

        btnConsultar.addActionListener(e ->
                new ConsultarAgenda(agenda).setVisible(true)
        );

        btnSalir.addActionListener(e ->
                System.exit(0)
        );
    }
}