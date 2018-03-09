package com.housecloud.housecloud.model;

import java.io.Serializable;

/**
 * Created by Jozet on 08/03/2018.
 */

public class Post{
    private String titulo;
    private String descripcion;
    private String categoria;
    private String id_user;

    public Post() {
    }

    public Post(String titulo, String descripcion, String categoria, String id_user) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.id_user = id_user;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
