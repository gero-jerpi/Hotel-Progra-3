package com.VegaMamaniJerpi.hotel.Facturas.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaRepositorio extends JpaRepository<Factura, Long> {
    Optional<Factura> findByReservaId(Long idReserva);///busca si hay una factura de una reserva específica
}
