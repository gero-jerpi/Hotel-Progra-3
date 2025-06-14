package com.VegaMamaniJerpi.hotel.Habitaciones.Servicio;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.HabitacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionServicio {

    @Autowired
    private HabitacionRepositorio repositorio;


    public List<Habitacion> listaHabitaciones(){
        return repositorio.findAll();
    }

    public Habitacion guardarHabitacion(Habitacion nuevaHabitacion){
        return repositorio.save(nuevaHabitacion);
    }


    public Habitacion update(Long id, Habitacion habitacion) {
        Habitacion habitacionModificada = repositorio.findById(id).get();

        habitacionModificada.setTipoHabitacion(habitacion.getTipoHabitacion());
        habitacionModificada.setCantidadPersonas(habitacion.getCantidadPersonas());
        habitacionModificada.setPrecioPorNoche(habitacion.getPrecioPorNoche());

        return repositorio.save(habitacionModificada);
    }

    }
