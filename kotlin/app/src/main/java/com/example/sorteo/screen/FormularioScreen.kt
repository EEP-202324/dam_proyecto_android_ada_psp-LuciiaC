package com.example.sorteo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.sorteo.viewModel.ParticipanteViewModel

@Composable
fun FormularioScreen() {
    participanteViewModel: ParticipanteViewModel,
    onShowAllClicked: () -> Unit,
    onAddClicked: (String, String, String, String) -> Unit // Pasar los campos como parámetros


    Column {
        // Formulario para crear participante
        // Aquí se incluyen campos para meter el DNI, nombre, etc.
        Button(onClick = { /* Acción al hacer clic */ }) {
            Text("Añadir")
        }
    }
}

