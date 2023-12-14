package cl.ucn.disc.poo.gestion.services;

import cl.ucn.disc.poo.gestion.domain.Alumno;

import java.util.List;

/* *
 * interface "EduTrack", form project "edutrack".
 *
 * Created by p.z in 14/12/2023.
 *
 * (c) p.z 14/12/2023.
 *
 * */
public interface EduTrack
{

    void load();
    void save();
    void addAlumno(final Alumno a);

    void sort();

    int size();

    Alumno getAlumno(int posicion);
}
