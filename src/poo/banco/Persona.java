package poo.banco;

import java.io.Serializable;

public class Persona implements Serializable{
    protected String nombre;
    protected String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;

        Persona persona = (Persona) o;

        return getDni().equals(persona.getDni());
    }

    @Override
    public int hashCode() {
        return getDni().hashCode();
    }

    @Override
    public String toString() {
       return "Nombre: " + this.nombre  + ", DNI: " + this.dni;
    }
}
