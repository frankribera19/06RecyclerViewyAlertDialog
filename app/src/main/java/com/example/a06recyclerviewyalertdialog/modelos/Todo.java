package com.example.a06recyclerviewyalertdialog.modelos;

import java.time.LocalDate;

public class Todo {
    private String titulo;
    private String Contenido;
    private boolean completado;
    private LocalDate fecha;

    public Todo(String titulo, String contenido, boolean completado) {
        this.titulo = titulo;
        Contenido = contenido;
        this.completado = completado;
        this.fecha = LocalDate.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
