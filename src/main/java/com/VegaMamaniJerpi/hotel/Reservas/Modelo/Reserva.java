package com.VegaMamaniJerpi.hotel.Reservas.Modelo;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Huesped.Modelo.Huesped;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table (name = "reservas")
public class Reserva {

    /// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne (optional = false, fetch = FetchType.EAGER) ///la reserva siempre tine que tener una habitacion
    @JoinColumn (name = "idHuesped", referencedColumnName = "idHuesped")
    private Huesped huesped;

    @ManyToOne (optional = false, fetch = FetchType.EAGER) ///la reserva siempre tine que tener una habitacion
    @JoinColumn (name = "idHabitacion", referencedColumnName = "idHabitacion")
    private Habitacion habitacion;

    @NotNull(message = "La fecha de entrada es obligatoria")
    @FutureOrPresent(message = "La fecha de entrada debe ser hoy o una fecha futura")
    private LocalDate fechaEntrada;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Future(message = "La fecha de salida debe ser en el futuro")
    private LocalDate fechaSalida;


    /// Constructores

    public Reserva() {

    }

    public Reserva(Huesped huesped, Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    /// Getters && Setters


    public Long getIdReserva() {
        return idReserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada( LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida( LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /// Metodos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(idReserva, reserva.idReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idReserva);
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", huesped=" + huesped +
                ", habitacion=" + habitacion +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                '}';
    }

    public long getCantidadDeDias() {
        if (fechaEntrada != null && fechaSalida != null) {
            return ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        }
        return 0;
    }


}
