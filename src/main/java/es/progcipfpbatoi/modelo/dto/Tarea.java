package es.progcipfpbatoi.modelo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Tarea {

    private int id;

    private String descripcion;

    private LocalDateTime fechaAlta;

    private boolean finalizada;

    private Categoria categoria;

    public Tarea(int id, String descripcion, LocalDateTime fechaAlta, boolean finalizada, Categoria categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.finalizada = finalizada;
        this.categoria = categoria;
    }

    public Tarea(int id, String descripcion, Categoria categoria) {
        this(id, descripcion, LocalDateTime.now(), false, categoria);
    }

    public Tarea(int id)  {
        this(id, null, null, false, null);
    }
    public int getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void cambiarEstado() {
        this.finalizada = !this.finalizada;
    }

    public LocalDate getFechaAltaSinTiempo() {
        return this.fechaAlta.toLocalDate();
    }
    @Override
    public String toString() {
        return descripcion + " " + finalizada;
    }

    public boolean empiezaPor(String text) {
        return this.descripcion.startsWith(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return id == tarea.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public LocalDateTime getFechaAlta() {
        return this.fechaAlta;
    }
}
