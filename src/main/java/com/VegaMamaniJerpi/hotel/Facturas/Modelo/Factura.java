package com.VegaMamaniJerpi.hotel.Facturas.Modelo;

import com.VegaMamaniJerpi.hotel.Enums.TipoDePago;
import com.VegaMamaniJerpi.hotel.Habitaciones.Modelo.Habitacion;
import com.VegaMamaniJerpi.hotel.Reservas.Modelo.Reserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "facturas")
public class Factura {

    /// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    //no se puede realizar una factura en el futuro
    //@NotNull(message = "La fecha es obligatoria")
    //@PastOrPresent(message = "La fecha debe ser hoy o una fecha pasada")
    private LocalDate fecha =  LocalDate.now();;

    @OneToOne (optional = false, fetch = FetchType.EAGER) ///la factura siempre tine que tener una reserva
    @JoinColumn (name = "idReserva", unique = true) ///una reserva tenga una sola factura
    private Reserva reserva;


    private double precioBase = 0;

    private double precioAjuste = 0;

    private double precioFinal = 0;

    @NotNull(message = "el tipo de pago no puede ser nulo")
    private TipoDePago tipoDePago;

    /// Constructores


    public Factura() {
    }

    public Factura(Reserva reserva, TipoDePago tipoDePago) {
        this.reserva = reserva;
        this.tipoDePago = tipoDePago;
    }

    /// Getters && setters


    public Long getIdFactura() {
        return idFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Reserva getReserva() {
        return reserva;
    }

    //si cambio la reserva, se calcula nuevamente el precio
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        calcularPrecio();
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getPrecioAjuste() {
        return precioAjuste;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public TipoDePago getTipoDePago() {
        return tipoDePago;
    }

    //si cambio el tipo de pago, se calcula nuevamente el precio
    public void setTipoDePago(TipoDePago tipoDePago) {
        this.tipoDePago = tipoDePago;
        calcularPrecio();
    }


    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setPrecioAjuste(double precioAjuste) {
        this.precioAjuste = precioAjuste;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    /// Metodos

    public void calcularPrecio (){
        if (reserva != null && reserva.getHabitacion() != null && tipoDePago != null) {
            long cantDias= reserva.getCantidadDeDias();

            setPrecioBase(cantDias*reserva.getHabitacion().getPrecioPorNoche());
            setPrecioAjuste(tipoDePago.calcularAjuste(precioBase));
            setPrecioFinal(precioBase + precioAjuste);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(idFactura, factura.idFactura);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idFactura);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", fecha=" + fecha +
                ", reserva=" + reserva +
                ", precioBase=" + precioBase +
                ", precioAjuste=" + precioAjuste +
                ", precioFinal=" + precioFinal +
                ", tipoDePago=" + tipoDePago +
                '}';
    }
}
