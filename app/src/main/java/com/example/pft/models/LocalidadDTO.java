package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalidadDTO {

    @SerializedName("idLocalidad")
    @Expose
    private Long IdLocalidad;

    @SerializedName("nombre")
    @Expose
    private String nombre;




    public LocalidadDTO() {
        super();
        // TODO Auto-generated constructor stub
    }



    public LocalidadDTO(Long idLocalidad, String nombre) {
        super();
        IdLocalidad = idLocalidad;
        this.nombre = nombre;
    }



    public Long getIdLocalidad() {
        return IdLocalidad;
    }

    public void setIdLocalidad(Long idLocalidad) {
        IdLocalidad = idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }



}
