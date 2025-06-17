package com.VegaMamaniJerpi.hotel.Huesped.Controlador;

import com.VegaMamaniJerpi.hotel.Excepciones.DNIyaCargadoException;
import com.VegaMamaniJerpi.hotel.Huesped.Modelo.Huesped;
import com.VegaMamaniJerpi.hotel.Huesped.Servicio.HuespedServicioImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/huespeds")

public class HuespedControlador {

    @Autowired
    private HuespedServicioImpl servicio;

    @GetMapping
    public List<Huesped> listarHuespeds(){
        return servicio.listarHuespeds();
    }


    @PostMapping
    @Transactional
    public Huesped guardarHuesped(@RequestBody Huesped nuevoHuesped) throws DNIyaCargadoException {
        return servicio.guardarHuesped(nuevoHuesped);
    }

    @PutMapping("/{id}")
    public Huesped actualizarHuesped(@PathVariable Long id, @RequestBody Huesped huesped){
        return servicio.actualizarHuesped(id, huesped);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarHuesped(@PathVariable Long id){
        return servicio.eliminarHuesped(id);
    }

}
