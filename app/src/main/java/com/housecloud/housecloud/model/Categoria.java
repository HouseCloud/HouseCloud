package com.housecloud.housecloud.model;

/**
 * Created by Jozet on 12/03/2018.
 */

public class Categoria {

    private String categoria;
    private int iconoC;

    public Categoria(String categoria, int iconoC) {
        this.categoria = categoria;
        this.iconoC = iconoC;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIconoC() {
        return iconoC;
    }

    public void setIconoC(int iconoC) {
        this.iconoC = iconoC;
    }
}
