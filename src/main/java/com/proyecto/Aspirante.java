package com.proyecto;

import java.util.Objects;

public class Aspirante {
    Integer anios_experiencia;
    Integer edad;
    String formacion;
    Integer sector1;
    Integer sector2;
    String nombre;

    public Aspirante() {
    }

    public Aspirante(Integer anios_experiencia, Integer edad, String formacion, Integer sector1, Integer sector2,
            String nombre) {
        this.anios_experiencia = anios_experiencia;
        this.edad = edad;
        this.formacion = formacion;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.nombre = nombre;
    }

    public Integer getAnios_experiencia() {
        return this.anios_experiencia;
    }

    public void setAnios_experiencia(Integer anios_experiencia) {
        this.anios_experiencia = anios_experiencia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getFormacion() {
        return this.formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public Aspirante anios_experiencia(Integer anios_experiencia) {
        setAnios_experiencia(anios_experiencia);
        return this;
    }

    public Aspirante edad(Integer edad) {
        setEdad(edad);
        return this;
    }

    public Aspirante formacion(String formacion) {
        setFormacion(formacion);
        return this;
    }

    public Integer getSector1() {
        return this.sector1;
    }

    public void setSector1(Integer sector1) {
        this.sector1 = sector1;
    }

    public Integer getSector2() {
        return this.sector2;
    }

    public void setSector2(Integer sector2) {
        this.sector2 = sector2;
    }

    @Override
    public String toString() {
        return "{" + " anios_experiencia='" + getAnios_experiencia() + "'" + ", edad='" + getEdad() + "'"
                + ", formacion='" + getFormacion() + "'" + ", sector1='" + getSector1() + "'" + ", sector2='"
                + getSector2() + "'" + ", nombre='" + getNombre() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Aspirante)) {
            return false;
        }
        Aspirante aspirante = (Aspirante) o;
        return Objects.equals(anios_experiencia, aspirante.anios_experiencia) && Objects.equals(edad, aspirante.edad)
                && Objects.equals(formacion, aspirante.formacion) && Objects.equals(sector1, aspirante.sector1)
                && Objects.equals(sector2, aspirante.sector2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anios_experiencia, edad, formacion, sector1, sector2);
    }

}
