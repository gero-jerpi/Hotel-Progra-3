package com.VegaMamaniJerpi.hotel.Reservas.Servicio;

import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;

import java.util.List;

public interface ReservaServicio {
    List<Reserva> listarReservas();
    Reserva guardarReserva (Reserva nuevaReserva);
    Reserva actualizarReserva(Long id, Reserva reserva);
    boolean eliminarReserva(Long id);
}
