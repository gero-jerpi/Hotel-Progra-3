package com.VegaMamaniJerpi.hotel.Facturas.Servicio;


import com.VegaMamaniJerpi.hotel.Excepciones.IdNoEncontradoException;
import com.VegaMamaniJerpi.hotel.Facturas.Modelo.Factura;

import java.util.List;

public interface FacturaServicio {
    List<Factura> listarFacturas();
    Factura guardarFactura(Factura nuevaFactura);
    Factura actualizarFactura(Long id, Factura factura) throws IdNoEncontradoException;
    boolean eliminarFactura(Long id) throws IdNoEncontradoException;
}
