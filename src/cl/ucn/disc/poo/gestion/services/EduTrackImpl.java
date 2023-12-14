package cl.ucn.disc.poo.gestion.services;
/* *
 * class "EduTrackImpl", form project "edutrack".
 *
 * Created by p.z in 14/12/2023.
 *
 * (c) p.z 14/12/2023.
 *
 * */

import cl.ucn.disc.poo.gestion.domain.Alumno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EduTrackImpl implements EduTrack
{

    private static EduTrack instancia;

    private static final String FILENAME = "alumnos.json";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final List<Alumno> alumnos;


    private EduTrackImpl()
    {
        alumnos = new ArrayList<>();

        try {
            this.load();
        } catch (RuntimeException e)
        {
            this.save();
        }
    }

    public static EduTrack getInstancia()
    {
        if (instancia == null)
        {
            instancia = new EduTrackImpl();
        }

        return instancia;
    }


    @Override
    public void load()
    {
        this.alumnos.clear();

        try
        {
            Alumno[] arrayAlumnos = GSON.fromJson(new FileReader(FILENAME), Alumno[].class);

            this.alumnos.addAll(Arrays.asList(arrayAlumnos));
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException("Error de lectura", e);
        }
    }

    @Override
    public void save()
    {
        try (FileWriter w = new FileWriter(FILENAME))
        {
            GSON.toJson(this.alumnos, w);
        } catch (IOException e) {
            throw new RuntimeException("Error de escritura: ", e);
        }
    }

    @Override
    public void addAlumno(final Alumno a)
    {
        if (a != null)
        {
            alumnos.add(a);
            this.sort();
            this.save();
        }
    }

    @Override
    public void sort()
    {
        this.alumnos.sort(Comparator.comparing(Alumno::getApellido));
    }

    @Override
    public int size() {
        return this.alumnos.size();
    }

    @Override
    public Alumno getAlumno(int posicion)
    {
        return alumnos.get(posicion);
    }
}
