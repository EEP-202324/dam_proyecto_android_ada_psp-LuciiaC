package com.example.sorteo.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
    interface ApiService {
        @GET("participante")
        fun getParticipante(): List<Participante>

        @POST("participante")
        fun crearParticipante(@Body participante: Participante): Response<Void>
    }

