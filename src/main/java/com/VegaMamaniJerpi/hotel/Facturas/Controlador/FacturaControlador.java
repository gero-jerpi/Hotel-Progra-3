package com.VegaMamaniJerpi.hotel.Facturas.Controlador;

import com.VegaMamaniJerpi.hotel.Excepciones.FechaInvalidaException;
import com.VegaMamaniJerpi.hotel.Excepciones.HabitacionReservadaException;
import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Facturas.Modelo.Factura;
import com.VegaMamaniJerpi.hotel.Facturas.Servicio.FacturaServicioImpl;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaControlador {

    @Autowired
    private FacturaServicioImpl servicio;

    @GetMapping
    public List<Factura> listarFacturas(){
        return servicio.listarFacturas();
    }

    @PostMapping
    @Transactional
    public Factura guardarFactura (@RequestBody Factura nuevaFactura){
        return servicio.guardarFactura(nuevaFactura);
    }

    @PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable Long id, @RequestBody Factura factura) throws IdNoEncontradoException{
        return servicio.actualizarFactura(id, factura);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarReserva(@PathVariable Long id) throws IdNoEncontradoException {
        return servicio.eliminarFactura(id);
    }
}
