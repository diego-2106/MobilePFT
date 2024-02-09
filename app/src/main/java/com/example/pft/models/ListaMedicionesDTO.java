package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ListaMedicionesDTO implements Serializable {

    @SerializedName("idMedicion")
    @Expose
    private long idMedicion;

    @SerializedName("departamento")
    @Expose
    private DepartamentoDTO departamento;

    @SerializedName("localidad")
    @Expose
    private LocalidadDTO localidad;

    @SerializedName("valor")
    @Expose
    private String valor;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("observaciones")
    @Expose
    private String observaciones;

    @SerializedName("actividad")
    @Expose
    private ActividadDeCampoDTO actividad;

    @SerializedName("datoMedida")
    @Expose
    private DatoMedidaDTO datoMedida;


    public ListaMedicionesDTO() {
        super();
    }



    public ListaMedicionesDTO(DepartamentoDTO departamento, LocalidadDTO localidad, String valor, Date fecha, String observaciones,
                         ActividadDeCampoDTO actividad, DatoMedidaDTO datoMedida) {
        super();
        this.departamento = departamento;
        this.localidad = localidad;
        this.valor = valor;


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-8"));
        this.fecha = dateFormat.format(fecha);

        this.observaciones = observaciones;
        this.actividad = actividad;
        this.datoMedida = datoMedida;
    }


    public long getIdMedicion() {

        return idMedicion;
    }


    public void setIdMedicion(long idMedicion) {

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


    public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getObservaciones() {
        return observaciones;
    }


    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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



    @Override
    public String toString() {
        return valor + fecha + observaciones + departamento + localidad + datoMedida + actividad;
    }


}
