package com.VegaMamaniJerpi.hotel.Habitaciones.Servicio;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionServicio {
    List<Habitacion> listarHabitaciones();
    Habitacion guardarHabitacion(Habitacion nuevaHabitacion);
    Habitacion actualizarHabitacion(Long id, Habitacion habitacion);
    boolean eliminarHabitacion(Long id);
}
