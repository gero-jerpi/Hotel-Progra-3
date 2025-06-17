package com.VegaMamaniJerpi.hotel.Reservas.Servicio;

import com.VegaMamaniJerpi.hotel.Excepciones.FechaInvalidaException;
import com.VegaMamaniJerpi.hotel.Excepciones.HabitacionReservadaException;
import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;

import java.util.List;

public interface ReservaServicio {
    List<Reserva> listarReservas();
    Reserva guardarReserva (Reserva nuevaReserva)throws FechaInvalidaException, HabitacionReservadaException;
    Reserva actualizarReserva(Long id, Reserva reserva)throws IdNoEncontradoException, FechaInvalidaException, HabitacionReservadaException;
    boolean eliminarReserva(Long id)throws IdNoEncontradoException;
}
