package com.example.sorteo

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sorteo.api.Participante
import com.example.sorteo.viewModel.ParticipanteViewModel
import com.example.sorteo.screen.FormularioScreen

class MainActivity: AppCompatActivity() {

    private val participanteViewModel: ParticipanteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FormularioScreen(
                participanteViewModel,
                onShowAllClicked = { startActivity(Intent(this@MainActivity, MostrarTodosActivity::class.java)) },
                onAddClicked = { nombre, telefono, dni, item ->

                    val nuevoParticipante = Participante(
                        nombre = nombre,
                        telefono = telefono,
                        dni = dni,
                        item = item
                    )
                    participanteViewModel.crearParticipante(nuevoParticipante)
                }
            )
        }
    }
}






