package cl.ucn.disc.poo.gestion.ui;

import javax.swing.*;
import java.awt.event.*;

public class TablaDeAlumnos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTable table1;

    public TablaDeAlumnos(final EduTrackModel eduTrackModel) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        this.table1.setModel(eduTrackModel);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

}
