package com.example.pft.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginBody implements Serializable {
    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;

    @SerializedName("contrasena")
    @Expose
    private String password;


    public LoginBody(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public LoginBody() {

    }

    public String getNombreUsuario() {
        return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) {

        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

}
