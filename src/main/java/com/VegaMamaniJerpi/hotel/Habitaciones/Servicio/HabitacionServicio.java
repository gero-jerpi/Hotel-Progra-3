package com.VegaMamaniJerpi.hotel.Habitaciones.Servicio;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionServicio {
    List<Habitacion> listaHabitaciones();
    Habitacion guardarHabitacion(Habitacion nuevaHabitacion);
    Habitacion update(Long id, Habitacion habitacion);
    boolean eliminar(Long id);
}
