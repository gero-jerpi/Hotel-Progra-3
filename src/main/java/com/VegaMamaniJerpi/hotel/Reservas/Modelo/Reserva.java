package com.VegaMamaniJerpi.hotel.Reservas.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table (name = "reservas")
public class Reserva {


    /// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 9 digitos")
    private String dniHuesped;

    @NotNull(message = "Debe indicar la habitación")
    private Long idHabitacion;

    @NotNull(message = "La fecha de entrada es obligatoria")
    @Future(message = "La fecha de entrada debe ser en el futuro")
    private Date fechaEntrada;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Future(message = "La fecha de salida debe ser en el futuro")
    private Date fechaSalida;



}
