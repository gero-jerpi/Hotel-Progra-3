package com.VegaMamaniJerpi.hotel.Habitaciones.Controlador;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Habitaciones.Servicio.HabitacionServicioLmpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")

public class HabitacionControlador {

    @Autowired
    private HabitacionServicioLmpl servicio;

    @GetMapping
    public List<Habitacion> listaHabitaciones() {
        return servicio.listaHabitaciones();
    }

    @PostMapping
    @Transactional
    public Habitacion guardarHabitacion(@RequestBody Habitacion nuevaHabitacion) {
        return servicio.guardarHabitacion(nuevaHabitacion);
    }

    @PatchMapping("/{id}")
    public Habitacion update(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return servicio.update(id, habitacion);
    }

    @PatchMapping("/{id}")
    public boolean eliminar(@PathVariable Long id){
        return servicio.eliminar(id);
    }

    /// TEXTO PARA MODIFICAR
    /*{
        "tipoHabitacion": "SIMPLE",
            "cantidadPersonas": 3,
            "precioPorNoche": 2000.00
    }
    */




}
