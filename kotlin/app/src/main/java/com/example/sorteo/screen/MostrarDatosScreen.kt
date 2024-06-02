package com.example.sorteo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.sorteo.api.Participante

@Composable
fun MostrarDatosScreen() {
    Column {
        // Lista de participantes
        LazyColumn {
            items(participantes) { participante ->
                ParticipanteItem(participante)
            }
        }
        // Botón para crear participante
        Button(onClick = { /* Acción al hacer clic */ }) {
            Text("Añadir participante")
        }
    }
}

@Composable
fun ParticipanteItem(participante: Participante) {
    // Composable para mostrar una tarjeta de la lista de participantes
    // Aquí se muestra la información del participante, como el ID, DNI, nombre, etc.
}