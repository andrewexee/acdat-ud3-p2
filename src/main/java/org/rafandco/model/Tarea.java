package org.rafandco.model;
import java.time.LocalDate;

/**
 * @author Andrés Iglesias Camacho
 * @date 28.11.2025
 */
public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private boolean completada;
    private LocalDate fechaCreacion;

    /**
     * Constructor para nuevas tareas (sin ID)
     * @param titulo
     * @param descripcion
     * @param fechaCreacion
     */
    public Tarea(String titulo, String descripcion, LocalDate fechaCreacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Constructor completo (incluyendo id), útil para reconstruir objetos desde la base de datos:
     * @param id
     * @param titulo
     * @param descripcion
     * @param completada
     * @param fechaCreacion
     */
    public Tarea(int id, String titulo, String descripcion, boolean completada, LocalDate fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = completada;
        this.fechaCreacion = fechaCreacion;
    }

    // Gerers and serers
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return this.completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Título: %s
                Descripcion: %s
                Completada: %b
                Fecha: %s""", this.id, this.titulo, this.descripcion, this.completada, this.fechaCreacion);
    }
}
