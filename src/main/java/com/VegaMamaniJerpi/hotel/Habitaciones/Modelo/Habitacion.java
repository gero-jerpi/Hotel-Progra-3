package com.VegaMamaniJerpi.hotel.Habitaciones.Modelo;
import com.VegaMamaniJerpi.hotel.Enums.TipoHabitacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table (name = "habitaciones")
public class Habitacion {

    /// Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;

    @NotNull(message = "El tipo de habitación no puede ser nulo")
    private TipoHabitacion tipoHabitacion;

    @Min(value = 1, message = "La habitación debe aceptar al menos 1 persona")
    @Max(value = 10, message = "La habitación no puede aceptar más de 10 personas")
    private int cantidadPersonas;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private double precioPorNoche;

    /// Constructores

    public Habitacion(){

    }

    public Habitacion(TipoHabitacion tipoHabitacion, int cantidadPersonas, double precioPorNoche) {
        this.tipoHabitacion = tipoHabitacion;
        this.cantidadPersonas = cantidadPersonas;
        this.precioPorNoche = precioPorNoche;
    }


    /// Getters && Setters

    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Long idHabitacion) {
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
