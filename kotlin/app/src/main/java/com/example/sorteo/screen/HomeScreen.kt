package com.example.sorteo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Column {
        // Formulario para crear participante
        // Aquí se incluyen campos para meter el DNI, nombre, etc.
        Button(onClick = { /* Acción al hacer clic */ }) {
            Text("Añadir")
        }
    }
}

