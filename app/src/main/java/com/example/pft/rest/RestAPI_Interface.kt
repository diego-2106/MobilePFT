package com.example.pft.rest

import com.example.pft.models.LoginBody
import com.example.pft.models.UsuarioDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RestAPI_Interface {

    @POST("usuarios/login")
    fun login(@Body loginBody: LoginBody): Call<UsuarioDTO?>
}