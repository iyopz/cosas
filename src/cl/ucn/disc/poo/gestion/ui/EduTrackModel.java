package cl.ucn.disc.poo.gestion.ui;
/* *
 * class "EduTrackModel", form project "edutrack".
 *
 * Created by p.z in 14/12/2023.
 *
 * (c) p.z 14/12/2023.
 *
 * */

import cl.ucn.disc.poo.gestion.domain.Alumno;
import cl.ucn.disc.poo.gestion.services.EduTrack;
import cl.ucn.disc.poo.gestion.services.EduTrackImpl;

import javax.swing.table.AbstractTableModel;

public class EduTrackModel extends AbstractTableModel
{

    private final EduTrack eduTrack = EduTrackImpl.getInstancia();

    public EduTrackModel()
    {

    }

    @Override
    public String getColumnName(int column) {
        switch (column)
        {
            case 0:
                return "Nombre";
            case 1:
                return "Apellido";
            case 2:
                return "Fecha de Nacimiento";
            case 3:
                return "Sexo";
            case 4:
                return "Grado";
            case 5:
                return "Nivel";
            case 6:
                return "Seccion";

        }
        throw new IllegalArgumentException("Wea no valida");
    }

    @Override
    public int getRowCount()
    {
        return eduTrack.size();
    }

    @Override
    public int getColumnCount()
    {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Alumno a = eduTrack.getAlumno(rowIndex);

        switch (columnIndex)
        {
            case 0 ->
            {
                return a.getNombre();
            }

            case 1 ->
            {
            return a.getApellido();
            }

            case 2 ->
            {
            return a.getFechaNacimiento();
            }

            case 3 ->
            {
            return a.getSexo();
            }

            case 4 ->
            {
            return a.getGrado();
            }

            case 5 ->
            {
            return a.getNivel();
            }

            case 6 ->
            {
            return a.getSeccion();
            }
        }

        return null;
    }

    public void add(final Alumno a)
    {
        eduTrack.addAlumno(a);

        this.fireTableDataChanged();
    }
}
