package com.VegaMamaniJerpi.hotel.Habitaciones.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepositorio extends JpaRepository<Habitacion, Long> {
}
