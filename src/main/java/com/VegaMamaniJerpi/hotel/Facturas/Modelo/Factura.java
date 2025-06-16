package com.VegaMamaniJerpi.hotel.Facturas.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "facturas")
public class Factura {

    /// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    //no se puede realizar una factura en el futuro
    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha debe ser hoy o una fecha pasada")
    private LocalDate fecha;


}
