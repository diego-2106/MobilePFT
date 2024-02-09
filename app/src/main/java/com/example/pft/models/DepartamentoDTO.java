package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DepartamentoDTO {


    @SerializedName("idDepartamento")
    @Expose
    public Long IdDepartamento;

    @SerializedName("nombre")
    @Expose
    public String nombre;

    public DepartamentoDTO() {
        super();

    }



    public DepartamentoDTO( Long idDepartamento,  String nombre) {
        super();
        IdDepartamento = idDepartamento;
        this.nombre = nombre;
    }



    public Long getIdDepartamento() {

        return IdDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        IdDepartamento = idDepartamento;
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
