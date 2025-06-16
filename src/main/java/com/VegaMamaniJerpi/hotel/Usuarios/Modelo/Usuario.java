package com.VegaMamaniJerpi.hotel.Usuarios.Modelo;

import com.VegaMamaniJerpi.hotel.Enums.Rol;
import com.VegaMamaniJerpi.hotel.Enums.TipoHabitacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    /// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasenia;

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;


    /// Constructores
    public Usuario(){

    }
    public Usuario(String nombre, String contrasenia, Rol rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    /// Getters && Setters


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre no puede estar vacío") @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "La contraseña no puede estar vacía") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(@NotBlank(message = "La contraseña no puede estar vacía") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public @NotNull(message = "El rol es obligatorio") Rol getRol() {
        return rol;
    }

    public void setRol(@NotNull(message = "El rol es obligatorio") Rol rol) {
        this.rol = rol;
    }


    /// Metodos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}

