package com.VegaMamaniJerpi.hotel.Facturas.Servicio;

import com.VegaMamaniJerpi.hotel.Excepciones.FacturaExistente;
import com.VegaMamaniJerpi.hotel.Excepciones.FechaInvalidaException;
import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Facturas.Modelo.Factura;
import com.VegaMamaniJerpi.hotel.Facturas.Modelo.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FacturaServicioImpl implements FacturaServicio {

    @Autowired
    private FacturaRepositorio repositorio;

    @Override
    public List<Factura> listarFacturas() {
        return repositorio.findAll();
    }

    @Override
    public Factura guardarFactura(Factura nuevaFactura) throws FacturaExistente {
        if (nuevaFactura.getReserva() != null) {
            Optional<Factura> existente = repositorio.findByReservaId(nuevaFactura.getReserva().getIdReserva());
            if (existente.isPresent()) {
                throw new FacturaExistente("Esa reserva ya tiene una factura asociada.");
            }
        }
        return repositorio.save(nuevaFactura);
    }

    @Override
    public Factura actualizarFactura(Long id, Factura factura) throws IdNoEncontradoException , FechaInvalidaException, FacturaExistente {


        if (factura.getFecha().isAfter(LocalDate.now())) {
            throw new FechaInvalidaException("La fecha de la factura no puede ser futura");
        }

        Factura facturaModificada= repositorio.findById(id).orElseThrow(()-> new IdNoEncontradoException("Id no encontrado"));

        if (factura.getReserva() != null && !factura.getReserva().getIdReserva().equals(facturaModificada.getReserva().getIdReserva())) {
            Optional<Factura> existente = repositorio.findByReservaId(factura.getReserva().getIdReserva());
            if (existente.isPresent()) {
                throw new FacturaExistente("La nueva reserva ya tiene una factura asociada.");
            }
        }

        facturaModificada.setFecha(factura.getFecha());
        facturaModificada.setReserva(factura.getReserva());
        facturaModificada.setTipoDePago(factura.getTipoDePago());

        return repositorio.save(facturaModificada);
    }

    @Override
    public boolean eliminarFactura(Long id) throws IdNoEncontradoException {
        Factura factura= repositorio.findById(id).orElseThrow(()-> new IdNoEncontradoException("Id no encontrado"));
        repositorio.delete(factura);
        return true;
    }

    public Factura obtenerFacturaPorId(Long id) throws IdNoEncontradoException {
        return repositorio.findById(id).orElseThrow(() -> new IdNoEncontradoException("Id no encontrado"));
    }
}
