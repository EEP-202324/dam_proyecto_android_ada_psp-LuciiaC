package com.example.sorteo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorteo.api.ApiService
import com.example.sorteo.api.Participante
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParticipanteViewModel: ViewModel() {

    private val _participante = MutableLiveData<List<Participante>>()
    val participante: LiveData<List<Participante>> get()=_participante

    private val _participar = MutableLiveData<Boolean>()
    val participar : LiveData<Boolean> get()=_participar

    private val apiService: ApiService

    init {
        val retrofit= Retrofit.Builder()
            .baseUrl("http://10.0.2.2.8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        fetchParticipar()
    }
    fun fetchParticipar() {
        viewModelScope.launch {
            try {
                val response = apiService.getParticipante()
                _participante.postValue(response)
            } catch (e:Exception) {

            }
        }

    }
    fun formularParticipar (formularioParticipar: FormularioParticipantar){
        viewModelScope.launch {
            try {
                val response = apiService.createParticipante(formularioParticipar)
                if (response.isSuccessful) {
                    _participar.postValue(true)
                    fetchParticipar()
                }
                else {
                    _participar.postValue(false)
                }
            } catch (e:Exception) {
                _participar.postValue(false)
            }
        }

    }    }




