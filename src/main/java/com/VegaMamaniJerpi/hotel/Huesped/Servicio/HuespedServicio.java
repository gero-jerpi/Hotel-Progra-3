package com.VegaMamaniJerpi.hotel.Huesped.Servicio;

import com.VegaMamaniJerpi.hotel.Huesped.Modelo.Huesped;

import java.util.List;

public interface HuespedServicio {
    List<Huesped> listarHuespeds();
    Huesped guardarHuesped(Huesped nuevoHuesped);
    Huesped actualizarHuesped(Long id, Huesped huesped);
    boolean eliminarHuesped(Long id);

}
