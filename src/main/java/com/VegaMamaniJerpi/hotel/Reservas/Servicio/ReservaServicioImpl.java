package com.VegaMamaniJerpi.hotel.Reservas.Servicio;

import com.VegaMamaniJerpi.hotel.Excepciones.FechaInvalidaException;
import com.VegaMamaniJerpi.hotel.Excepciones.HabitacionReservadaException;
import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServicioImpl implements ReservaServicio  {

    @Autowired
    private ReservaRepositorio repositorio;


    @Override
    public List<Reserva> listarReservas() {
        return repositorio.findAll();
    }

    @Override
    public Reserva guardarReserva(Reserva nuevaReserva) throws FechaInvalidaException, HabitacionReservadaException {

        if(!nuevaReserva.getFechaEntrada().isBefore(nuevaReserva.getFechaSalida())){
            throw new FechaInvalidaException("La fecha de entrada debe ser anterior a la fecha de salida");
        }

        List<Reserva> reservasSolapadas= repositorio.buscarReservasSolapadas(nuevaReserva.getHabitacion().getIdHabitacion(), nuevaReserva.getFechaEntrada(), nuevaReserva.getFechaSalida());

        if(!reservasSolapadas.isEmpty()){
            throw new HabitacionReservadaException("La habitación"+ nuevaReserva.getHabitacion().getIdHabitacion() + "ya está reservada en esas fechas.");
        }

        return repositorio.save(nuevaReserva);
    }

    @Override
    public Reserva actualizarReserva(Long id, Reserva reserva) throws IdNoEncontradoException, FechaInvalidaException, HabitacionReservadaException{

        if(!reserva.getFechaEntrada().isBefore(reserva.getFechaSalida())){
            throw new FechaInvalidaException("La fecha de entrada debe ser anterior a la fecha de salida");
        }

        Reserva reservaModificada = repositorio.findById(id).orElseThrow(()-> new IdNoEncontradoException("Id no encontrado"));

        List <Reserva> reservasSolapadas= repositorio.buscarReservasSolapadasActualizar(
                reserva.getHabitacion().getIdHabitacion(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                id
        );

        if(!reservasSolapadas.isEmpty()){
            throw new HabitacionReservadaException("La habitación"+ reserva.getHabitacion().getIdHabitacion() + "ya está reservada en esas fechas.");
        }

        reservaModificada.setFechaEntrada(reserva.getFechaEntrada());
        reservaModificada.setFechaSalida(reserva.getFechaSalida());
        reservaModificada.setHabitacion(reserva.getHabitacion());
        reservaModificada.setHuesped(reserva.getHuesped());

        return repositorio.save(reservaModificada);
    }

    @Override
    public boolean eliminarReserva(Long id) throws IdNoEncontradoException{

        Reserva reserva = repositorio.findById(id).orElseThrow(()-> new IdNoEncontradoException("Id no encontrado"));
        repositorio.delete(reserva);
        return true;
    }
}
