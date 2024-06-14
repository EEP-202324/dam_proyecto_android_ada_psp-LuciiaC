package com.example.sorteo.api

import kotlinx.serialization.Serializable
@Serializable
data class Participante(
    val id: Int,
    val nombre: String,
    val dni: String,
    val numeroTelefono: String,
    val itemComprado: String
)

