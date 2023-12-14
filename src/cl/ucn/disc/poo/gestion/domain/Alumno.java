package cl.ucn.disc.poo.gestion.domain;
/* *
 * class "Alumno", form project "edutrack".
 *
 * Created by p.z in 14/12/2023.
 *
 * (c) p.z 14/12/2023.
 *
 * */

import java.util.Date;

public class Alumno
{
    private String nombre;

    private String apellido;

    private Date fechaNacimiento;

    private Grado grado;

    private Nivel nivel;

    private Seccion seccion;
    private Sexo sexo;

    public Alumno(String nombre, String apellido, Date fechaNacimiento, Grado grado, Nivel nivel, Seccion seccion, Sexo sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.grado = grado;
        this.nivel = nivel;
        this.seccion = seccion;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Grado getGrado() {
        return grado;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public Sexo getSexo() {
        return sexo;
    }
}
