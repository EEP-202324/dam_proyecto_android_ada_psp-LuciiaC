package com.example.sorteo.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL =
        "http://10.0.2.2:8080" // esta es la IP del localhost del ordenador
private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

interface ParticipanteApiService {
        @GET("api_participante/")
        suspend fun getParticipante(): List<Participante>

        @GET("api_participante/{id}")
        suspend fun getParticipanteById(@Path("id") id:Int): Participante

        @POST("api_participante/create")
        suspend fun createParticipante(@Body participante: Participante): Participante

        @DELETE("api_participante/{id}")
        suspend fun deleteParticipante(@Path("id") id:Int)

        @PUT("api_participante/{id}")
        suspend fun updateParticipante(@Path("id") id: Int, @Body participante: Participante)
}

object ParticipanteApi {
        val retrofitService: ParticipanteApiService by lazy {
                retrofit.create(ParticipanteApiService::class.java)
        }
}



