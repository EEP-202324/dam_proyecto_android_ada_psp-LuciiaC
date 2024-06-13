package com.example.sorteo



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sorteo.api.Participante
import com.example.sorteo.api.ParticipanteApi
import com.example.sorteo.ui.theme.SorteoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SorteoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main_screen") {
                    composable("main_screen") {
                        HomeScreen(navController)
                    }
                    composable("pantalla_participantes") {
                        ParticipanteListScreen(navController)
                    }
                    composable("pantalla_anadir") {
                        AddParticipanteScreen(navController)
                    }
                    composable("pantalla_participante/{id}"){
                        val id = it.arguments?.getString("id")
                        id?.let { id ->
                            ParticipanteScreen(navController, id)
                        } ?: run {
                            HomeScreen(navController)
                        }
                    }
                    composable("pantalla_actualizar/{id}"){

                        val id = it.arguments?.getString("id")
                        id?.let { id ->
                            UpdateScreen(navController, id)
                        } ?: run{
                            HomeScreen(navController)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ParticipanteListScreen(navController: NavController) {

        val participanteState = remember { mutableStateOf<List<Participante>>(emptyList()) }

        LaunchedEffect(key1 = Unit) {
            try {
                val participante: List<Participante> = View.compartido.getParticipante()
                participanteState.value = participante
                Log.i("participantes", participanteState.value.toString())

            } catch (e: Exception) {
                Log.i("participantes","La lista de participantes está vacia.")
                participanteState.value = emptyList()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), verticalArrangement = Arrangement.Top ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lista de participantes",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {
                items(participanteState.value) { participante ->
                    ParticipanteItem(participante = participante,navController)
                }
            }
        }
    }

    @Composable
    fun ParticipanteItem(participante: Participante, navController: NavController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "${participante.nombre} ${participante.dni}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Número de teléfono: ${participante.numeroTelefono}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Gray
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                View.compartido.deleteParticipante(participante.id)
                                navController.navigate("pantalla_participante")
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text("Borrar")
                        }

                        Button(
                            onClick = {
                                navController.navigate("pantalla_participante/${participante.id}")
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text("Ver más")
                        }
                    }
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("pantalla_actualizar/${participante.id}")
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text("Actualizar")
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun ParticipanteScreen(navController: NavController, id: String){

        val id = id.toInt()

        val participanteState = remember { mutableStateOf<Participante>(Participante(0, "", "", "", "")) }

        Log.i("Participante", "$id")

        LaunchedEffect(key1 = Unit) {
            try {
                val participante = View.compartido.getParticipanteById(id)
                participanteState.value = participante
                Log.i("Participante", "$participanteState")
            } catch (e: Exception) {
                Log.e("ErrorRespuesta", "No se ha encontrado ningún participante :(")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                shape = RoundedCornerShape(16.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Nombre: ${participanteState.value.nombre} ${participanteState.value.nombre}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "DNI: ${participanteState.value.dni}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Numero de teléfono: ${participanteState.value.numeroTelefono}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Item Comprado: ${participanteState.value.itemComprado ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Volver <-")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddParticipanteScreen(navController: NavController) {
    var participante by remember { mutableStateOf(Participante(0, "", "", "", "")) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Añadir participante",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = participante.nombre,
            onValueChange = { participante = participante.copy(nombre = it) },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = participante.dni,
            onValueChange = { participante = participante.copy(dni = it) },
            label = { Text("DNI") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = participante.numeroTelefono,
            onValueChange = { participante = participante.copy(numeroTelefono = it) },
            label = { Text("Número de teléfono") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = participante.itemComprado,
            onValueChange = { participante = participante.copy(itemComprado = it) },
            label = { Text("Item comprado") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            View.compartido.sendParticipante(participante)
            keyboardController?.hide()
            navController.navigate("main_screen")
        }) {
            Text("¡Participar!")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate("pantalla_anadir") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Añadir participante")
        }
        Button(
            onClick = { navController.navigate("pantalla_participantes") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Todos los participantes")
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UpdateScreen(navController: NavController, id: String) {
    val participanteId = id.toInt()
    var participante by remember { mutableStateOf(Participante(0, "", "", "", "")) }

    LaunchedEffect(key1 = participanteId) {
        try {
            participante = View.compartido.getParticipanteById(participanteId)
        } catch (e: Exception) {
            Log.e("ErrorRespuesta", "No se ha encontrado el participante :(", e)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Actualizar",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = participante.numeroTelefono.toString(),
            onValueChange = { participante = participante.copy(numeroTelefono = it) },
            label = { Text("Número de teléfono") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = participante.itemComprado.toString(),
            onValueChange = { participante = participante.copy(itemComprado = it) },
            label = { Text("Item comprado") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            try {
                View.compartido.actualizarParticipante(participanteId, participante)
                navController.navigate("principal")
            } catch (e: Exception) {
                Log.e("ErrorRespuesta", "Error al actualizar participante :(", e)
            }
        }) {
            Text("¡Participar!")
        }
    }
}

class View : ViewModel() {

    companion object {
        val compartido: View by lazy { View() }
    }

    fun sendParticipante(participante: Participante) {
        viewModelScope.launch {
            try {
                ParticipanteApi.retrofitService.createParticipante(participante)
                Log.i("Respuesta", "El participante ha participado.")

            } catch (e: Exception) {
                Log.e("ErrorRespuesta", e.stackTraceToString())
                Log.i("Respuesta", "El participante NO ha participado.")
            }
        }
    }

    suspend fun getParticipante(): List<Participante> {
        return try {
            val participante = ParticipanteApi.retrofitService.getParticipante()
            Log.i("Respuesta", "Los participantes se han obtenido: $participante")
            participante
        } catch (e: Exception) {
            Log.e("ErrorRespuesta", "Error al obtener participantes: ${e.message}")
            emptyList()
        }
    }

    fun deleteParticipante(id: Int){
        viewModelScope.launch {
            try{
                ParticipanteApi.retrofitService.deleteParticipante(id)
                Log.i("Respuesta", "El participante se ha eliminado.")
            }catch (e: Exception){
                Log.e("ErrorRespuesta", "El participante no se ha eliminado.")
            }
        }
    }

    suspend fun getParticipanteById(id: Int): Participante{
        return try{
            val participante = ParticipanteApi.retrofitService.getParticipanteById(id)
            Log.i("Respuesta", "El participante se ha obtenido.")
            participante
        } catch (e: Exception){
            Log.e("ErrorRespuesta", "El participante no se ha obtenido: ${e.message}")
            Participante(0, "", "", "", "")
        }
    }

    fun actualizarParticipante(id: Int, participante: Participante) {
        viewModelScope.launch {
            try {
                ParticipanteApi.retrofitService.updateParticipante(id, participante)
                Log.i("Respuesta", "El participante se ha actualizado.")

            } catch (e: Exception) {
                Log.e("ErrorRespuesta", "El participante no se ha actualizado: ${e.message}")
            }
        }
    }
}






