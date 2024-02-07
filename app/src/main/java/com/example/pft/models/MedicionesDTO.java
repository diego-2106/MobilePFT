package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MedicionesDTO {

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
    private Date fecha;

    @SerializedName("observaciones")
    @Expose
    private String observaciones;

    @SerializedName("actividad")
    @Expose
    private Long actividad;

    @SerializedName("datoMedida")
    @Expose
    private Long datoMedida;

    @SerializedName("estado")
    @Expose
    private String estado;


    public MedicionesDTO() {
        super();
    }



    public MedicionesDTO(long departamento, long localidad, String valor, Date fecha, String observaciones,
                       Long actividad, Long datoMedida, String estado) {
        super();
        this.departamento = departamento;
        this.localidad = localidad;
        this.valor = valor;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.actividad = actividad;
        this.datoMedida = datoMedida;
        this.estado = estado;
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


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
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


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "MedicionDTO [departamento=" + departamento + ", localidad=" + localidad + ", valor=" + valor
                + ", fecha=" + fecha + ", observaciones=" + observaciones + ", actividad=" + actividad + ", datoMedida="
                + datoMedida + ", estado=" + estado + "]";
    }


}
