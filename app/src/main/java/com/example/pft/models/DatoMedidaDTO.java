package com.example.pft.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatoMedidaDTO {

    @SerializedName("idDatoMedida")
    @Expose
    private Long idDatoMedida;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("elemento")
    @Expose
    private String elemento;

    @SerializedName("valorAdmitido")
    @Expose
    private String valorAdmitido;



    public DatoMedidaDTO() {

    }

    public DatoMedidaDTO(Long idDatoMedida,String nombre, String elemento,
                         String valorAdmitido) {
        super();
        this.idDatoMedida = idDatoMedida;
        this.nombre = nombre;
        this.elemento = elemento;
        this.valorAdmitido = valorAdmitido;
    }

    public Long getIdDatoMedida() {
        return idDatoMedida;
    }

    public void setIdDatoMedida(Long idDatoMedida) {
        this.idDatoMedida = idDatoMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getValorAdmitido() {
        return valorAdmitido;
    }

    public void setValorAdmitido(String valorAdmitido) {
        this.valorAdmitido = valorAdmitido;
    }

    @Override
    public String toString() {
        return nombre + " | " + " Valor: " + valorAdmitido;
    }




}
