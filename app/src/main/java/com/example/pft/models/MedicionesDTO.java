package com.example.pft.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MedicionesDTO {


    @SerializedName("idMedicion")
    private Long idMedicion;

    @SerializedName("departamento")
    private DepartamentoDTO departamento;
    @SerializedName("localidad")
    private LocalidadDTO localidad;

    @SerializedName("valor")
    private String valor;

    @SerializedName("fecha")
    private Date fecha;

    @SerializedName("actividad")
    private ActividadDeCampoDTO actividad;

    @SerializedName("datoMedida")
    private DatoMedidaDTO datoMedida;

    @SerializedName("descripcion")
    private String descripcion;


    public MedicionesDTO() {

    }



    public MedicionesDTO(Long idMedicion, DepartamentoDTO departamento, LocalidadDTO localidad,
                         String valor, Date fecha, ActividadDeCampoDTO actividad, DatoMedidaDTO datoMedida, String descripcion) {
        super();
        this.idMedicion = idMedicion;
        this.departamento = departamento;
        this.localidad = localidad;
        this.valor = valor;
        this.fecha = fecha;
        this.actividad = actividad;
        this.datoMedida = datoMedida;
        this.descripcion = descripcion;
    }



    public Long getIdMedicion() {
        return idMedicion;
    }

    public void setIdMedicion(Long idMedicion) {
        this.idMedicion = idMedicion;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public LocalidadDTO getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ActividadDeCampoDTO getActividad() {
        return actividad;
    }

    public void setActividad(ActividadDeCampoDTO actividad) {
        this.actividad = actividad;
    }

    public DatoMedidaDTO getDatoMedida() {
        return datoMedida;
    }

    public void setDatoMedida(DatoMedidaDTO datoMedida) {
        this.datoMedida = datoMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "MedicionDTO [idMedicion=" + idMedicion + ", departamento=" + departamento + ", localidad=" + localidad
                + ", valor=" + valor + ", fecha=" + fecha + ", actividad=" + actividad + ", datoMedida=" + datoMedida
                + ", descripcion=" + descripcion + "]";
    }


}