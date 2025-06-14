package com.VegaMamaniJerpi.hotel.Huesped.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table (name = "huespeds")
public class Huesped {

    /// Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHuesped;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 9 digitos")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private Date fechaNacimiento;


    /// Constructores

    public Huesped(){

    }

    public Huesped(String dni, String nombre, String apellido, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }


    /// Getters && Setters


    public Long getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(Long idHuesped) {
        this.idHuesped = idHuesped;
    }

    public @NotBlank(message = "El DNI no puede estar vacío") @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 9 digitos") String getDni() {
        return dni;
    }

    public void setDni(@NotBlank(message = "El DNI no puede estar vacío") @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 9 digitos") String dni) {
        this.dni = dni;
    }

    public @NotBlank(message = "El nombre es obligatorio") @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El apellido es obligatorio") @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(message = "El apellido es obligatorio") @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres") String apellido) {
        this.apellido = apellido;
    }

    public @NotNull(message = "La fecha de nacimiento es obligatoria") @Past(message = "La fecha de nacimiento debe ser en el pasado") Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NotNull(message = "La fecha de nacimiento es obligatoria") @Past(message = "La fecha de nacimiento debe ser en el pasado") Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /// Metodos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Huesped huesped)) return false;
        return Objects.equals(idHuesped, huesped.idHuesped);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idHuesped);
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "idHuesped=" + idHuesped +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
