package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ModificarMedicionesDTO {


    @SerializedName("idMedicion")
    @Expose
    private long idMedicion;

    @SerializedName("departamento")
    @Expose
    private long departamento;

    @SerializedName("localidad")
    @Expose
    private long localidad;

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
    private Long actividad;

    @SerializedName("datoMedida")
    @Expose
    private Long datoMedida;


    public ModificarMedicionesDTO() {
        super();
    }



    public ModificarMedicionesDTO(Long idMedicion,long departamento, long localidad, String valor, Date fecha, String observaciones,
                         Long actividad, Long datoMedida) {
        super();
        this.idMedicion = idMedicion;
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

    public Long getDepartamento() {
        return departamento;
    }


    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }


    public Long getLocalidad() {
        return localidad;
    }


    public void setLocalidad(Long localidad) {
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


    public Long getActividad() {
        return actividad;
    }


    public void setActividad(Long actividad) {
        this.actividad = actividad;
    }


    public Long getDatoMedida() {
        return datoMedida;
    }


    public void setDatoMedida(Long datoMedida) {
        this.datoMedida = datoMedida;
    }



    @Override
    public String toString() {

        return valor + fecha + observaciones;
    }


}
