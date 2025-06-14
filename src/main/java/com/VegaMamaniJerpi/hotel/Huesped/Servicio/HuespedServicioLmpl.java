package com.VegaMamaniJerpi.hotel.Huesped.Servicio;

import com.VegaMamaniJerpi.hotel.Huesped.Modelo.Huesped;
import com.VegaMamaniJerpi.hotel.Huesped.Modelo.HuespedRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuespedServicioLmpl implements HuespedServicio {

    @Autowired
    private HuespedRepositorio repositorio;


    @Override
    public List<Huesped> listarHuespeds() {
        return repositorio.findAll();
    }

    @Override
    public Huesped guardarHuesped(Huesped nuevoHuesped) {
        return repositorio.save(nuevoHuesped);
    }

    @Override
    public Huesped actualizarHuesped(Long id, Huesped huesped) {
        Huesped huespedModificado = repositorio.findById(id).get();

        huespedModificado.setNombre(huesped.getNombre());
        huespedModificado.setApellido(huesped.getApellido());
        huespedModificado.setDni(huesped.getDni());
        huespedModificado.setFechaNacimiento(huesped.getFechaNacimiento());

        return huespedModificado;
    }

    @Override
    public boolean eliminarHuesped(Long id) {
        boolean flag = false;
        Optional<Huesped> huespedOptional = repositorio.findById(id);

        if(huespedOptional.isPresent()){
            repositorio.delete(huespedOptional.get());
            flag = true;
        }

        return flag;
    }
}
