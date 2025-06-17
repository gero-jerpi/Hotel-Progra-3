package com.VegaMamaniJerpi.hotel.Excepciones;

public class FacturaExistente extends RuntimeException {
    public FacturaExistente(String message) {
        super(message);
    }
}
