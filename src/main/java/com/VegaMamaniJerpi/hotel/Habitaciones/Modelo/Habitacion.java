package com.VegaMamaniJerpi.hotel.Habitaciones.Modelo;
import com.VegaMamaniJerpi.hotel.Enums.TipoHabitacion;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "habitaciones")
public class Habitacion {

    /// Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idHabitacion;

    private TipoHabitacion tipoHabitacion;
    private int cantidadPersonas;
    private double precioPorNoche;

    /// Constructor

    public Habitacion(){

    }

    public Habitacion(long idHabitacion, TipoHabitacion tipoHabitacion, int cantidadPersonas, double precioPorNoche) {
        this.idHabitacion = idHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.cantidadPersonas = cantidadPersonas;
        this.precioPorNoche = precioPorNoche;
    }


    /// Getters && Setters


    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }


    /// Metodos


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitacion that)) return false;
        return idHabitacion == that.idHabitacion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idHabitacion);
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", tipoHabitacion=" + tipoHabitacion +
                ", cantidadPersonas=" + cantidadPersonas +
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }
}
