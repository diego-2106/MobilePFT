package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class LocalidadDTO implements Serializable {

    @SerializedName("idLocalidad")
    @Expose
    public Long IdLocalidad;

    @SerializedName("nombre")
    @Expose
    public String nombre;




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
