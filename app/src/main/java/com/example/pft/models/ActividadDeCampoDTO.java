package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActividadDeCampoDTO {

    @SerializedName("idActividad")
    @Expose
    private Long idActividad;

    @SerializedName("nombreFormulario")
    @Expose
    private FormularioDTO formulario;

    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public ActividadDeCampoDTO() {

    }

    public ActividadDeCampoDTO(Long idActividad, FormularioDTO formulario, UsuarioDTO usuario) {
        super();
        this.idActividad = idActividad;
        this.formulario = formulario;
        this.usuario = usuario;
    }


    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public FormularioDTO getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioDTO formulario) {
        this.formulario = formulario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ID Actividad " + idActividad + usuario;
    }


}

