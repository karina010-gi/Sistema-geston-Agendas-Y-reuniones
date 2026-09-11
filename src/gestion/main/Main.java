package gestion.main;

import gestion.clases.Agenda;
import gestion.consola.ConsolaUI;
import gestion.gui.MenuPrincipal;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        // Creamos la agenda
        Agenda agenda = new Agenda("Mi Agenda");

        // Preguntamos qué modo quiere utilizar el usuario
        String opcion = JOptionPane.showInputDialog(
                "Seleccione el modo:\n"
                + "1. Consola\n"
                + "2. Interfaz gráfica"
        );

        if (opcion != null && opcion.equals("1")) {

            // Iniciar consola
            ConsolaUI consola = new ConsolaUI(agenda);
            consola.iniciar();

        } else if (opcion != null && opcion.equals("2")) {

            // Iniciar interfaz gráfica
            SwingUtilities.invokeLater(() -> {
                new MenuPrincipal(agenda).setVisible(true);
            });

        } else {

            System.out.println("Opción inválida.");
        }
    }
}