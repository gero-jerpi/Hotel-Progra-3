package com.VegaMamaniJerpi.hotel.Facturas.Servicio;

import com.VegaMamaniJerpi.hotel.Excepciones.FacturaExistenteException;
import com.VegaMamaniJerpi.hotel.Excepciones.FechaInvalidaException;
import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Facturas.Modelo.Factura;
import com.VegaMamaniJerpi.hotel.Facturas.Modelo.FacturaRepositorio;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServicioImpl implements FacturaServicio {

    @Autowired
    private FacturaRepositorio repositorio;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Override
    public List<Factura> listarFacturas() {
        return repositorio.findAll();
    }

    @Override
    public Factura guardarFactura(Factura nuevaFactura) throws FacturaExistenteException {
        if (nuevaFactura.getReserva() != null) {
            // Buscar la reserva completa por id para cargar habitación y fechas
            Reserva reservaCompleta = reservaRepositorio.findById(nuevaFactura.getReserva().getIdReserva())
                    .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

            nuevaFactura.setReserva(reservaCompleta);
        }

        Optional<Factura> existente = repositorio.findByReserva_IdReserva(nuevaFactura.getReserva().getIdReserva());
        if (existente.isPresent()) {
            throw new FacturaExistenteException("Esa reserva ya tiene una factura asociada.");
        }

        nuevaFactura.calcularPrecio();

        return repositorio.save(nuevaFactura);
    }

    @Override
    public Factura actualizarFactura(Long id, Factura factura) throws IdNoEncontradoException , FechaInvalidaException, FacturaExistenteException {


        if (factura.getFecha().isAfter(LocalDate.now())) {
            throw new FechaInvalidaException("La fecha de la factura no puede ser futura");
        }

        Factura facturaModificada= repositorio.findById(id).orElseThrow(()-> new IdNoEncontradoException("Id no encontrado"));

        if (factura.getReserva() != null && !factura.getReserva().getIdReserva().equals(facturaModificada.getReserva().getIdReserva())) {
            Optional<Factura> existente = repositorio.findByReserva_IdReserva(factura.getReserva().getIdReserva());
            if (existente.isPresent()) {
                throw new FacturaExistenteException("La nueva reserva ya tiene una factura asociada.");
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
