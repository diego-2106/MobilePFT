package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class FormularioDTO {

    @SerializedName("idFormulario")
    @Expose
    private Long idFormulario;


    @SerializedName("nombreFormulario")
    @Expose
    private String nombre;

    @SerializedName("fecha")
    @Expose
    private Date fecha;

    @SerializedName("comentarios")
    @Expose
    private String comentarios;

    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    @SerializedName("activo")
    @Expose
    private boolean activo;





    public FormularioDTO() {
        // TODO Auto-generated constructor stub
    }



    public FormularioDTO(Long idFormulario, String nombre,
                         Date fecha, String comentarios, UsuarioDTO usuario, boolean activo) {
        super();
        this.idFormulario = idFormulario;
        this.nombre = nombre;
        this.fecha = fecha;
        this.comentarios = comentarios;
        this.usuario = usuario;
        this.activo = activo;
    }





    public Long getIdFormulario() {
        return idFormulario;
    }



    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public Date getFecha() {
        return fecha;
    }



    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }



    public String getComentarios() {
        return comentarios;
    }



    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }



    public UsuarioDTO getUsuario() {
        return usuario;
    }



    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    @Override
    public String toString() {
        return nombre;
    }

}

