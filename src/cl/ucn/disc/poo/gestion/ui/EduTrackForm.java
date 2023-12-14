package cl.ucn.disc.poo.gestion.ui;

import cl.ucn.disc.poo.gestion.domain.*;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

/**
 * The Gestion GUI.
 *
 * @author Programacion Avanzada.
 */
public final class EduTrackForm extends JFrame {

    private JPanel panelPrincipal;
    private JTextField textNombres;
    private JTextField textApellidos;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femeninoRadioButton;
    private JComboBox<Nivel> comboBoxNivel;
    private JComboBox<Grado> comboBoxGrado;
    private JComboBox<Seccion> comboBoxSeccion;
    private JButton buttonNuevo;
    private JButton buttonGuardar;
    private JFormattedTextField textFechaNacimiento;
    private JButton buttonAlumnosT;

    private ButtonGroup buttonGroupSexo;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private EduTrackModel eduTrackModel;

    /**
     * The Constructor.
     */
    public EduTrackForm()
    {
        super("Gestion de Alumnos v1.0");

        eduTrackModel = new EduTrackModel();

        this.setContentPane(this.panelPrincipal);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // the group of radio buttons
        this.buttonGroupSexo = new ButtonGroup();
        this.buttonGroupSexo.add(masculinoRadioButton);
        this.buttonGroupSexo.add(femeninoRadioButton);

        // the combobox
        this.comboBoxNivel.setModel(new DefaultComboBoxModel<>(Nivel.values()));
        this.comboBoxGrado.setModel(new DefaultComboBoxModel<>(Grado.values()));
        this.comboBoxSeccion.setModel(new DefaultComboBoxModel<>(Seccion.values()));


        // clear button
        this.buttonNuevo.addActionListener(e -> this.clearForm());
        this.clearForm();

        // save button
        this.buttonGuardar.addActionListener(e -> this.saveForm());

        this.buttonAlumnosT.addActionListener(e ->
        {
            TablaDeAlumnos tA = new TablaDeAlumnos(eduTrackModel);
            tA.pack();
            tA.setLocationRelativeTo(null);
            tA.setVisible(true);
        });

        this.setVisible(true);
    }

    /**
     * Limpiar el formulario.
     */
    private void clearForm() {
        this.textNombres.setText("");
        this.textApellidos.setText("");
        this.textFechaNacimiento.setValue(LocalDate.now(ZoneId.systemDefault()));
        buttonGroupSexo.clearSelection();
        this.comboBoxNivel.setSelectedIndex(-1);
        this.comboBoxGrado.setSelectedIndex(-1);
        this.comboBoxSeccion.setSelectedIndex(-1);
    }

    /**
     * Crear un nuevo Alumno.
     */
    private void saveForm() {

        // el nombre
        String nombres = this.textNombres.getText()
                                         .strip();
        if (nombres.length() < 2) {
            JOptionPane.showMessageDialog(this, "El nombre debe tener al menos 2 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // el apellido
        String apellidos = this.textApellidos.getText()
                                             .strip();
        if (apellidos.length() < 2) {
            JOptionPane.showMessageDialog(this, "El apellido debe tener al menos 2 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // fecha de nacimiento
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = sdf.parse(this.textFechaNacimiento.getText());
            System.out.println("Fecha: " + fechaNacimiento);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la fecha de nacimiento", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // the sexo
        Sexo sexo = null;
        for (AbstractButton radioButton : Collections.list(buttonGroupSexo.getElements())) {
            if (radioButton.isSelected()) {
                sexo = Sexo.valueOf(radioButton.getText()
                                               .toUpperCase());
            }
        }
        System.out.println("Sexo: " + sexo);

        // no se ha seleccionado el sexo
        if (sexo == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el sexo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // el nivel
        Nivel nivel = (Nivel) this.comboBoxNivel.getSelectedItem();
        if (nivel == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el nivel", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // el grado
        Grado grado = (Grado) this.comboBoxGrado.getSelectedItem();
        if (grado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el grado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // la seccion
        Seccion seccion = (Seccion) this.comboBoxSeccion.getSelectedItem();
        if (seccion == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar la seccion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: Agregar el alumno al backend
        eduTrackModel.add(new Alumno(nombres, apellidos, fechaNacimiento, grado, nivel, seccion, sexo));
    }

    /**
     * the main.
     */
    public static void main(String[] args) {

        // look & feel: https://www.formdev.com/flatlaf/
        FlatLightLaf.setup();

        // inicio de los elementos graficos
        SwingUtilities.invokeLater(() -> {
            EduTrackForm gestionForm = new EduTrackForm();
            gestionForm.pack();
            gestionForm.setLocationRelativeTo(null);
            gestionForm.setVisible(true);
        });
    }

}
