package com.VegaMamaniJerpi.hotel.Facturas.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Long> {
    Optional<Factura> findByReserva_IdReserva(Long idReserva);///busca si hay una factura de una reserva específica
}
