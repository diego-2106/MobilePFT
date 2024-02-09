package com.example.pft.rest

import com.example.pft.models.ActividadDeCampoDTO
import com.example.pft.models.DatoMedidaDTO
import com.example.pft.models.DepartamentoDTO
import com.example.pft.models.ListaMedicionesDTO
import com.example.pft.models.LocalidadDTO
import com.example.pft.models.LoginBody
import com.example.pft.models.MedicionesDTO
import com.example.pft.models.UsuarioDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface RestAPI_Interface {

    @POST("usuarios/login")
    fun login(@Body loginBody: LoginBody): Call<UsuarioDTO?>

    /*Departamento*/
    @GET("departamentos/listarDepartamentos")
    fun getDepartamentos(): Call<List<DepartamentoDTO>>

    @GET("departamentos/listarLocalidades")
    fun getLocalidades(): Call<List<LocalidadDTO>>

    @GET("actividades/listarActividades")
    fun getActividades(): Call<List<ActividadDeCampoDTO>>

    @GET("medidas/listarDatosMedida")
    fun getDatosMedida(): Call<List<DatoMedidaDTO>>

    @GET("registros/listarMediciones")
    fun getMediciones(): Call<List<ListaMedicionesDTO>>

    @POST("registros/crear")
    fun addRegistro(@Body registro: MedicionesDTO): Call<Void>

    @PUT("registros/modificar")
    fun updateRegistro(@Body registro: MedicionesDTO?): Call<Void>
}