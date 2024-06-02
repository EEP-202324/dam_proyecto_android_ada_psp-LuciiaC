package com.example.sorteo.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sorteo.api.Participante
import retrofit2.create

interface ApiService {

        @GET("participante")
        fun getParticipante(): Response<List<Participante>>

        @GET("participante/{id}")
        fun getParticipanteById(@Path("id") id: Int): Response<Participante>

        @POST("participante")
        fun createParticipante(@Body participante: Participante): Response<Participante>

    }
val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
object ApiClient {
        val service: ApiService by Lazy {
                retrofit.create(ApiService::class.java)
        }
}

