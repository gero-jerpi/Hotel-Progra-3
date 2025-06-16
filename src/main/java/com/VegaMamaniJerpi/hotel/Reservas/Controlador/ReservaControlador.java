package com.VegaMamaniJerpi.hotel.Reservas.Controlador;

import com.VegaMamaniJerpi.hotel.Excepciones.FechaInvalidaException;
import com.VegaMamaniJerpi.hotel.Excepciones.HabitacionReservadaException;
import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;
import com.VegaMamaniJerpi.hotel.Reservas.Servicio.ReservaServicioImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")

public class ReservaControlador {

    @Autowired
    private ReservaServicioImpl servicio;

    @GetMapping
    public List<Reserva> listarReservas(){
        return servicio.listarReservas();
    }

    @PostMapping
    @Transactional
    public Reserva guardarReserva(@RequestBody Reserva nuevaReserva) throws FechaInvalidaException, HabitacionReservadaException {
        return servicio.guardarReserva(nuevaReserva);
    }

    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) throws IdNoEncontradoException, FechaInvalidaException, HabitacionReservadaException{
        return servicio.actualizarReserva(id, reserva);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarReserva(@PathVariable Long id) throws IdNoEncontradoException {
        return servicio.eliminarReserva(id);
    }
}
