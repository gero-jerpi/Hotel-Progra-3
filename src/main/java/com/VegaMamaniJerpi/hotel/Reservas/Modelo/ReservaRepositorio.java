package com.VegaMamaniJerpi.hotel.Reservas.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    // Para crear una reserva
    @Query("SELECT r FROM Reserva r WHERE r.habitacion.idHabitacion = :p_idHabitacion " +
            "AND r.fechaEntrada < :p_fechaSalida AND r.fechaSalida > :p_fechaEntrada")
    List<Reserva> buscarReservasSolapadas(@Param("p_idHabitacion") Long p_idHabitacion,
                                          @Param("p_fechaEntrada") LocalDate p_fechaEntrada,
                                          @Param("p_fechaSalida") LocalDate p_fechaSalida);


    // Para actualizar fechas de una reserva
    @Query("SELECT r FROM Reserva r WHERE r.habitacion.idHabitacion = :p_idHabitacion " +
            "AND r.fechaEntrada < :p_fechaSalida AND r.fechaSalida > :p_fechaEntrada " +
            "AND r.idReserva != :p_idReserva")
    List<Reserva> buscarReservasSolapadasActualizar(@Param("p_idHabitacion") Long p_idHabitacion,
                                                    @Param("p_fechaEntrada") LocalDate p_fechaEntrada,
                                                    @Param("p_fechaSalida") LocalDate p_fechaSalida,
                                                    @Param("p_idReserva") Long p_idReserva);


}
