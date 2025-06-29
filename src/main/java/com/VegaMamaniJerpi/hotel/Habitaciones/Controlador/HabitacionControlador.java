package com.VegaMamaniJerpi.hotel.Habitaciones.Controlador;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Habitaciones.Servicio.HabitacionServicioImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")

public class HabitacionControlador {

    @Autowired
    private HabitacionServicioImpl servicio;

    @GetMapping
    public List<Habitacion> listarHabitaciones() {
        return servicio.listarHabitaciones();
    }

    @PostMapping
    @Transactional
    public Habitacion guardarHabitacion(@RequestBody Habitacion nuevaHabitacion) {
        return servicio.guardarHabitacion(nuevaHabitacion);
    }

    @PutMapping("/{id}")
    public Habitacion actualizarHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return servicio.actualizarHabitacion(id, habitacion);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarHabitacion(@PathVariable Long id){
        return servicio.eliminarHabitacion(id);
    }

    /// TEXTO PARA MODIFICAR
    /*{
            "tipoHabitacion": "SIMPLE",
            "cantidadPersonas": 3,
            "precioPorNoche": 2000.00
    }
    */




}
