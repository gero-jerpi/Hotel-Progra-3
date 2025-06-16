package com.VegaMamaniJerpi.hotel.Habitaciones.Servicio;

import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.HabitacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServicioImpl implements HabitacionServicio{

    @Autowired
    private HabitacionRepositorio repositorio;

    @Override
    public List<Habitacion> listarHabitaciones() {
        return repositorio.findAll();
    }

    @Override
    public Habitacion guardarHabitacion(Habitacion nuevaHabitacion) {
        return repositorio.save(nuevaHabitacion);
    }

    @Override
    public Habitacion actualizarHabitacion(Long id, Habitacion habitacion) {
        Habitacion habitacionModificada = repositorio.findById(id).get();

        habitacionModificada.setTipoHabitacion(habitacion.getTipoHabitacion());
        habitacionModificada.setCantidadPersonas(habitacion.getCantidadPersonas());
        habitacionModificada.setPrecioPorNoche(habitacion.getPrecioPorNoche());

        return repositorio.save(habitacionModificada);
    }

    @Override
    public boolean eliminarHabitacion(Long id) {
        boolean flag = false;

        Optional<Habitacion> habitacionOptional = repositorio.findById(id);

        if(habitacionOptional.isPresent()){
            repositorio.delete(habitacionOptional.get());
            flag = true;
        }

        return flag;
    }
}
