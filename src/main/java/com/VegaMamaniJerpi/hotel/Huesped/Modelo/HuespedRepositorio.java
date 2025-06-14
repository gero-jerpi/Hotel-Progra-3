package com.VegaMamaniJerpi.hotel.Huesped.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuespedRepositorio extends JpaRepository<Huesped, Long> {
}
